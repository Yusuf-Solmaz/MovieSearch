package com.yusuf.moviesearch.data.remote

import com.yusuf.moviesearch.data.remote.dto.MovieDetailDto
import com.yusuf.moviesearch.data.remote.dto.MoviesDto
import com.yusuf.moviesearch.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    //Requests
    //https://www.omdbapi.com/?apikey=95a2bdea&s=inception
    //https://www.omdbapi.com/?apikey=95a2bdea&i=tt1375666


    //Base URL'den sonra direkt istek atılıyor, başka bir parametre yok.https://www.omdbapi.com/movies/?apikey=95a2bdea&s=inception gibi. O yüzden nokta konuldu.
    @GET(".")
    suspend fun getMovies(
        @Query("s") searchString: String,
        @Query("apikey")  apiKey: String = API_KEY
    ) : MoviesDto

    @GET(".")
    suspend fun getMovieDetail(
        @Query("i") imdbId: String,
        @Query("apikey")  apiKey: String = API_KEY
    ) : MovieDetailDto

}