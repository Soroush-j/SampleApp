package com.example.sjavaherian.myapp.search.dagger

import com.example.sjavaherian.myapp.dagger.AppComponent
import com.example.sjavaherian.myapp.search.SearchFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SearchModule::class], dependencies = [AppComponent::class])
interface SearchComponent {
    fun inject(searchFragment: SearchFragment)

    @Component.Builder
    interface Builder {
        fun appComponent(appComponent: AppComponent): Builder
        fun addModule(searchModule: SearchModule):Builder
        fun build(): SearchComponent
    }
}