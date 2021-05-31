package com.example.mobileprogramming.presentation.api

import com.example.mobileprogramming.presentation.list.Movie

data class MovieListResponse(
    val results: List<Movie>
)