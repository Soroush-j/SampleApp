package com.example.sjavaherian.myapp.task.search

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.sjavaherian.myapp.common.BaseViewModel
import com.example.sjavaherian.myapp.task.database.Task
import com.example.sjavaherian.myapp.task.database.TasksDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchTasksViewModel(private val mTasksDao: TasksDao) : ViewModel(), BaseViewModel {
    override val TAG: String = "tag SearchTasksViewModel"
    override val mDisposable = CompositeDisposable()


    val mResults = MutableLiveData<List<Task>>()
    fun searchTasks(query: String) {
        mDisposable.add(
                mTasksDao.getTaskByTitle("%$query%")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext {
                            Log.d(TAG, "search result: $it")
                            mResults.value = it
                        }
                        .doOnComplete { Log.d(TAG,"search completed.") }
                        .subscribe()
        )
    }

    override fun onStop() {
        mDisposable.clear()
    }
}