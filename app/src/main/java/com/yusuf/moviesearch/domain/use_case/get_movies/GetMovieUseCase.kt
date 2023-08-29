package com.yusuf.moviesearch.domain.use_case.get_movies

import android.net.http.HttpException
import com.yusuf.moviesearch.data.remote.dto.toMovieList
import com.yusuf.moviesearch.domain.model.Movie
import com.yusuf.moviesearch.domain.repository.MovieRepository
import com.yusuf.moviesearch.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repo: MovieRepository
) {

    fun executeGetMovies(searchString: String): Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val movieList = repo.getMovies(searchString = searchString)

            if(movieList.Response.equals("True") ){
                emit(Resource.Success(movieList.toMovieList()))
            }
            else{
                emit(Resource.Error("Movie Not Found!"))
            }
        }
        catch (e: IOError){
            emit(Resource.Error(message = "Internet Connection Not Found!"))
        }
        catch (e: Exception){
            emit(Resource.Error(message= e.localizedMessage ?: "Unknown Error!"))
        }
    }

}