package com.burkclik.movieapp.domain

import com.burkclik.movieapp.common.IMAGE_BASE_URL
import com.burkclik.movieapp.data.remote.model.Movie
import com.burkclik.movieapp.domain.model.MovieItem
import javax.inject.Inject

class MovieMapper @Inject constructor() {
    fun movieItemList(movieList: List<Movie>): List<MovieItem> =
        movieList.map {
            it.toMovieItem()
        }

    private fun Movie.toMovieItem() = MovieItem(
        id = id,
        imageUrl = IMAGE_BASE_URL + posterPath
    )
}
