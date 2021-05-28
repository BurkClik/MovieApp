package com.burkclik.movieapp.common

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.burkclik.movieapp.R
import com.burkclik.movieapp.data.remote.model.Credits
import com.burkclik.movieapp.data.remote.model.Movie
import com.burkclik.movieapp.ui.movie.list.theater.MovieListTheaterDecorator

@BindingAdapter("inTheater")
fun RecyclerView.inTheaterMovies(movies: List<Movie>?) {
    val theaterAdapter = GenericAdapter<Movie>(R.layout.item_in_theater)
    adapter = theaterAdapter

    if (itemDecorationCount == 0) {
        addItemDecoration(MovieListTheaterDecorator())
    }
    theaterAdapter.submitList(movies.orEmpty())
}

@BindingAdapter("credits")
fun RecyclerView.credits(credits: List<Credits>?) {
    val castAdapter = GenericAdapter<Credits>(R.layout.item_cast_card)
    adapter = castAdapter

    if (itemDecorationCount == 0) {
        addItemDecoration(MovieListTheaterDecorator())
    }
    castAdapter.submitList(credits)
}

@BindingAdapter("app:imageUrl")
fun ImageView.imageUrl(imageUrl: String?) {
    imageUrl?.let {
        Glide.with(this)
            .load(IMAGE_BASE_URL + imageUrl)
            .into(this)
    }
}

@BindingAdapter("convertToString")
fun TextView.convertToString(data: Any?) {
    this.text = data.toString()
}