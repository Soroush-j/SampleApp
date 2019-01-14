package com.example.sjavaherian.myapp.task

import com.example.sjavaherian.myapp.main.MainActivity
import com.example.sjavaherian.myapp.main.MainApp
import com.example.sjavaherian.myapp.task.ui.taskaddedit.AddEditModule
import com.example.sjavaherian.myapp.task.ui.taskaddedit.AddEditTaskFragment
import com.example.sjavaherian.myapp.task.ui.taskdetail.TaskDetailFragment
import com.example.sjavaherian.myapp.task.ui.taskdetail.TaskDetailModule
import com.example.sjavaherian.myapp.task.ui.tasks.TasksFragment
import com.example.sjavaherian.myapp.task.ui.tasks.TasksModule

fun TasksFragment.inject() {
    MainApp.getTaskMainComponent(context?.applicationContext)
        .addModule(TasksModule(activity as MainActivity, this))
        .inject(this)
}

fun TaskDetailFragment.inject() {
    MainApp.getTaskMainComponent(context?.applicationContext)
        .addModule(TaskDetailModule(this))
        .inject(this)
}

fun AddEditTaskFragment.inject() {
    MainApp.getTaskMainComponent(context?.applicationContext)
        .addModule(AddEditModule(this))
        .inject(this)
}