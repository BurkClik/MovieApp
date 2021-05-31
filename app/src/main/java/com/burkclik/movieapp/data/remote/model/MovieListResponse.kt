package com.burkclik.movieapp.data.remote.model

data class MovieListResponse(
    val page: Int,
    val results: List<MovieResponse>
)
