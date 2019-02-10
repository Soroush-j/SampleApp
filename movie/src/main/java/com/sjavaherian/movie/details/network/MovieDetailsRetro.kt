package com.sjavaherian.movie.details.network

import com.sjavaherian.core.database.movies.Movie
import com.google.gson.annotations.SerializedName

data class MovieDetailsRetro(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("poster") val poster: String,
    @SerializedName("year") val year: String,
    @SerializedName("rated") val rated: String,
    @SerializedName("released") val released: String,
    @SerializedName("runtime") val runtime: String,
    @SerializedName("director") val director: String,
    @SerializedName("writer") val writer: String,
    @SerializedName("actors") val actors: String,
    @SerializedName("plot") val plot: String,
    @SerializedName("country") val country: String,
    @SerializedName("awards") val awards: String,
    @SerializedName("metascore") val metascore: String,
    @SerializedName("imdb_rating") val imdbRating: String,
    @SerializedName("imdb_votes") val imdbVotes: String,
    @SerializedName("imdb_id") val imdbId: String,
    @SerializedName("type") val type: String,
    @SerializedName("genres") val genres: List<String>,
    @SerializedName("images") val images: List<String>
) {

    fun convertToMovie(): Movie {
        return Movie(
            this.id, this.title, this.poster, this.year, this.country, this.imdbRating, this.genres,
            this.images, this.rated, this.released, this.runtime, this.director, this.writer, this.actors,
            this.plot, this.awards, this.metascore, this.imdbVotes, this.imdbId, this.type, fullyLoaded = true
        )
    }

    fun convertToMovie(key: Int): Movie {
        return Movie(
            key, this.id, this.title, this.poster, this.year, this.country, this.imdbRating, this.genres,
            this.images, this.rated, this.released, this.runtime, this.director, this.writer, this.actors,
            this.plot, this.awards, this.metascore, this.imdbVotes, this.imdbId, this.type, fullyLoaded = true
        )
    }
}