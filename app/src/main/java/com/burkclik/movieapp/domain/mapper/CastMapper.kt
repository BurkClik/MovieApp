package com.burkclik.movieapp.domain.mapper

import com.burkclik.movieapp.common.IMAGE_BASE_URL
import com.burkclik.movieapp.common.Mapper
import com.burkclik.movieapp.data.remote.model.CastResponse
import com.burkclik.movieapp.domain.model.CreditsItem
import javax.inject.Inject

class CastMapper @Inject constructor() : Mapper<CastResponse, List<CreditsItem>> {
    override fun mapFrom(type: CastResponse): List<CreditsItem> {
        return type.cast.map {
            CreditsItem(
                character = it.character,
                name = it.name,
                profilePath = IMAGE_BASE_URL + it.profilePath
            )
        }
    }
}