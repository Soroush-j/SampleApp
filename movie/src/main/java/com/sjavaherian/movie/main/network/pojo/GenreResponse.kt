package com.sjavaherian.movie.main.network.pojo

import com.google.gson.annotations.SerializedName

data class GenreResponse(@SerializedName("data") val movies: List<MovieRetro>)