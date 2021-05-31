package com.burkclik.movieapp.domain.usecase

import com.burkclik.movieapp.common.Result
import com.burkclik.movieapp.data.MovieRepository
import com.burkclik.movieapp.domain.mapper.CastMapper
import com.burkclik.movieapp.domain.model.CreditsItem
import javax.inject.Inject

class CastUseCase @Inject constructor(
    private val castMapper: CastMapper,
    private val movieRepository: MovieRepository
) {
    suspend fun credits(movieId: Int): Result<List<CreditsItem>?> {
        return Result.Success(castMapper.mapFrom(movieRepository.getCreditsMovie(movieId).data!!))
    }
}