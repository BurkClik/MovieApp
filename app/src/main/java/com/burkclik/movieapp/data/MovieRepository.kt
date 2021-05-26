package com.burkclik.movieapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.burkclik.movieapp.API_KEY
import com.burkclik.movieapp.api.MovieApi
import com.burkclik.movieapp.model.Movie
import com.burkclik.movieapp.model.MovieDetail
import com.burkclik.movieapp.model.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieApi: MovieApi) {
    suspend fun getTheaterMovies(): Response<MovieResponse> {
        return withContext(Dispatchers.IO) {
            movieApi.fetchTheaterMovies(API_KEY)
        }
    }

    suspend fun getMovieDetail(movieId: Int): MovieDetail {
        return withContext(Dispatchers.IO) {
            movieApi.fetchMovieDetail(movieId = movieId)
        }
    }

    fun getPopularMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = PopularMoviePagingSource.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PopularMoviePagingSource(movieApi) }
        ).flow
    }
}