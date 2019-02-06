package com.example.sjavaherian.myapp.movie.search

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.sjavaherian.myapp.movie.database.Movie
import com.example.sjavaherian.myapp.movie.database.MovieDao
import com.example.sjavaherian.myapp.task.database.TasksDao
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchViewModel(
    private val mMovieDao: MovieDao,
    private val mTasksDao: TasksDao
) : ViewModel() {
    private val TAG = "tag SearchViewModel"
    private val mDisposable = CompositeDisposable()

    val result = MutableLiveData<List<Movie>>()

    fun search(query: String) {
        Log.d(TAG,query)
        mDisposable.add(
            Observable.fromCallable { mMovieDao.searchMovieByTitle("%$query%") }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { list ->
                    Log.d(TAG, "result: ${list.size}")
                    result.value = list
                }
                .doOnError { throwable ->
                    Log.e(TAG, "search resulted in error", throwable)
                    throwable.printStackTrace()
                }
                .subscribe()
        )
    }

    fun onStop() {
        mDisposable.clear()
    }


}