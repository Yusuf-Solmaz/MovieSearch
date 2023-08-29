package com.yusuf.moviesearch.data.repository

import com.yusuf.moviesearch.data.remote.MovieAPI
import com.yusuf.moviesearch.data.remote.dto.MovieDetailDto
import com.yusuf.moviesearch.data.remote.dto.MoviesDto
import com.yusuf.moviesearch.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val api: MovieAPI
) : MovieRepository {

    override suspend fun getMovies(searchString: String): MoviesDto {
        return api.getMovies(searchString = searchString)
    }

    override suspend fun getMoviesDetail(imdbId: String): MovieDetailDto {
       return api.getMovieDetail(imdbId = imdbId)
    }
}