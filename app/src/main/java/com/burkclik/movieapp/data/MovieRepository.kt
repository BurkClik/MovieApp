package com.burkclik.movieapp.data

import com.burkclik.movieapp.common.Result
import com.burkclik.movieapp.data.remote.MovieDataSource
import com.burkclik.movieapp.data.remote.model.CastResponse
import com.burkclik.movieapp.data.remote.model.MovieDetailResponse
import com.burkclik.movieapp.data.remote.model.MovieListResponse
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieDataSource: MovieDataSource,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun getTheaterMovies(): Result<MovieListResponse?> {
        return try {
            val result = movieDataSource.getInTheaterMovies()
            if (result.isSuccessful) {
                val movies = result.body()
                Result.Success(movies)
            } else {
                Result.Error(result.code().toString())
            }
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    suspend fun getMovieDetail(movieId: Int): Result<MovieDetailResponse?> {
        return try {
            val result = movieDataSource.getMovieDetail(movieId)
            if (result.isSuccessful) {
                val movie = result.body()
                Result.Success(movie)
            } else {
                Result.Error("Unexpected Error", status = result.code())
            }
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    suspend fun getRelatedMovies(movieId: Int): Result<MovieListResponse?> {
        return try {
            val result = movieDataSource.getRelatedMovies(movieId)
            if (result.isSuccessful) {
                val movies = result.body()
                Result.Success(movies)
            } else {
                Result.Error(result.code().toString())
            }
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    suspend fun getCreditsMovie(movieId: Int): Result<CastResponse?> {
        return try {
            val result = movieDataSource.getMovieCredits(movieId)
            if (result.isSuccessful) {
                val credits = result.body()
                Result.Success(credits)
            } else {
                Result.Error(result.code().toString())
            }
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }
}