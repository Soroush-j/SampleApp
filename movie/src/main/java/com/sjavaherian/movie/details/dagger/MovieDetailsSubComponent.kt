package com.sjavaherian.movie.details.dagger

import com.sjavaherian.movie.details.MovieDetailsFragment
import dagger.Subcomponent

@Subcomponent(modules = [MovieDetailsModule::class])
interface MovieDetailsSubComponent {

    fun inject(movieDetailsFragment: MovieDetailsFragment)

}