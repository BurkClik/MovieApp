package com.burkclik.movieapp

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.burkclik.movieapp.data.remote.model.Credits
import com.burkclik.movieapp.data.remote.model.Movie
import com.burkclik.movieapp.ui.movie.detail.MovieCreditsAdapter
import com.burkclik.movieapp.ui.movie.list.theater.MovieListTheaterAdapter
import com.burkclik.movieapp.ui.movie.list.theater.MovieListTheaterDecorator

val theaterAdapter = MovieListTheaterAdapter()
val castAdapter = MovieCreditsAdapter()

@BindingAdapter("inTheater")
fun RecyclerView.inTheaterMovies(movies: List<Movie>?) {
    adapter = theaterAdapter

    if (itemDecorationCount == 0) {
        addItemDecoration(MovieListTheaterDecorator())
    }
    theaterAdapter.submitList(movies.orEmpty())
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

@BindingAdapter("credits")
fun RecyclerView.credits(credits: List<Credits>?) {
    adapter = castAdapter

    if (itemDecorationCount == 0) {
        addItemDecoration(MovieListTheaterDecorator())
    }
    castAdapter.submitList(credits)
}