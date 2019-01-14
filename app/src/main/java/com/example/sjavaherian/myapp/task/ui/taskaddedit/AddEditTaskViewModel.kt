package com.example.sjavaherian.myapp.task.ui.taskaddedit

import android.app.Application
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.support.v4.content.res.ResourcesCompat
import android.util.Log
import com.example.sjavaherian.myapp.common.BaseAndroidViewModel
import com.example.sjavaherian.myapp.R
import com.example.sjavaherian.myapp.common.SingleLiveEvent
import com.example.sjavaherian.myapp.task.database.TasksRepository
import com.example.sjavaherian.myapp.task.database.Task
import com.example.sjavaherian.myapp.common.shortToast
import com.orhanobut.logger.Logger

class AddEditTaskViewModel(
    app: Application,
    private val repository: TasksRepository
) : BaseAndroidViewModel(app) {

    private val TAG = "tag AddEditTaskViewMod"

    var task: Task =
        Task("", "")
    val state = ObservableField<String>()
    val title = ObservableField<String>()
    val desc = ObservableField<String>()
    val colorRes = ObservableInt()
    val active = ObservableBoolean(true)
    var id: String? = null
    private val resources = getApplication<Application>().resources
    fun getTaskById(id: String) {
        disposable.add(
            repository.getTaskById(id)
                .subscribe({
                    task = it
                    this.id = it.id
                    title.set(it.title)
                    desc.set(it.description)
                    active.set(it.isActive)

                    if (it.isActive) {
                        Log.d(TAG, "first if task is active: ${it.isActive}")
                        state.set("Active")
                        colorRes.set(ResourcesCompat.getColor(resources, R.color.red, null))
                    } else {
                        Log.d(TAG, "task is active: ${it.isCompleted()}")
                        state.set("Completed")
                        colorRes.set(ResourcesCompat.getColor(resources, R.color.green, null))
                    }

                },
                    {
                        Log.w(TAG, "Task was not found!", it)
                        shortToast(getApplication(), "Couldn't find the task!")
                    })
        )
    }

    var isSaved: Boolean = false
    val saveEvent = SingleLiveEvent<Unit>()
    fun saveTodo(): Boolean {
        if (title.get() == null && desc.get() == null) {
            shortToast(getApplication(), "Nothing was saved!")
            saveEvent.call()
            return true
        }

        if (!checkTaskHasChanged()) {
            shortToast(getApplication(), "To-do wasn't changed!!")
            saveEvent.call()
            return true
        }
        if (checkTaskNonNull() && checkTaskHasChanged()) {
            if (id.isNullOrBlank()) {
                saveTodo(
                    Task(title.get()!!, desc.get()!!, active.get())
                )
                saveEvent.call()
                shortToast(getApplication(), "New to-do saved!!")
                return true
            } else if (!id.isNullOrBlank()) {
                updateTask(
                    Task(id!!, title.get()!!, desc.get()!!, active.get())
                )
                saveEvent.call()
                shortToast(getApplication(), "To-do updated!!")
                return true
            }
        }
        saveEvent.call()
        shortToast(getApplication(), "Nothing was saved!")
        return false
    }

    private fun checkTaskHasChanged(): Boolean {
        val bool = (title.get() != task.title) || (desc.get() != task.description) || (active.get() != task.isActive)
        Logger.d("title? " + (title.get() != task.title))
        Logger.d("des? " + (desc.get() != task.description))
        Logger.d("active? " + (active.get() != task.isActive))
        Logger.d("has changed? $bool")
        return bool
    }

    private fun updateTask(task: Task) {
        disposable.add(
            repository.updateTask(task)
                .subscribe({
                    shortToast(getApplication(), "To-do isSaved.")
                    isSaved = true
                },
                    {
                        Log.e(TAG, "Couldn't save the new to-do", it)
                        shortToast(getApplication(), "To-do saving failed.")
                        isSaved = false
                    })
        )
    }

    private fun saveTodo(task: Task) {
        disposable.add(
            repository.saveTask(task)
                .subscribe({
                    shortToast(getApplication(), "To-do isSaved.")
                    isSaved = true
                },
                    {
                        Log.e(TAG, "Couldn't save the new to-do", it)
                        shortToast(getApplication(), "To-do saving failed.")
                        isSaved = false
                    })
        )
    }

    private fun checkTaskNonNull(): Boolean {
        if (title.get().isNullOrBlank()) {
            shortToast(getApplication(), "Can't create an empty to-do")
            return false
        }
        return true
    }

    fun changeState() {
        if (active.get()) {
            state.set("Active")
            colorRes.set(ResourcesCompat.getColor(resources, R.color.red, null))
        } else if (!active.get()) {
            state.set("Completed")
            colorRes.set(ResourcesCompat.getColor(resources, R.color.green, null))
        }
    }
}