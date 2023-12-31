package com.yusuf.moviesearch.presentation.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.yusuf.moviesearch.R
import com.yusuf.moviesearch.databinding.MovieRecyclerRowBinding
import com.yusuf.moviesearch.domain.model.Movie
import com.yusuf.moviesearch.presentation.movies.views.MoviesFragmentDirections

class MovieRecyclerViewAdapter(private val movieList: MutableList<Movie>, listener: Listener) :  RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieHolder>(){

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
            .load(movieList[position].Poster)
            .placeholder(R.drawable.img)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.binding.recyclerViewRowImage)
        holder.binding.MovieYear.text = movieList.get(position).Year
        holder.binding.movieName.text = movieList.get(position).Title

    }

    fun updateData(newMovieList: List<Movie>) {
        movieList.clear()
        movieList.addAll(newMovieList)
        notifyDataSetChanged()
    }



}