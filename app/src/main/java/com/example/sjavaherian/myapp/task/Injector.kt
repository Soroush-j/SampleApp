package com.example.sjavaherian.myapp.task

import com.example.sjavaherian.myapp.main.MainApp
import com.example.sjavaherian.myapp.task.search.SearchTasksFragment
import com.example.sjavaherian.myapp.task.search.SearchTasksModule
import com.example.sjavaherian.myapp.task.taskaddedit.AddEditModule
import com.example.sjavaherian.myapp.task.taskaddedit.AddEditTaskFragment
import com.example.sjavaherian.myapp.task.taskdetail.TaskDetailFragment
import com.example.sjavaherian.myapp.task.taskdetail.TaskDetailModule
import com.example.sjavaherian.myapp.task.tasks.TasksFragment
import com.example.sjavaherian.myapp.task.tasks.TasksModule

fun TasksFragment.inject() {
    MainApp.getTaskMainComponent(context?.applicationContext)
        .addModule(TasksModule(this))
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

fun SearchTasksFragment.inject() {
    MainApp.getTaskMainComponent(context?.applicationContext)
        .addModule(SearchTasksModule())
        .inject(this)

}