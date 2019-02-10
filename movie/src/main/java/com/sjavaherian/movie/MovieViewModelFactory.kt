package com.sjavaherian.movie

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.sjavaherian.core.database.movies.GenreDao
import com.sjavaherian.core.database.movies.MovieDao
import com.sjavaherian.movie.details.MovieDetailsViewModel
import com.sjavaherian.movie.details.network.MovieDetailsApiEndpoint
import com.sjavaherian.movie.main.MoviesViewModel
import com.sjavaherian.movie.main.network.MovieBoundaryCallback
import com.sjavaherian.movie.main.network.MoviesApiEndPoint

class MovieViewModelFactory(
    private val app: Application,
    private val moviesApiEndPoint: MoviesApiEndPoint,
    private val detailsEndpoint: MovieDetailsApiEndpoint,
    private val movieDao: MovieDao,
    private val genreDao: GenreDao,
    private val movieBoundaryCallback: MovieBoundaryCallback
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                MoviesViewModel(moviesApiEndPoint, movieDao, genreDao, movieBoundaryCallback) as T
            }
            modelClass.isAssignableFrom(MovieDetailsViewModel::class.java) -> {
                MovieDetailsViewModel(detailsEndpoint, movieDao) as T
            }
            else -> throw IllegalArgumentException("ViewModel not found")
        }
    }
}