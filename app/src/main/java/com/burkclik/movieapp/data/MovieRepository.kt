package com.burkclik.movieapp.data

import com.burkclik.movieapp.API_KEY
import com.burkclik.movieapp.api.MovieService
import com.burkclik.movieapp.model.Genres
import com.burkclik.movieapp.model.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class MovieRepository(private val movieService: MovieService) {
    suspend fun getTheaterMovies(): Response<MovieResponse> {
        return withContext(Dispatchers.IO) {
            movieService.retrofitService.fetchTheaterMovies(API_KEY)
        }
    }

    suspend fun getGenres(): Response<Genres> {
        return withContext(Dispatchers.IO) {
            movieService.retrofitService.fetchGenres(API_KEY)
        }
    }
}