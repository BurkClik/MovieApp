package com.burkclik.movieapp.data.remote.model

import com.burkclik.movieapp.common.BaseModel
import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String
) : BaseModel()
