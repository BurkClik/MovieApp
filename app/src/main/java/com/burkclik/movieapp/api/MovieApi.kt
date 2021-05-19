package com.burkclik.movieapp.api

import com.burkclik.movieapp.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("/3/movie/now_playing")
    suspend fun fetchTheaterMovies(@Query("api_key") apiKey: String): Response<MovieResponse>
}