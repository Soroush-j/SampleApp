package com.example.sjavaherian.myapp.movie.search.dagger

import com.example.sjavaherian.myapp.dagger.AppComponent
import com.example.sjavaherian.myapp.movie.search.SearchMovieFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SearchModule::class], dependencies = [AppComponent::class])
interface SearchComponent {
    fun inject(searchMovieFragment: SearchMovieFragment)

    @Component.Builder
    interface Builder {
        fun appComponent(appComponent: AppComponent): Builder
        fun addModule(searchModule: SearchModule):Builder
        fun build(): SearchComponent
    }
}