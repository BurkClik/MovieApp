package com.burkclik.movieapp.domain.usecase

import com.burkclik.movieapp.common.Result
import com.burkclik.movieapp.data.MovieRepository
import com.burkclik.movieapp.domain.mapper.MovieDetailMapper
import com.burkclik.movieapp.domain.model.MovieDetailItem
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieDetailMapper: MovieDetailMapper,
) {
    suspend fun movieDetail(movieId: Int): Result<MovieDetailItem> {
        return Result.Success(
            movieDetailMapper.mapFrom(
                movieRepository.getMovieDetail(
                    movieId
                ).data!!
            )
        )
    }
}