package com.example.sjavaherian.myapp.task.taskdetail

import android.app.Application
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.util.Log
import android.view.View
import android.widget.Switch
import android.widget.Toast
import com.sjavaherian.core.BaseAndroidViewModel
import com.sjavaherian.core.SingleLiveEvent
import com.sjavaherian.core.database.tasks.Task
import com.sjavaherian.core.database.tasks.TasksRepository

class TaskDetailViewModel(app: Application, private val repository: TasksRepository) : BaseAndroidViewModel(app) {
    private val TAG = "tag TaskDetailViewModel"

    private lateinit var mId: String

    fun start(id: String) {
        mId = id
        getTaskById(mId)
    }

    val title = ObservableField<String>()
    val desc = ObservableField<String>()
    val active = ObservableBoolean(true)
    private lateinit var mTask: Task

    private fun getTaskById(id: String) {
        disposable.add(
            repository.getTaskById(id)
                .subscribe({
                    mTask = it
                    active.set(it.isActive)
                    title.set(it.title)
                    desc.set(it.description)
                }, {
                    Log.w(TAG, "desirable task was not found!", it)
                    Toast.makeText(getApplication(), "Couldn't find the task!", Toast.LENGTH_SHORT).show()
                })
        )
    }

    val openEditTask = SingleLiveEvent<String>()
    fun openTaskEditFragment() {
        // under development
        // under development
        openEditTask.value = mId
    }

    fun updateTask(view: View) {
        val switch = view as Switch
        val updateTask = repository.updateTask(
            Task(mId, title.get()!!, desc.get()!!, switch.isChecked)
        ).subscribe(
            {
                Toast.makeText(
                    getApplication(),
                    "Task is " + (if (switch.isChecked) "active" else "complete"),
                    Toast.LENGTH_SHORT
                ).show()
            },
            { Log.e(TAG, it.message, it) })
        disposable.add(updateTask)
        Log.d(TAG, "update task isDisposed? ${updateTask.isDisposed}")
    }
}