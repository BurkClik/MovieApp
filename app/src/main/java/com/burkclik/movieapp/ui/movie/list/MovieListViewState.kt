package com.burkclik.movieapp.ui.movie.list

import com.burkclik.movieapp.model.Movie

class MovieListViewState(val movie: Movie) {
    fun isMovieVisible() = movie.posterPath.isEmpty()
}