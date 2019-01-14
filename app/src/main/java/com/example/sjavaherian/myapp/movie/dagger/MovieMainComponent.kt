package com.example.sjavaherian.myapp.movie.dagger

import com.example.sjavaherian.myapp.dagger.AppComponent
import com.example.sjavaherian.myapp.movie.moviedetails.dagger.MovieDetailsModule
import com.example.sjavaherian.myapp.movie.moviedetails.dagger.MovieDetailsSubComponent
import com.example.sjavaherian.myapp.movie.movies.dagger.MoviesModule
import com.example.sjavaherian.myapp.movie.movies.dagger.MoviesSubComponent
import dagger.Component

@Component(dependencies = [AppComponent::class], modules = [MovieMainModule::class])
interface MovieMainComponent {

    fun addModule(moviesModule: MoviesModule): MoviesSubComponent
    fun addModule(movieDetailsModule: MovieDetailsModule): MovieDetailsSubComponent

    @Component.Builder
    interface Builder {
        fun build(): MovieMainComponent
        fun appComponent(appComponent: AppComponent): Builder
    }
}