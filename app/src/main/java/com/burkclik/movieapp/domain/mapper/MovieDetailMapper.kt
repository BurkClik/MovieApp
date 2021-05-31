package com.burkclik.movieapp.domain.mapper

import com.burkclik.movieapp.common.HIGH_RES_IMAGE_URL
import com.burkclik.movieapp.common.IMAGE_BASE_URL
import com.burkclik.movieapp.common.Mapper
import com.burkclik.movieapp.data.remote.model.MovieDetailResponse
import com.burkclik.movieapp.domain.model.MovieDetailItem
import javax.inject.Inject

class MovieDetailMapper @Inject constructor() : Mapper<MovieDetailResponse, MovieDetailItem> {
    override fun mapFrom(type: MovieDetailResponse): MovieDetailItem {
        return type.run {
            MovieDetailItem(
                id = this.id,
                overview = this.overview,
                runtime = this.runtime.toString(),
                title = this.title,
                tagline = this.tagline,
                voteAverage = this.voteAverage.toString(),
                posterPath = IMAGE_BASE_URL + this.posterPath,
                backdropPath = HIGH_RES_IMAGE_URL + this.backdropPath,
                revenue = this.revenue.toString()
            )
        }
    }
}