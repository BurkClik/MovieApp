package com.burkclik.movieapp.data.remote

import javax.inject.Inject

// TODO: Check representation for right way
class MovieDataSource @Inject constructor(private val movieService: MovieService) {
    suspend fun getInTheaterMovies() = movieService.fetchTheaterMovies()

    suspend fun getRelatedMovies(movieId: Int) = movieService.fetchRelatedMovies(movieId)

    suspend fun getMovieCredits(movieId: Int) = movieService.fetchMovieCredits(movieId)

    suspend fun getMovieDetail(movieId: Int) = movieService.fetchMovieDetail(movieId)
}