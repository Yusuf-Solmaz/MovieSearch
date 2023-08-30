package com.yusuf.moviesearch.presentation.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class FragmentFactory @Inject constructor(

): FragmentFactory() {


    @OptIn(ExperimentalCoroutinesApi::class)
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when(className){

            else -> super.instantiate(classLoader, className)
        }

    }
}