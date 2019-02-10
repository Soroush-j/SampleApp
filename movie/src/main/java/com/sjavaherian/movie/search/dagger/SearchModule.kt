package com.sjavaherian.movie.search.dagger

import android.arch.lifecycle.ViewModelProviders
import com.sjavaherian.core.database.movies.MovieDao
import com.sjavaherian.movie.search.SearchMovieFragment
import com.sjavaherian.movie.search.SearchViewModel
import com.sjavaherian.movie.search.SearchViewModelFactory
import com.sjavaherian.movie.search.adapter.ResultsAdapter
import com.sjavaherian.core.database.tasks.TasksDao
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