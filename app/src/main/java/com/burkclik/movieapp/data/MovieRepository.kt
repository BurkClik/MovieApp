package com.burkclik.movieapp.data

import android.util.Log
import com.burkclik.movieapp.data.remote.MovieDataSource
import com.burkclik.movieapp.data.remote.model.CastResponse
import com.burkclik.movieapp.data.remote.model.MovieDetail
import com.burkclik.movieapp.data.remote.model.MovieResponse
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieDataSource: MovieDataSource,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun getTheaterMovies(): MovieResponse {
        return movieDataSource.getInTheaterMovies()
    }

    suspend fun getMovieDetail(movieId: Int): Response<MovieDetail> {
        return movieDataSource.getMovieDetail(movieId)
    }

    suspend fun getRelatedMovies(movieId: Int): Response<MovieResponse> {
        Log.i("Burak", "Repository -> ${Thread.currentThread().name}")
        return movieDataSource.getRelatedMovies(movieId)
    }

    suspend fun getCreditsMovie(movieId: Int): CastResponse {
        return movieDataSource.getMovieCredits(movieId)
    }
}