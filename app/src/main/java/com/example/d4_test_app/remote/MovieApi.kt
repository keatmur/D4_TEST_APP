package com.example.d4_test_app.remote

import com.example.d4_test_app.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

const val CONTENT_TYPE = "Content-Type: application/json"

interface MovieApi {

    @Headers(CONTENT_TYPE)
    @GET("discover/movie")
    suspend fun getMovies(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc"

    ): MovieResponse
}