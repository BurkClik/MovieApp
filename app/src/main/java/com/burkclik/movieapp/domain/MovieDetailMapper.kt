package com.burkclik.movieapp.domain

import com.burkclik.movieapp.common.HIGH_RES_IMAGE_URL
import com.burkclik.movieapp.common.IMAGE_BASE_URL
import com.burkclik.movieapp.data.remote.model.MovieDetail
import com.burkclik.movieapp.domain.model.MovieDetailItem
import javax.inject.Inject

class MovieDetailMapper @Inject constructor() {
    fun toMovieDetailItem(movie: MovieDetail?) = MovieDetailItem(
        id = movie!!.id,
        overview = movie?.overview,
        runtime = movie?.runtime.toString(),
        title = movie!!.title,
        tagline = movie?.tagline,
        voteAverage = movie?.voteAverage.toString(),
        posterPath = IMAGE_BASE_URL + movie?.posterPath,
        backdropPath = HIGH_RES_IMAGE_URL + movie?.backdropPath,
        revenue = movie?.revenue.toString()
    )
}