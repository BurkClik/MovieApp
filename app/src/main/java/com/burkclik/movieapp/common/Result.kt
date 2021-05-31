package com.burkclik.movieapp.common

sealed class Result<T>(
    val data: T? = null,
    val message: String? = null,
    val status: Int? = null
) {
    class Success<T>(data: T, status: Int? = null) : Result<T>(data, status = status)
    class Error<T>(message: String, data: T? = null, status: Int? = null) :
        Result<T>(data, message, status)

    class Loading<T> : Result<T>()
}