package com.burkclik.movieapp.domain.usecase

import com.burkclik.movieapp.common.Result
import com.burkclik.movieapp.data.MovieRepository
import com.burkclik.movieapp.domain.mapper.MovieMapper
import com.burkclik.movieapp.domain.model.MovieItem
import javax.inject.Inject

class MovieRelatedUseCase @Inject constructor(
    private val movieMapper: MovieMapper,
    private val movieRepository: MovieRepository
) {
    suspend fun movieRelated(movieId: Int): Result<List<MovieItem>?> {
        return Result.Success(movieMapper.mapFrom(movieRepository.getRelatedMovies(movieId).data!!))
    }
}