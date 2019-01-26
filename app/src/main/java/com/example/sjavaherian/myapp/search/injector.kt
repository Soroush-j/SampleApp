package com.example.sjavaherian.myapp.search

import com.example.sjavaherian.myapp.main.MainApp
import com.example.sjavaherian.myapp.search.dagger.DaggerSearchComponent
import com.example.sjavaherian.myapp.search.dagger.SearchModule

fun SearchFragment.inject() {
    // todo: every time a new viewModel is created.
            DaggerSearchComponent
                .builder()
                .appComponent(MainApp.getAppComponent(context?.applicationContext))
                .addModule(SearchModule(this))
                .build()
                .inject(this)
}