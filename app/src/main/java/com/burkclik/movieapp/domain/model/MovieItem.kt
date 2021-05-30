package com.burkclik.movieapp.domain.model

import com.burkclik.movieapp.common.base.BaseModel

data class MovieItem(
    val id: Int,
    val imageUrl: String,
) : BaseModel()
