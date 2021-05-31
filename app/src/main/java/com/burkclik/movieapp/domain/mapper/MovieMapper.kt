package com.burkclik.movieapp.domain.mapper

import com.burkclik.movieapp.common.IMAGE_BASE_URL
import com.burkclik.movieapp.common.Mapper
import com.burkclik.movieapp.data.remote.model.MovieListResponse
import com.burkclik.movieapp.domain.model.MovieItem
import javax.inject.Inject

class MovieMapper @Inject constructor() : Mapper<MovieListResponse, List<MovieItem>> {
    override fun mapFrom(type: MovieListResponse): List<MovieItem> {
        return type.results.map {
            MovieItem(
                id = it.id,
                imageUrl = IMAGE_BASE_URL + it.posterPath
            )
        }
    }
}
