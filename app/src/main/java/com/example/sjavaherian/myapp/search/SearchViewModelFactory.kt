package com.example.sjavaherian.myapp.search

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.sjavaherian.myapp.movie.database.MovieDao
import com.example.sjavaherian.myapp.task.database.TasksDao

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