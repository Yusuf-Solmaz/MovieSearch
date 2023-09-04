
package com.yusuf.moviesearch.presentation.movie_detail.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.yusuf.moviesearch.R
import com.yusuf.moviesearch.databinding.FragmentMovieDetailBinding
import com.yusuf.moviesearch.presentation.movie_detail.MovieDetailViewModel
import kotlinx.coroutines.launch

class MovieDetailFragment : Fragment() {

    private var fragmentBinding: FragmentMovieDetailBinding? = null
    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMovieDetailBinding.bind(view)
        fragmentBinding = binding

        arguments?.let {
            val imdbId = MovieDetailFragmentArgs.fromBundle(it).imdbId
            viewModel = ViewModelProvider(requireActivity())[MovieDetailViewModel::class.java]
            viewModel.setImdbId(imdbId)
        }

        val state = viewModel.state

        lifecycleScope.launch {
            state.collect {
                val movie = it.movie
                if (movie != null) {
                    binding.movieName.text = movie.Title
                    binding.movieCountry.text= movie.Country
                    binding.movieDirector.text= movie.Director
                    binding.movieStar.text = movie.Actors
                    binding.movieImdbRating.text = movie.imdbRating
                    binding.movieYear.text = movie.Year
                    Glide.with(requireContext())
                        .load(movie.Poster)
                        .placeholder(R.drawable.img)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(binding.movieDetailsImageView)
                }
            }
        }
    }
}
