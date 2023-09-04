package com.yusuf.moviesearch.presentation.movie_detail


import com.yusuf.moviesearch.domain.model.MovieDetail

data class MovieDetailState (
    val isLoading: Boolean = false,
    val movie: MovieDetail? = null,
    val error: String = ""
    )