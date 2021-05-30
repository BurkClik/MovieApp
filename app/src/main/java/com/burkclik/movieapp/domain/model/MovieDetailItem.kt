package com.burkclik.movieapp.domain.model

data class MovieDetailItem(
    val id: Int,
    val overview: String?,
    val runtime: String?,
    val title: String,
    val tagline: String?,
    val voteAverage: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val revenue: String?,
)
