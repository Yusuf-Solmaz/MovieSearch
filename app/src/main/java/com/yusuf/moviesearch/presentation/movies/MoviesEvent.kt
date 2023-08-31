package com.yusuf.moviesearch.presentation.movies

sealed class MoviesEvent{

    data class Search(val search: String): MoviesEvent()
}
