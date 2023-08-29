package com.yusuf.moviesearch.domain.use_case.get_movie_detail

import com.yusuf.moviesearch.data.remote.dto.toMovieDetail
import com.yusuf.moviesearch.data.remote.dto.toMovieList
import com.yusuf.moviesearch.domain.model.Movie
import com.yusuf.moviesearch.domain.model.MovieDetail
import com.yusuf.moviesearch.domain.repository.MovieRepository
import com.yusuf.moviesearch.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repo: MovieRepository
){

    fun executeGetMovieDetail(imdb: String):  Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading())
            val movieDetail = repo.getMoviesDetail(imdbId = imdb)
            emit(Resource.Success(movieDetail.toMovieDetail()))

        }
        catch (e: IOError){
            emit(Resource.Error(message = "Internet Connection Not Found!"))
        }
        catch (e: Exception){
            emit(Resource.Error(message= e.localizedMessage ?: "Unknown Error!"))
        }
    }
}