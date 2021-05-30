package com.burkclik.movieapp.domain

import android.util.Log
import com.burkclik.movieapp.common.Result
import com.burkclik.movieapp.data.MovieRepository
import com.burkclik.movieapp.domain.model.CreditsItem
import com.burkclik.movieapp.domain.model.MovieDetailItem
import com.burkclik.movieapp.domain.model.MovieItem
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper,
    private val castMapper: CastMapper,
    private val movieDetailMapper: MovieDetailMapper,
) {
    suspend fun inTheaterMovie(): List<MovieItem> {
        return movieMapper.movieItemList(movieRepository.getTheaterMovies().results)
    }

    suspend fun credits(movieId: Int): List<CreditsItem> {
        return castMapper.creditsItemList(movieRepository.getCreditsMovie(movieId).cast)
    }

    suspend fun relatedMovies(movieId: Int): Result<List<MovieItem>?> {
        return try {
            Log.i("Burak", "UseCase -> ${Thread.currentThread().name}")
            val result = movieRepository.getRelatedMovies(movieId)
            if (result.isSuccessful) {
                val movies = result.body()?.results
                Result.Success(movieMapper.movieItemList(movies!!))
            } else {
                Result.Error("error code -> ${result.code()}")
            }
        } catch (exception: Exception) {
            Log.i("Burak", "Girdim")
            Result.Error("exception -> ${exception.message}")
        }
    }

    suspend fun movieDetail(movieId: Int): Result<MovieDetailItem> {
        return try {
            val result = movieRepository.getMovieDetail(movieId)
            if (result.isSuccessful) {
                val movie = result.body()
                Result.Success(movieDetailMapper.toMovieDetailItem(movie))
            } else {
                Result.Error("error code -> ${result.code()}")
            }
        } catch (exception: Exception) {
            Result.Error(exception.message.toString())
        }
    }
}