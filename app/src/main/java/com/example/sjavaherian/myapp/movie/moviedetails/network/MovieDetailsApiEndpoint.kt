package com.example.sjavaherian.myapp.movie.moviedetails.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailsApiEndpoint {

    @GET("movies/{movie_id}")
    fun getMovieDetailsById(@Path("movie_id") id: Int): Call<MovieDetailsRetro>

    companion object {
        const val MOVIE_DETAILS_ENDPOINT = "MOVIE_DETAILS_ENDPOINT"
    }
}