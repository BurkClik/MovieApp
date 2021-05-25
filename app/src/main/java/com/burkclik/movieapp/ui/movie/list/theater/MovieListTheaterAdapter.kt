package com.burkclik.movieapp.ui.movie.list.theater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.burkclik.movieapp.IMAGE_BASE_URL
import com.burkclik.movieapp.databinding.ItemInTheaterBinding
import com.burkclik.movieapp.model.Movie

class MovieListTheaterAdapter : ListAdapter<Movie, MovieListTheaterAdapter.TheaterViewHolder>(
    DIFF_CALLBACK
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TheaterViewHolder {
        val binding =
            ItemInTheaterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TheaterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TheaterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TheaterViewHolder(private val binding: ItemInTheaterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            Glide.with(binding.root.context)
                .load(IMAGE_BASE_URL + movie.posterPath)
                .into(binding.imageViewInTheaterMovies)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(
                oldItem: Movie,
                newItem: Movie
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Movie,
                newItem: Movie
            ) = oldItem == newItem
        }
    }
}