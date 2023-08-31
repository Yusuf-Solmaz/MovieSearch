package com.yusuf.moviesearch.data.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yusuf.moviesearch.R
import com.yusuf.moviesearch.data.remote.MovieAPI
import com.yusuf.moviesearch.data.repository.MovieRepositoryImp
import com.yusuf.moviesearch.domain.repository.MovieRepository
import com.yusuf.moviesearch.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieApi(): MovieAPI{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepo(api: MovieAPI): MovieRepository{
        return MovieRepositoryImp(api= api)
    }


    @Singleton
    @Provides
    fun injectGlide( @ApplicationContext context: Context) = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.img)
                .error(R.drawable.ic_launcher_foreground)
        )
}