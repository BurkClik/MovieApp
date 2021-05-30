package com.burkclik.movieapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class Credits(
    @SerializedName("name")
    val name: String,
    @SerializedName("character")
    val character: String,
    @SerializedName("profile_path")
    val profilePath: String
)
