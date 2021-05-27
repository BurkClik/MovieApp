package com.burkclik.movieapp.data.remote.model

data class MovieResponse(
    val page: Int,
    val results: List<Movie>
)
