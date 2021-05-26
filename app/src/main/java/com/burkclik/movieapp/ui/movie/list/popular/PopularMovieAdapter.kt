package com.burkclik.movieapp.ui.movie.list.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.burkclik.movieapp.IMAGE_BASE_URL
import com.burkclik.movieapp.databinding.ItemPopularMoviesBinding
import com.burkclik.movieapp.model.Movie

class PopularMovieAdapter :
    PagingDataAdapter<Movie, PopularMovieAdapter.GenreViewHolder>(DIFF_CALLBACK) {

    var itemClickListener: (Movie) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val binding =
            ItemPopularMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    class GenreViewHolder(
        private val binding: ItemPopularMoviesBinding,
        private val itemClickListener: (Movie) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            Glide.with(binding.root.context)
                .load(IMAGE_BASE_URL + movie.posterPath)
                .into(binding.imageViewPopularItem)

            binding.imageViewPopularItem.setOnClickListener {
                itemClickListener(movie)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
        }
    }
}