package com.burkclik.movieapp

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.burkclik.movieapp.model.Movie
import com.burkclik.movieapp.ui.movie.list.theater.MovieListTheaterAdapter
import com.burkclik.movieapp.ui.movie.list.theater.MovieListTheaterDecorator

val theaterAdapter = MovieListTheaterAdapter()

@BindingAdapter("inTheater")
fun RecyclerView.inTheaterMovies(movies: List<Movie>?) {
    adapter = theaterAdapter

    if (itemDecorationCount == 0) {
        addItemDecoration(MovieListTheaterDecorator())
    }

    theaterAdapter.submitList(movies.orEmpty())
}
