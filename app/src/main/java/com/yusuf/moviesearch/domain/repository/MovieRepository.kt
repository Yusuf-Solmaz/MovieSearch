package com.yusuf.moviesearch.domain.repository

import com.yusuf.moviesearch.data.remote.dto.MovieDetailDto
import com.yusuf.moviesearch.data.remote.dto.MoviesDto

interface MovieRepository {

    suspend fun getMovies (search: String): MoviesDto

    suspend fun getMoviesDetail (imdbId: String): MovieDetailDto
}