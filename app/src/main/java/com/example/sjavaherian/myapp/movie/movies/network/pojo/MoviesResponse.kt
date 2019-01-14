package com.example.sjavaherian.myapp.movie.movies.network.pojo

import com.google.gson.annotations.SerializedName

data class MoviesResponse(

    @SerializedName("metadata")
    val metadata: MetadataRetro,

    @SerializedName("data")
    val data: List<MovieRetro>
)