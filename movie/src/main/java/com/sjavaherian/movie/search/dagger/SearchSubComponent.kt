package com.sjavaherian.movie.search.dagger

import com.sjavaherian.core.dagger.AppComponent
import com.sjavaherian.movie.search.SearchMovieFragment
import dagger.Component
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Subcomponent(modules = [SearchModule::class])
interface SearchSubComponent {
    fun inject(searchMovieFragment: SearchMovieFragment)
}