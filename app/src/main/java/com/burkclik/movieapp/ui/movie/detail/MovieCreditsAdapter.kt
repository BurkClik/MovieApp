package com.burkclik.movieapp.ui.movie.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.burkclik.movieapp.IMAGE_BASE_URL
import com.burkclik.movieapp.databinding.ItemCastCardBinding
import com.burkclik.movieapp.model.Credits

class MovieCreditsAdapter : ListAdapter<Credits, MovieCreditsAdapter.MovieCreditViewHolder>(
    DIFF_UTIL
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCreditViewHolder {
        val binding =
            ItemCastCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieCreditViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieCreditViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MovieCreditViewHolder(private val binding: ItemCastCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(credits: Credits) {
            Glide.with(binding.root.context)
                .load(IMAGE_BASE_URL + credits.profilePath)
                .circleCrop()
                .into(binding.imageViewCastPhoto)

            binding.textViewCastName.text = credits.name
            binding.textViewCastMovieName.text = credits.character
        }
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Credits>() {
            override fun areItemsTheSame(oldItem: Credits, newItem: Credits) =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Credits, newItem: Credits) = oldItem == newItem
        }
    }
}