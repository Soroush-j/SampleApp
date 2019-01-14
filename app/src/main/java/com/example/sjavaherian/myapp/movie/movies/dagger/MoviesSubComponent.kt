package com.example.sjavaherian.myapp.movie.movies.dagger

import com.example.sjavaherian.myapp.movie.movies.MoviesFragment
import dagger.Subcomponent

@Subcomponent(modules = [MoviesModule::class])
interface MoviesSubComponent {

    fun inject(moviesFragment: MoviesFragment)
}