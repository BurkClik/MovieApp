package com.burkclik.movieapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.burkclik.movieapp.model.Movie

private const val STARTING_INDEX = 1

class MovieSource(private val backend: MovieRepository) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: STARTING_INDEX
        return try {
            val movies = backend.getMovieByGenre(position).body()!!
            LoadResult.Page(
                data = movies.results,
                prevKey = if (position == STARTING_INDEX) null else position - 1,
                nextKey = if (movies.results.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}