package com.example.sjavaherian.myapp.movie

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.sjavaherian.myapp.movie.database.GenreDao
import com.example.sjavaherian.myapp.movie.database.MovieDao
import com.example.sjavaherian.myapp.movie.moviedetails.MovieDetailsViewModel
import com.example.sjavaherian.myapp.movie.moviedetails.network.MovieDetailsApiEndpoint
import com.example.sjavaherian.myapp.movie.movies.MoviesViewModel
import com.example.sjavaherian.myapp.movie.movies.network.MovieBoundaryCallback
import com.example.sjavaherian.myapp.movie.movies.network.MoviesApiEndPoint

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