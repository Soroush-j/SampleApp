package com.sjavaherian.core.database.tasks

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TasksRepository constructor(private val tasksDao: TasksDao) {

    fun loadAllTasks(): Flowable<List<Task>> {
        return tasksDao.loadAllTasks()
    }

    fun updateTask(task: Task): Completable {
        return Completable.fromAction { tasksDao.updateTask(task) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getTaskById(id: String): Single<Task> {
        return Single.fromCallable { tasksDao.getTaskById(id) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun saveTask(task: Task): Completable {
        return Completable.fromCallable { tasksDao.insertTask(task) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}