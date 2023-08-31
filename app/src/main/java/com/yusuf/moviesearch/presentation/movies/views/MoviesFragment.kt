package com.yusuf.moviesearch.presentation.movies.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusuf.moviesearch.R
import com.yusuf.moviesearch.data.remote.MovieAPI
import com.yusuf.moviesearch.data.remote.dto.toMovieList
import com.yusuf.moviesearch.data.repository.MovieRepositoryImp
import com.yusuf.moviesearch.databinding.FragmentMoviesBinding
import com.yusuf.moviesearch.domain.model.Movie
import com.yusuf.moviesearch.domain.repository.MovieRepository
import com.yusuf.moviesearch.presentation.movies.MoviesViewModel
import com.yusuf.moviesearch.presentation.movies.adapter.MovieRecyclerViewAdapter
import com.yusuf.moviesearch.util.Constants
import com.yusuf.moviesearch.util.Constants.API_KEY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class MoviesFragment() : Fragment(R.layout.fragment_movies), MovieRecyclerViewAdapter.Listener {

    private var fragmentBinding: FragmentMoviesBinding? = null
    private lateinit var viewModel: MoviesViewModel

    private var movieList= arrayListOf<Movie>()

    private var movieAdapter = MovieRecyclerViewAdapter(arrayListOf(),this)


    private var job: Job? = null
    lateinit var repo: MovieRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MoviesViewModel::class.java]

        val binding = FragmentMoviesBinding.bind(view)
        fragmentBinding = binding

        val layoutManager = LinearLayoutManager(requireContext())
        binding.moviesRecyclerView.layoutManager = layoutManager



        repo = MovieRepositoryImp(api= provideMovieApi())

        job = CoroutineScope(Dispatchers.IO).launch {
            val newMovieList = (repo as MovieRepositoryImp).getMovies("Inception").toMovieList() as ArrayList<Movie>
            requireActivity().runOnUiThread {
                // movieList güncellendiğinde burada işlem yapabilirsiniz
                movieList = newMovieList
                movieAdapter?.updateData(newMovieList)
                println(movieList)
            }
        }


        movieAdapter = MovieRecyclerViewAdapter(movieList ?: arrayListOf(),this@MoviesFragment)
        binding.moviesRecyclerView.adapter=movieAdapter


        //viewModel.onEvent(MoviesEvent.Search("Inception"))
        viewModel.getMovies("Inception")
        println(viewModel.state.value.movies)

        println("-----------------------")



    }

    private fun provideMovieApi(): MovieAPI{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieAPI::class.java)
    }

    override fun onItemClick(movieModel: Movie) {
        Toast.makeText(requireContext(),"Clicked on: ${movieModel.Title}",Toast.LENGTH_LONG).show()
    }

}
