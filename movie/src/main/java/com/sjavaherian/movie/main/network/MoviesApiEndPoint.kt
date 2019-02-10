package com.sjavaherian.movie.main.network

import com.sjavaherian.movie.main.network.pojo.MoviesResponse
import com.sjavaherian.movie.main.network.pojo.GenreRetro
import com.sjavaherian.movie.main.network.pojo.GenreResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MoviesApiEndPoint {

    @GET("movies")
    fun loadAllMovies(@Query("page") page: Int): Call<MoviesResponse>

    @GET("genres")
    fun getAllGenres(): Call<List<GenreRetro>?>

    @GET("genres/{genre_id}/movies")
    fun getMoviesByGenre(@Path("genre_id") id: Int, @Query("page") pageNumber: Int = 1): Call<GenreResponse>

    companion object {
        const val MOVIE_API_RETROFIT = "MOVIE_API_RETROFIT"
        const val MOVIES_ENDPOINT = "MOVIES_ENDPOINT"
        const val MOVIES_API_BASE_URL = "http://moviesapi.ir/api/v1/"
    }
}