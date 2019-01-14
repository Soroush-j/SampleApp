package com.example.sjavaherian.myapp.movie.movies

import com.example.sjavaherian.myapp.movie.database.Movie

interface MovieClickListener {
    fun onMovieClicked(movie: Movie)
}