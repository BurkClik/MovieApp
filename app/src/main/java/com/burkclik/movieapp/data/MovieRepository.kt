package com.burkclik.movieapp.data

import com.burkclik.movieapp.API_KEY
import com.burkclik.movieapp.api.MovieService
import com.burkclik.movieapp.model.Genres
import com.burkclik.movieapp.model.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class MovieRepository {
    suspend fun getTheaterMovies(): Response<MovieResponse> {
        return withContext(Dispatchers.IO) {
            MovieService.retrofitService.fetchTheaterMovies(API_KEY)
        }
    }

    suspend fun getMovieByGenre(): Response<MovieResponse> {
        return withContext(Dispatchers.IO) {
            MovieService.retrofitService.fetchMovieByGenre(API_KEY, 36)
        }
    }

    suspend fun getGenres(): Response<Genres> {
        return withContext(Dispatchers.IO) {
            MovieService.retrofitService.fetchGenres(API_KEY)
        }
    }
}