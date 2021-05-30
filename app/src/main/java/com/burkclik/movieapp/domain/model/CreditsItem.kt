package com.burkclik.movieapp.domain.model

import com.burkclik.movieapp.common.base.BaseModel

data class CreditsItem(
    val name: String,
    val character: String,
    val profilePath: String,
) : BaseModel()