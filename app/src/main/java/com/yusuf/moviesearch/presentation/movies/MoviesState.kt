package com.yusuf.moviesearch.presentation.movies

import com.yusuf.moviesearch.domain.model.Movie

data class MoviesState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String= "",
    val search : String = "batman"
)
