package com.example.mobileprogramming.presentation.api

data class MovieDetailResponse(
    val name: String,
    val types:List<MovieSlot>
)

data class MovieSlot(
    val slot: Int,
    val type: MovieType
)

data class MovieType(
    val name: String,
    val url: String
)