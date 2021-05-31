package com.burkclik.movieapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class CastResponse(
    val cast: List<CreditsResponse>
) {
    data class CreditsResponse(
        @SerializedName("name")
        val name: String,
        @SerializedName("character")
        val character: String,
        @SerializedName("profile_path")
        val profilePath: String
    )
}