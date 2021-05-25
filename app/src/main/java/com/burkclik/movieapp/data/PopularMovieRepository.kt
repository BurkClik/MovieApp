package com.burkclik.movieapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.burkclik.movieapp.api.MovieApi
import com.burkclik.movieapp.data.PopularMoviePagingSource.Companion.NETWORK_PAGE_SIZE
import com.burkclik.movieapp.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PopularMovieRepository @Inject constructor(private val movieApi: MovieApi) {
    fun getPopularMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PopularMoviePagingSource(movieApi) }
        ).flow
    }
}