package com.example.sjavaherian.myapp.movie.search

import com.example.sjavaherian.myapp.main.MainApp
import com.example.sjavaherian.myapp.movie.search.dagger.DaggerSearchComponent
import com.example.sjavaherian.myapp.movie.search.dagger.SearchModule

fun SearchMovieFragment.inject() {
    // todo: every time a new viewModel is created.
            DaggerSearchComponent
                .builder()
                .appComponent(MainApp.getAppComponent(context?.applicationContext))
                .addModule(SearchModule(this))
                .build()
                .inject(this)
}