package com.yusuf.moviesearch.presentation.movies.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yusuf.moviesearch.R
import com.yusuf.moviesearch.databinding.FragmentMoviesBinding
import com.yusuf.moviesearch.domain.model.Movie
import com.yusuf.moviesearch.presentation.movies.MoviesEvent
import com.yusuf.moviesearch.presentation.movies.MoviesViewModel
import com.yusuf.moviesearch.presentation.movies.adapter.MovieRecyclerViewAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class MoviesFragment() : Fragment(R.layout.fragment_movies), MovieRecyclerViewAdapter.Listener {

    private var fragmentBinding: FragmentMoviesBinding? = null
    private lateinit var viewModel: MoviesViewModel
    private lateinit var movieList: MutableList<Movie>


    private var movieAdapter = MovieRecyclerViewAdapter(arrayListOf(),this)
    private var job: Job? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MoviesViewModel::class.java]

        val binding = FragmentMoviesBinding.bind(view)
        fragmentBinding = binding

        val layoutManager = LinearLayoutManager(requireContext())
        binding.moviesRecyclerView.layoutManager = layoutManager

        movieList = mutableListOf()

        movieAdapter = MovieRecyclerViewAdapter(movieList,this@MoviesFragment)
        binding.moviesRecyclerView.adapter=movieAdapter


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Kullanıcı arama sorgusunu gönderdiğinde bu metot çağrılır
                if (query == null) {
                    Toast.makeText(requireContext(),"Search Something!",Toast.LENGTH_LONG).show()
                }
                else{

                   //viewModel.onEvent(MoviesEvent.Search(query))
                    //viewModel.getMovies(query)
                    val state = viewModel.state


                    lifecycleScope.launch{
                        state.collect(){
                            val newMovieList = it.movies

                            if (newMovieList.isNotEmpty()){
                            binding.moviesRecyclerView.visibility=RecyclerView.VISIBLE
                            binding.textView.visibility=TextView.GONE
                            movieList.clear()
                            movieList.addAll(newMovieList)
                            movieAdapter.updateData(newMovieList)
                            }
                            else{
                                binding.moviesRecyclerView.visibility=RecyclerView.GONE
                                binding.textView.visibility=TextView.VISIBLE

                            }
                        }
                    }

                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Kullanıcı arama sorgusunu değiştirdiğinde bu metot çağrılır

                return true
            }
        })
    }

    override fun onItemClick(movieModel: Movie) {
        //Toast.makeText(requireContext(),"Clicked on: ${movieModel.Title}",Toast.LENGTH_LONG).show()
    }

}
