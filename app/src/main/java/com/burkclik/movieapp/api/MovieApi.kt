package com.burkclik.movieapp.api

import com.burkclik.movieapp.model.Genres
import com.burkclik.movieapp.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("/3/movie/now_playing")
    suspend fun fetchTheaterMovies(@Query("api_key") apiKey: String): Response<MovieResponse>

    @GET("/3/discover/movie")
    suspend fun fetchMovieByGenre(
        @Query("api_key") apiKey: String,
        @Query("with_genres") withGenres: Int,
        @Query("sort_by") sortBy: String = "popularity.desc",
    ): Response<MovieResponse>

    @GET("/3/genre/movie/list")
    suspend fun fetchGenres(@Query("api_key") apiKey: String): Response<Genres>
}