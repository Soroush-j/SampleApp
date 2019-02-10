package com.sjavaherian.core.database.movies

import android.arch.persistence.room.*

@Entity(tableName = "movie",indices = [Index(value = ["title"])])
data class Movie(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowid") val key: Int?,

    @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "poster") val poster: String?,
    @ColumnInfo(name = "year") val year: String?,
    @ColumnInfo(name = "country") val country: String?,
    @ColumnInfo(name = "imdb_rating") val imdbRating: String?,
    @ColumnInfo(name = "genres") val genres: List<String>?,
    @ColumnInfo(name = "images") val images: List<String>?,
    @ColumnInfo(name = "rated") val rated: String? = "",
    @ColumnInfo(name = "released") val released: String? = "",
    @ColumnInfo(name = "runtime") val runtime: String? = "",
    @ColumnInfo(name = "director") val director: String? = "",
    @ColumnInfo(name = "writer") val writer: String? = "",
    @ColumnInfo(name = "actors") val actors: String? = "",
    @ColumnInfo(name = "plot") val plot: String? = "",
    @ColumnInfo(name = "awards") val awards: String? = "",
    @ColumnInfo(name = "metascore") val metascore: String? = "",
    @ColumnInfo(name = "imdb_votes") val imdbVotes: String? = "",
    @ColumnInfo(name = "imdb_id") val imdbId: String? = "",
    @ColumnInfo(name = "type") val type: String? = "",
    @ColumnInfo(name = "page") val page: Int = 1,
    @ColumnInfo(name = "total_pages") val totalPageCount: Int = 1,
    @ColumnInfo(name = "fully_loaded") val fullyLoaded: Boolean = false
) {

    @Ignore
    constructor(
        id: Int?, title: String?, poster: String?, year: String?, country: String?, imdbRating: String?,
        genres: List<String>?, images: List<String>?, rated: String? = "", released: String? = "",
        runtime: String? = "", director: String? = "", writer: String? = "", actors: String? = "",
        plot: String? = "", awards: String? = "", metascore: String? = "", imdbVotes: String? = "",
        imdbId: String? = "", type: String? = "", page: Int = 1, totalPageCount: Int = 1,
        fullyLoaded: Boolean = false
    )
            : this(
        null, id, title, poster, year, country, imdbRating, genres, images, rated, released, runtime,
        director, writer, actors, plot, awards, metascore, imdbVotes, imdbId, type, page, totalPageCount, fullyLoaded
    ) {

    }

    @Ignore
    fun title() = "Title: $title"

    @Ignore
    fun country() = "Country: $country"

    @Ignore
    fun year() = "Year: $year"

    @Ignore
    fun rated() = "Rated: $rated"

    @Ignore
    fun imdbRating() = "IMDB rating: $imdbRating"

    @Ignore
    fun genres() = "GenreRetro: $genres"

    @Ignore
    fun metascore() = "Meta Score: $metascore"

    @Ignore
    fun awards() = "Awards: $awards"

    @Ignore
    fun runtime() = "Runtime: $runtime"

    @Ignore
    fun writer() = "Writer: $writer"

    @Ignore
    fun plot() = "PLot: $plot"

    @Ignore
    fun director() = "Director: $director"

    @Ignore
    fun actors() = "Actors: $actors"

    @Ignore
    fun imdbVotes() = "IMDB votes: $imdbVotes"
}