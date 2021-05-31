package com.burkclik.movieapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.burkclik.movieapp.data.remote.MovieService
import com.burkclik.movieapp.data.remote.PopularMoviePagingSource
import com.burkclik.movieapp.data.remote.model.MovieResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PopularMovieRepository @Inject constructor(private val movieService: MovieService) {
    fun getPopularMovies(): Flow<PagingData<MovieResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = PopularMoviePagingSource.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PopularMoviePagingSource(movieService) }
        ).flow
    }
}