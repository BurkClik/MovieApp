package com.burkclik.movieapp.data

import com.burkclik.movieapp.API_KEY
import com.burkclik.movieapp.api.MovieApi
import com.burkclik.movieapp.model.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieApi: MovieApi) {
    suspend fun getTheaterMovies(): Response<MovieResponse> {
        return withContext(Dispatchers.IO) {
            movieApi.fetchTheaterMovies(API_KEY)
        }
    }

/*    suspend fun getGenres(): Response<Genres> {
        return withContext(Dispatchers.IO) {
            movieService.retrofitService.fetchGenres(API_KEY)
        }
    }*/
}