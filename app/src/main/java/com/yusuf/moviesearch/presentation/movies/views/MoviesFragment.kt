package com.yusuf.moviesearch.presentation.movies.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.yusuf.moviesearch.R
import com.yusuf.moviesearch.databinding.FragmentMoviesBinding
import com.yusuf.moviesearch.presentation.movies.MoviesViewModel
import com.yusuf.moviesearch.presentation.movies.adapter.MovieRecyclerViewAdapter
import javax.inject.Inject

class MoviesFragment @Inject constructor(
    private val recyclerAdapter: MovieRecyclerViewAdapter
) : Fragment() {

    private var fragmentBinding: FragmentMoviesBinding? = null
    private lateinit var viewModel: MoviesViewModel

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


    }

}
