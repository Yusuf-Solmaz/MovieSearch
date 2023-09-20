package com.yusuf.moviesearch.presentation.movies

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuf.moviesearch.data.remote.MovieAPI
import com.yusuf.moviesearch.data.remote.dto.toMovieList
import com.yusuf.moviesearch.data.repository.MovieRepositoryImp
import com.yusuf.moviesearch.domain.model.Movie
import com.yusuf.moviesearch.domain.repository.MovieRepository
import com.yusuf.moviesearch.domain.use_case.get_movies.GetMovieUseCase
import com.yusuf.moviesearch.presentation.movies.adapter.MovieRecyclerViewAdapter
import com.yusuf.moviesearch.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMovieUseCase,
    private val api: MovieAPI
): ViewModel() {


    lateinit var repo: MovieRepository


    private val _state = MutableStateFlow<MoviesState>(MoviesState())
    val state: StateFlow<MoviesState> = _state

    private var job : Job? = null

    init {
        getMovies(_state.value.search)
    }


        private fun getMovies(search: String){
        job?.cancel()
        job= getMoviesUseCase.executeGetMovies(search).onEach {
            when(it){
                is Resource.Success -> {
                    _state.value = MoviesState(movies = it.data ?: emptyList())
                }
                is Resource.Error ->{
                    _state.value = MoviesState(error = it.message ?: "Error!")
                }
                is Resource.Loading -> {
                    _state.value = MoviesState(isLoading = true)
                }

            }

        }.launchIn(viewModelScope)
    }

    fun onEvent(event: MoviesEvent){
        when(event){
            is MoviesEvent.Search ->{
                getMovies(event.search)
            }

        }
    }


    fun getMoviesFromAPI(search: String ,movieList: MutableList<Movie>, movieAdapter: MovieRecyclerViewAdapter, activity: FragmentActivity) {
        repo = MovieRepositoryImp(api = api)

        job = CoroutineScope(Dispatchers.IO).launch {
            val newMovieList = (repo as MovieRepositoryImp).getMovies(search).toMovieList() as ArrayList<Movie>

            try {
                activity.runOnUiThread {
                    // movieList içeriğini güncelle, ancak yeniden atama yapma
                    movieList.clear()
                    movieList.addAll(newMovieList)

                    // movieAdapter güncellemesi
                    movieAdapter.updateData(newMovieList)

                    println(movieList)
                }
            }
            catch (e: Exception){
                println("Movie Not Found!"+ e.localizedMessage)
            }
        }
    }
}