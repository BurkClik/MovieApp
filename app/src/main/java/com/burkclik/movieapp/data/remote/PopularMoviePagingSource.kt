package com.burkclik.movieapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.burkclik.movieapp.common.MovieApi
import com.burkclik.movieapp.data.remote.model.Movie
import retrofit2.HttpException
import java.io.IOException


class PopularMoviePagingSource(private val movieApi: MovieApi) :
    PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: STARTING_INDEX
        return try {
            val response =
                movieApi.fetchMovieByGenre(position)
            val movies = response.results
            val nextKey = if (movies.isEmpty()) {
                null
            } else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = movies,
                prevKey = if (position == STARTING_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    companion object {
        const val STARTING_INDEX = 1
        const val NETWORK_PAGE_SIZE = 20
    }
}