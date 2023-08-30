package com.yusuf.moviesearch.presentation.movie_detail

import androidx.lifecycle.ViewModel
import com.yusuf.moviesearch.domain.use_case.get_movie_detail.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailUseCase
): ViewModel() {
}