package com.yusuf.moviesearch.presentation.movie_detail


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuf.moviesearch.domain.use_case.get_movie_detail.GetMovieDetailUseCase
import com.yusuf.moviesearch.presentation.movies.MoviesEvent
import com.yusuf.moviesearch.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import com.yusuf.moviesearch.util.Constants.IMBD_ID

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailUseCase,
    private val stateHandle: SavedStateHandle
): ViewModel() {



    private val _state = MutableStateFlow<MovieDetailState>(MovieDetailState())
    val state: StateFlow<MovieDetailState> = _state

    init {
        stateHandle.get<String>(IMBD_ID)?.let {
            getMovieDetail(it)
        }
    }

    private fun getMovieDetail(imdbId: String){
        getMovieDetailsUseCase.executeGetMovieDetail(imdb = imdbId).onEach {
            when(it){
                is Resource.Success ->{
                    _state.value = MovieDetailState(movie = it.data)
                }
                is Resource.Error ->{
                    _state.value = MovieDetailState(error = it.message ?: "Error!")
                }
                is Resource.Loading -> {
                    _state.value = MovieDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: MoviesEvent) {

        when (event) {

            is MoviesEvent.Search -> {

                getMovieDetail(event.search)

            }

        }
    }
}