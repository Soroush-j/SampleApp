package com.example.sjavaherian.myapp.search.dagger

import android.arch.lifecycle.ViewModelProviders
import com.example.sjavaherian.myapp.movie.database.MovieDao
import com.example.sjavaherian.myapp.search.SearchFragment
import com.example.sjavaherian.myapp.search.SearchViewModel
import com.example.sjavaherian.myapp.search.SearchViewModelFactory
import com.example.sjavaherian.myapp.search.adapter.ResultsAdapter
import com.example.sjavaherian.myapp.task.database.TasksDao
import dagger.Module
import dagger.Provides

@Module
class SearchModule(private val searchFragment: SearchFragment) {

    @Provides
    fun provideViewModelFactory(movieDao: MovieDao, tasksDao: TasksDao): SearchViewModelFactory =
        SearchViewModelFactory(movieDao, tasksDao)

    @Provides
    fun provideSearchViewModel(factory: SearchViewModelFactory): SearchViewModel =
        ViewModelProviders.of(searchFragment,factory).get(SearchViewModel::class.java)

    @Provides
    fun provideResultsAdapter() :ResultsAdapter = ResultsAdapter()
}