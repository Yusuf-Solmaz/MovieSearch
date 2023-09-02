package com.yusuf.moviesearch.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.yusuf.moviesearch.R
import com.yusuf.moviesearch.data.remote.MovieAPI
import com.yusuf.moviesearch.data.remote.dto.MoviesDto
import com.yusuf.moviesearch.data.repository.MovieRepositoryImp
import com.yusuf.moviesearch.databinding.ActivityMainBinding
import com.yusuf.moviesearch.domain.repository.MovieRepository
import com.yusuf.moviesearch.presentation.movies.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }
}