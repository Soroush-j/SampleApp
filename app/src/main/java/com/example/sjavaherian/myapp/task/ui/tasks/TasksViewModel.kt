package com.example.sjavaherian.myapp.task.ui.tasks

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import com.example.sjavaherian.myapp.R
import com.example.sjavaherian.myapp.common.BaseAndroidViewModel
import com.example.sjavaherian.myapp.common.loge
import com.example.sjavaherian.myapp.common.shortToast
import com.example.sjavaherian.myapp.task.TasksFilterType
import com.example.sjavaherian.myapp.task.database.Task
import com.example.sjavaherian.myapp.task.database.TasksRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function

class TasksViewModel constructor(
    app: Application,
    private val repository: TasksRepository
) : BaseAndroidViewModel(app) {

    private val TAG: String = "TAG TasksViewModel"

    private val items = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>>
        get() = items

    fun loadTasks(filterType: TasksFilterType = taskFilterType.get()!!) {
        disposable.add(
            repository.loadAllTasks()
                .map(Function<List<Task>, List<Task>> { t ->
                    val list = mutableListOf<Task>()
                    t.forEach {
                        when (filterType) {
                            TasksFilterType.ACTIVE -> if (it.isActive) list.add(it)
                            TasksFilterType.COMPLETED -> if (it.isCompleted()) list.add(it)
                            else -> list.add(it)
                        }
                    }
                    Log.d(
                        TAG,
                        "mFilterType: $filterType \n is mEmpty? ${list.isEmpty()} \n item count: ${list.size} \n list: $list"
                    )
                    emptyState(list.isEmpty(), filterType)
                    return@Function list
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        items.postValue(it)
                    },
                    { Log.e(TAG, "load tasks error: ${it.message}", it) })
        )
    }

    val mEmpty = ObservableInt(View.VISIBLE)
    val mNoTaskLabel = ObservableField<String>()
    val mNoTaskDrawable = ObservableField<Drawable>()

    private fun emptyState(show: Boolean, filterType: TasksFilterType) {
        val res = getApplication<Application>().baseContext.resources
        when (show) {
            true -> {
                mEmpty.set(View.VISIBLE)
                when (filterType) {
                    TasksFilterType.ALL_TASKS -> {
                        mNoTaskLabel.set("You have no TO-DOs!")
                        mNoTaskDrawable.set(
                            res.getDrawable(R.drawable.ic_done_all_48dp, null)
                        )
                    }
                    TasksFilterType.COMPLETED -> {
                        mNoTaskLabel.set("You have no active TO-DOs!")
                        mNoTaskDrawable.set(
                            res.getDrawable(R.drawable.ic_check_circle_48dp, null)
                        )
                    }
                    TasksFilterType.ACTIVE -> {
                        mNoTaskLabel.set("You have no active TO-DOs!")
                        mNoTaskDrawable.set(
                            res.getDrawable(R.drawable.ic_assignment_turned_in_48dp, null)
                        )
                    }
                }
            }
            false -> {
                mEmpty.set(View.GONE)
                mNoTaskLabel.set("")
                mNoTaskDrawable.set(res.getDrawable(R.drawable.ic_done_all_48dp, null))
            }
        }
        Log.d(TAG, "show label: ${mEmpty.get()}")
    }

    fun insertFakeTasks() {
        val fake: Array<Task> = arrayOf(
            Task("title 1", "desc 1"),
            Task("title 2", "desc 2"),
            Task("title 3", "desc 3"),
            Task("title 4", "desc 4"),
            Task("title 5", "desc 5"),
            Task("title 6", "desc 6"),
            Task("title 7", "desc 7"),
            Task("title 8", "desc 8"),
            Task("title 9", "desc 9"),
            Task("title 10", "desc 10")
        )

        fake.forEach {
            disposable.add(
                repository.saveTask(it)
                    .subscribe(
                        { Log.d(TAG, "Fakes are successfully inserted") },
                        { throwable -> Log.e(TAG, throwable.message, throwable) }
                    )
            )
        }
    }

    var taskFilterType = ObservableField<TasksFilterType>(TasksFilterType.ALL_TASKS)
    fun setFilterType(filterType: TasksFilterType) {
        taskFilterType.set(filterType)
        loadTasks(taskFilterType.get()!!)
    }

    fun start() {
        setFilterType(taskFilterType.get()!!)
    }

    fun updateTask(check: Boolean, task: Task) {
        val updateTask =
            repository.updateTask(Task(task.id, task.title, task.description, check))
                .doOnComplete {
                    shortToast(
                        getApplication(),
                        "Task is " + (if (check) "active" else "complete")
                    )
                }
                .doOnError { loge(TAG, it) }
                .subscribe()
        disposable.add(updateTask)
        Log.d(TAG, "update task isDisposed? ${updateTask.isDisposed}")
    }
}