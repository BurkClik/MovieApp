package com.burkclik.movieapp.domain

import com.burkclik.movieapp.common.IMAGE_BASE_URL
import com.burkclik.movieapp.data.remote.model.Credits
import com.burkclik.movieapp.domain.model.CreditsItem
import javax.inject.Inject

class CastMapper @Inject constructor() {
    fun creditsItemList(castList: List<Credits>): List<CreditsItem> =
        castList.map {
            it.toCreditsItem()
        }

    private fun Credits.toCreditsItem() = CreditsItem(
        name = name,
        character = character,
        profilePath = IMAGE_BASE_URL + profilePath
    )
}