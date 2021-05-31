package com.burkclik.movieapp.common

interface Mapper<R, D> {
    fun mapFrom(type: R): D
}