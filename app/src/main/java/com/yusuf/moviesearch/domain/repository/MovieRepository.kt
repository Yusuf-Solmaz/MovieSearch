package com.yusuf.moviesearch.domain.repository

import com.yusuf.moviesearch.data.remote.dto.MovieDetailDto
import com.yusuf.moviesearch.data.remote.dto.MoviesDto

interface MovieRepository {

    suspend fun getMovies (searchString: String): MoviesDto

    suspend fun getMoviesDetail (imdbId: String): MovieDetailDto
}