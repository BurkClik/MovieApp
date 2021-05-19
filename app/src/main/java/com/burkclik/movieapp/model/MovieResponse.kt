package com.burkclik.movieapp.model

data class MovieResponse(
    val page: Int,
    val results: List<Movie>
) {
    data class Movie(
        val id: Int,
        val poster_path: String,
        val title: String
    )
}
