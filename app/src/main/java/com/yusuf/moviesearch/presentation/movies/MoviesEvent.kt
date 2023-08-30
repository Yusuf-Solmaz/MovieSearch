package com.yusuf.moviesearch.presentation.movies

sealed class MoviesEvent{

    data class Search(val searchString: String): MoviesEvent()
}
