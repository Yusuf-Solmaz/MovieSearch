package com.yusuf.moviesearch.presentation.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.yusuf.moviesearch.data.remote.MovieAPI
import com.yusuf.moviesearch.presentation.movies.views.MoviesFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class FragmentFactory @Inject constructor(
private val api: MovieAPI
): FragmentFactory() {


    @OptIn(ExperimentalCoroutinesApi::class)
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when(className){
            //MoviesFragment::class.java.name -> MoviesFragment(api)

            else -> super.instantiate(classLoader, className)
        }

    }
}