package com.tmdbshowcase.data.repository

import android.util.Log
import coil.network.HttpException
import com.tmdbshowcase.data.api.TmdbApi
import com.tmdbshowcase.data.model.MovieResponse
import okio.IOException

class MovieRepository(private val api: TmdbApi) {
    suspend fun getPopularMovies(authHeader: String): MovieResponse {
        val emptyMovieResponse = MovieResponse(0, emptyList(), 0, 0)

        return try {
            api.getPopularMovies(authHeader)
        } catch (e: HttpException) {
            Log.e("MovieRepository", "HttpException occurred: $e.")
            return emptyMovieResponse
        } catch (e: IOException) {
            Log.e("MovieRepository", "IOException occurred: $e.")
            return emptyMovieResponse
        } catch (e: Exception) {
            Log.e("MovieRepository", "Unknown exception occurred: $e.")
            return emptyMovieResponse
        }
    }

    suspend fun getTopRatedMovies(authHeader: String): MovieResponse {
        val emptyMovieResponse = MovieResponse(0, emptyList(), 0, 0)

        return try {
            api.getTopRatedMovies(authHeader)
        } catch (e: HttpException) {
            Log.e("MovieRepository", "HttpException occurred: $e.")
            return emptyMovieResponse
        } catch (e: IOException) {
            Log.e("MovieRepository", "IOException occurred: $e.")
            return emptyMovieResponse
        } catch (e: Exception) {
            Log.e("MovieRepository", "Unknown exception occurred: $e.")
            return emptyMovieResponse
        }
    }

    suspend fun getUpcomingMovies(authHeader: String): MovieResponse {
        val emptyMovieResponse = MovieResponse(0, emptyList(), 0, 0)

        return try {
            api.getUpcomingMovies(authHeader)
        } catch (e: HttpException) {
            Log.e("MovieRepository", "HttpException occurred: $e.")
            return emptyMovieResponse
        } catch (e: IOException) {
            Log.e("MovieRepository", "IOException occurred: $e.")
            return emptyMovieResponse
        } catch (e: Exception) {
            Log.e("MovieRepository", "Unknown exception occurred: $e.")
            return emptyMovieResponse
        }
    }
}