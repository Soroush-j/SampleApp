package com.sjavaherian.movie.dagger

import com.example.sjavaherian.myapp.movie.dagger.MovieMainModule
import com.sjavaherian.movie.details.dagger.MovieDetailsSubComponent
import com.sjavaherian.movie.main.dagger.MoviesSubComponent
import com.sjavaherian.core.dagger.AppComponent
import com.sjavaherian.movie.details.dagger.MovieDetailsModule
import com.sjavaherian.movie.main.dagger.MoviesModule
import com.sjavaherian.movie.search.dagger.SearchModule
import com.sjavaherian.movie.search.dagger.SearchSubComponent
import dagger.Component

@Component(dependencies = [AppComponent::class], modules = [MovieMainModule::class])
interface MovieMainComponent {

    fun addModule(moviesModule: MoviesModule): MoviesSubComponent
    fun addModule(movieDetailsModule: MovieDetailsModule): MovieDetailsSubComponent
    fun addModule(searchModule: SearchModule):SearchSubComponent

    @Component.Builder
    interface Builder {
        fun build(): MovieMainComponent
        fun appComponent(appComponent: AppComponent): Builder
    }
}