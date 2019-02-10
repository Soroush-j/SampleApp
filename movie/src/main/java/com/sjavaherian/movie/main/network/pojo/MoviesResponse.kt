package com.sjavaherian.movie.main.network.pojo

import com.google.gson.annotations.SerializedName
import com.sjavaherian.movie.main.network.pojo.MetadataRetro
import com.sjavaherian.movie.main.network.pojo.MovieRetro

data class MoviesResponse(

    @SerializedName("metadata")
    val metadata: MetadataRetro,

    @SerializedName("data")
    val data: List<MovieRetro>
)