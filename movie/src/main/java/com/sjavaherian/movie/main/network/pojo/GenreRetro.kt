package com.sjavaherian.movie.main.network.pojo

import com.sjavaherian.core.database.movies.Genre
import com.google.gson.annotations.SerializedName

data class GenreRetro(
    @SerializedName("id") var id: Int,
    @SerializedName("name") val name: String
) {
    fun convertToGenre(): Genre {
        return Genre(null, this.id, this.name)
    }
}