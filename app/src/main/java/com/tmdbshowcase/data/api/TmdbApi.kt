package com.tmdbshowcase.data.api

import com.tmdbshowcase.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface TmdbApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Header("Authorization") authHeader: String): MovieResponse
}

