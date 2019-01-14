package com.example.sjavaherian.myapp.movie.moviedetails.dagger

import com.example.sjavaherian.myapp.movie.moviedetails.MovieDetailsFragment
import dagger.Subcomponent

@Subcomponent(modules = [MovieDetailsModule::class])
interface MovieDetailsSubComponent {

    fun inject(movieDetailsFragment: MovieDetailsFragment)

}