package com.yusuf.moviesearch.presentation.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.yusuf.moviesearch.R
import com.yusuf.moviesearch.databinding.MovieRecyclerRowBinding
import com.yusuf.moviesearch.domain.model.Movie
import com.yusuf.moviesearch.presentation.movies.views.MoviesFragment
import com.yusuf.moviesearch.presentation.movies.views.MoviesFragmentDirections
import java.security.AccessController.getContext

class MovieRecyclerViewAdapter(private val movieList: ArrayList<Movie>, listener: Listener) :  RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieHolder>(){

    interface Listener {
        fun onItemClick(movieModel: Movie)
    }

    class MovieHolder(val binding: MovieRecyclerRowBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val itemBinding = MovieRecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return movieList.count()
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {

        holder.itemView.setOnClickListener{
            val action = MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment(movieList.get(position).imdbID)

            Navigation.findNavController(it).navigate(action)
        }

        Glide.with(holder.itemView.context)
            .load(movieList[position].Poster) // URL'yi yüklemek istediğiniz yer
            .placeholder(R.drawable.img) // Resim yüklenene kadar gösterilecek yerel bir placeholder
            .transition(DrawableTransitionOptions.withCrossFade()) // İyi bir geçiş efekti ekler
            .into(holder.binding.recyclerViewRowImage) // Resmi göstermek istediğiniz ImageView
        holder.binding.MovieYear.text = movieList.get(position).Year
        holder.binding.movieName.text = movieList.get(position).Title

    }

    fun updateData(newMovieList: ArrayList<Movie>) {
        movieList.clear()
        movieList.addAll(newMovieList)
        notifyDataSetChanged()
    }



}