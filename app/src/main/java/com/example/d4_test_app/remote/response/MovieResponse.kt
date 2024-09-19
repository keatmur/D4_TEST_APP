package com.example.d4_test_app.remote.response


data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)



