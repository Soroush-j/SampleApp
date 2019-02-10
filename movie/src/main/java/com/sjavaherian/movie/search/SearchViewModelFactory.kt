package com.sjavaherian.movie.search

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.sjavaherian.core.database.movies.MovieDao
import com.sjavaherian.core.database.tasks.TasksDao

class SearchViewModelFactory(
    private val mMovieDao: MovieDao,
    private val mTasksDao: TasksDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> SearchViewModel(mMovieDao, mTasksDao) as T
            else -> throw IllegalArgumentException("view model base class not found!")
        }
    }
}