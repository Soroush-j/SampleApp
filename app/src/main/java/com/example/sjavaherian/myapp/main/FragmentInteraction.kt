package com.example.sjavaherian.myapp.main

import android.view.View
import com.example.sjavaherian.myapp.task.database.Task

interface AddEditTaskListener {
    fun openEditTask(id: String)
    fun openAddTask()
}

interface TaskDetailListener {
    fun openTaskDetails(view: View, task: Task)
}

interface MovieDetailsListener {
    fun openMovieDetailsFragment(id: Int)
}