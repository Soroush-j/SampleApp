package com.example.sjavaherian.myapp.movie.movies.network.pojo

import com.google.gson.annotations.SerializedName

data class GenreRetro(
    @SerializedName("id") var id: Int,
    @SerializedName("name") val name: String
)