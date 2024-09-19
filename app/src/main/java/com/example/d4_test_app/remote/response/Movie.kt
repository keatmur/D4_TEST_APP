package com.example.d4_test_app.remote.response


data class Movie(
    val id: Int,
    val overview: String,
    val poster_path: String?,
    val title: String,
    val vote_average: Double
)

