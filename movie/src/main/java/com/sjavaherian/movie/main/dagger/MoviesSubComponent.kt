package com.sjavaherian.movie.main.dagger

import com.sjavaherian.movie.main.MoviesFragment
import dagger.Subcomponent

@Subcomponent(modules = [MoviesModule::class])
interface MoviesSubComponent {
    fun inject(moviesFragment: MoviesFragment)
}