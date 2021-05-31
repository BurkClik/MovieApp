package com.burkclik.movieapp.data.remote

import com.burkclik.movieapp.common.API_KEY
import com.burkclik.movieapp.data.remote.model.CastResponse
import com.burkclik.movieapp.data.remote.model.GenresResponse
import com.burkclik.movieapp.data.remote.model.MovieDetailResponse
import com.burkclik.movieapp.data.remote.model.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("/3/movie/now_playing")
    suspend fun fetchTheaterMovies(@Query("api_key") apiKey: String = API_KEY): Response<MovieListResponse>

    @GET("/3/discover/movie")
    suspend fun fetchMovieByGenre(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("with_genres") withGenres: Int = 36,
        @Query("sort_by") sortBy: String = "popularity.desc",
    ): MovieListResponse

    @GET("/3/genre/movie/list")
    suspend fun fetchGenres(@Query("api_key") apiKey: String): Response<GenresResponse>

    @GET("/3/movie/{movie_id}/similar")
    suspend fun fetchRelatedMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): Response<MovieListResponse>

    @GET("/3/movie/{movie_id}/credits")
    suspend fun fetchMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): Response<CastResponse>

    @GET("/3/movie/{movie_id}")
    suspend fun fetchMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ): Response<MovieDetailResponse>
}