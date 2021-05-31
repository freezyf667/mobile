package com.example.mobileprogramming.presentation.api


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET( "discover/movie")
    fun getMovieList(@Query(value = "?api_key=25e530a18952524c31924dfd9440075f&sort_by=popularity.desc") value: String): Call<MovieListResponse>

    @GET( "movie/{id}")
    fun getMovieDetail(@Path("id") id:Int): Call<MovieDetailResponse>
}