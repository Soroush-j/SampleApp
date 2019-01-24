package com.example.sjavaherian.myapp.movie

import android.app.Activity
import android.support.v4.app.Fragment
import com.example.sjavaherian.myapp.main.MainApp
import com.example.sjavaherian.myapp.movie.moviedetails.MovieDetailsFragment
import com.example.sjavaherian.myapp.movie.moviedetails.dagger.MovieDetailsModule
import com.example.sjavaherian.myapp.movie.movies.MoviesFragment
import com.example.sjavaherian.myapp.movie.movies.dagger.MoviesModule

fun MoviesFragment.inject() {
    MainApp.getMovieMainComponent(context?.applicationContext)
        .addModule(MoviesModule(this))
        .inject(this)
}

fun MovieDetailsFragment.inject() {
    MainApp.getMovieMainComponent(context?.applicationContext)
        .addModule(MovieDetailsModule(this))
        .inject(this)

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
        ?.getSharedPreferences("x",Activity.MODE_PRIVATE)
        ?.getInt("position",0)!!
}