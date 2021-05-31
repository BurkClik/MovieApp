package com.burkclik.movieapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String
)
