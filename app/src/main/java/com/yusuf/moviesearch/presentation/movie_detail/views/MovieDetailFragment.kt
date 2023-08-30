package com.yusuf.moviesearch.presentation.movie_detail.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.yusuf.moviesearch.R
import com.yusuf.moviesearch.databinding.FragmentMovieDetailBinding
import com.yusuf.moviesearch.databinding.FragmentMoviesBinding
import com.yusuf.moviesearch.presentation.movie_detail.MovieDetailViewModel

class MovieDetailFragment : Fragment() {

    private var fragmentBinding: FragmentMovieDetailBinding? = null

    private lateinit var viewModel : MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MovieDetailViewModel::class.java]

        val binding = FragmentMovieDetailBinding.bind(view)
        fragmentBinding = binding


    }

}