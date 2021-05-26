package com.burkclik.movieapp.api

import com.burkclik.movieapp.API_KEY
import com.burkclik.movieapp.model.CastResponse
import com.burkclik.movieapp.model.Genres
import com.burkclik.movieapp.model.MovieDetail
import com.burkclik.movieapp.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    // TODO: Check endpoint. If there are similar urls, make efficient requestes.

    @GET("/3/movie/now_playing")
    suspend fun fetchTheaterMovies(@Query("api_key") apiKey: String): Response<MovieResponse>

    @GET("/3/discover/movie")
    suspend fun fetchMovieByGenre(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("with_genres") withGenres: Int = 36,
        @Query("sort_by") sortBy: String = "popularity.desc",
    ): MovieResponse

    @GET("/3/genre/movie/list")
    suspend fun fetchGenres(@Query("api_key") apiKey: String): Response<Genres>

    @GET("/3/movie/{movie_id}/similar")
    suspend fun fetchRelatedMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieResponse

    @GET("/3/movie/{movie_id}/credits")
    suspend fun fetchMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): CastResponse

    @GET("/3/movie/{movie_id}")
    suspend fun fetchMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ): MovieDetail
}