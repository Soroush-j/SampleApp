package com.sjavaherian.movie.main.network.pojo

import com.sjavaherian.core.database.movies.Movie
import com.google.gson.annotations.SerializedName

data class MovieRetro(
    @SerializedName("id") val id: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("poster") val poster: String?,
    @SerializedName("year") val year: String?,
    @SerializedName("country") val country: String?,
    @SerializedName("imdb_rating") val imdbRating: String?,
    @SerializedName("genres") val genres: List<String>?,
    @SerializedName("images") val images: List<String>?
) {

    fun description(): String = "Year: $year IMDB: $imdbRating GenreRetro: $genres Country: $country"

    fun convertToMovie(): Movie {
        return Movie(
            this.id,
            this.title,
            this.poster,
            this.year,
            this.country,
            this.imdbRating,
            this.genres,
            this.images
        )
    }
}