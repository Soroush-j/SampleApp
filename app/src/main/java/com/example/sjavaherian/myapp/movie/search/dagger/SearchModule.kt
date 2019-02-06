package com.example.sjavaherian.myapp.movie.search.dagger

import android.arch.lifecycle.ViewModelProviders
import com.example.sjavaherian.myapp.movie.database.MovieDao
import com.example.sjavaherian.myapp.movie.search.SearchMovieFragment
import com.example.sjavaherian.myapp.movie.search.SearchViewModel
import com.example.sjavaherian.myapp.movie.search.SearchViewModelFactory
import com.example.sjavaherian.myapp.movie.search.adapter.ResultsAdapter
import com.example.sjavaherian.myapp.task.database.TasksDao
import dagger.Module
import dagger.Provides

@Module
class SearchModule(private val searchMovieFragment: SearchMovieFragment) {

    @Provides
    fun provideViewModelFactory(movieDao: MovieDao, tasksDao: TasksDao): SearchViewModelFactory =
            SearchViewModelFactory(movieDao, tasksDao)

    @Provides
    fun provideSearchViewModel(factory: SearchViewModelFactory): SearchViewModel =
            ViewModelProviders.of(searchMovieFragment, factory).get(SearchViewModel::class.java)

    @Provides
    fun provideResultsAdapter(): ResultsAdapter = ResultsAdapter()
}