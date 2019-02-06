package com.example.sjavaherian.myapp.task

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.sjavaherian.myapp.task.database.TasksRepository
import com.example.sjavaherian.myapp.task.taskaddedit.AddEditTaskViewModel
import com.example.sjavaherian.myapp.task.taskdetail.TaskDetailViewModel
import com.example.sjavaherian.myapp.task.tasks.TasksViewModel

class ViewModelFactoryTask constructor(
    private val app: Application,
//    private val database: AppDatabase,
    private val repository: TasksRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(TasksViewModel::class.java) -> TasksViewModel(app, repository) as T
//            modelClass.isAssignableFrom(TasksViewModel::class.java) -> TasksViewModel(app, database, repository) as T
            modelClass.isAssignableFrom(TaskDetailViewModel::class.java) -> TaskDetailViewModel(app, repository) as T
            modelClass.isAssignableFrom(AddEditTaskViewModel::class.java) -> AddEditTaskViewModel(app, repository) as T
            else -> throw IllegalArgumentException("ViewModel not found")

        }
    }
}