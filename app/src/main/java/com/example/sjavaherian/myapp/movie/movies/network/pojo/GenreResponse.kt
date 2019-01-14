package com.example.sjavaherian.myapp.movie.movies.network.pojo

import com.google.gson.annotations.SerializedName

data class GenreResponse(@SerializedName("data") val movies: List<MovieRetro>)