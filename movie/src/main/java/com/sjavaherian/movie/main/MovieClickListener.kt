package com.sjavaherian.movie.main

import com.sjavaherian.core.database.movies.Movie

interface MovieClickListener {
    fun onMovieClicked(movie: Movie)
}