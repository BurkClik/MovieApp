package com.burkclik.movieapp

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.burkclik.movieapp.model.Movie
import com.burkclik.movieapp.ui.movie.list.popular.PopularMovieAdapter
import com.burkclik.movieapp.ui.movie.list.theater.MovieListTheaterAdapter
import com.burkclik.movieapp.ui.movie.list.theater.MovieListTheaterDecorator

val theaterAdapter = MovieListTheaterAdapter()
val popularAdapter = PopularMovieAdapter()

@BindingAdapter("inTheater")
fun RecyclerView.inTheaterMovies(movies: List<Movie>?) {
    adapter = theaterAdapter

    if (itemDecorationCount == 0) {
        addItemDecoration(MovieListTheaterDecorator())
    }

    theaterAdapter.submitList(movies.orEmpty())
}

/*
@BindingAdapter("popularMovie","app:itemClickListener")
suspend fun RecyclerView.popularMovies(products: PagingData<Movie>,itemClickListener: (Movie) -> Unit) {
    adapter = popularAdapter

    if (itemDecorationCount == 0) {
        addItemDecoration(PopularMovieDecorator())
    }

    popularAdapter.itemClickListener = itemClickListener

    popularAdapter.submitData(products)
}*/

@BindingAdapter("app:imageUrl")
fun ImageView.imageUrl(imageUrl: String?) {
    imageUrl?.let {
        Glide.with(this)
            .load(IMAGE_BASE_URL + imageUrl)
            .into(this)
    }
}