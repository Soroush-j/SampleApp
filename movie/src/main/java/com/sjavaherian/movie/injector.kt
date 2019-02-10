package com.sjavaherian.movie

import android.app.Activity
import android.support.v4.app.Fragment
import com.sjavaherian.movie.dagger.MovieMainComponent
import com.sjavaherian.movie.details.MovieDetailsFragment
import com.sjavaherian.movie.details.dagger.MovieDetailsModule
import com.sjavaherian.movie.main.MoviesFragment
import com.sjavaherian.movie.main.dagger.MoviesModule
import com.sjavaherian.movie.search.SearchMovieFragment
import com.sjavaherian.movie.search.dagger.SearchModule

fun MoviesFragment.inject() {
    (activity?.application as Injector)
        .getMovieComponent()
        .addModule(MoviesModule(this))
        .inject(this)
}

fun MovieDetailsFragment.inject() {
    (activity?.application as Injector)
        .getMovieComponent()
        .addModule(MovieDetailsModule(this))
        .inject(this)

}

fun SearchMovieFragment.inject() {
    (activity?.application as Injector)
        .getMovieComponent()
        .addModule(SearchModule(this))
        .inject(this)
}

interface Injector {
    fun getMovieComponent(): MovieMainComponent
}

fun Fragment.saveSharedPreference(position: Int) {
    activity
        ?.getSharedPreferences("x", Activity.MODE_PRIVATE)
        ?.edit()
        ?.putInt("position", position)
        ?.apply()
}

fun Fragment.getIntFromSharedPreference(): Int {
    return activity
        ?.getSharedPreferences("x", Activity.MODE_PRIVATE)
        ?.getInt("position", 0)!!
}
