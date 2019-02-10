package com.sjavaherian.task

import com.sjavaherian.task.search.SearchTasksFragment
import com.sjavaherian.task.search.SearchTasksModule
import com.sjavaherian.task.detail.TaskDetailFragment
import com.sjavaherian.task.detail.TaskDetailModule
import com.sjavaherian.task.main.TasksFragment
import com.sjavaherian.task.addedit.AddEditModule
import com.sjavaherian.task.addedit.AddEditTaskFragment
import com.sjavaherian.task.common.TaskMainComponent
import com.sjavaherian.task.main.TasksModule

fun TasksFragment.inject() {
    (activity?.application as Injector)
        .getTaskComponent()
        .addModule(TasksModule(this))
        .inject(this)
}

fun TaskDetailFragment.inject() {
    (activity?.application as Injector)
        .getTaskComponent()
        .addModule(TaskDetailModule(this))
        .inject(this)
}

fun AddEditTaskFragment.inject() {
    (activity?.application as Injector)
        .getTaskComponent()
        .addModule(AddEditModule(this))
        .inject(this)
}

fun SearchTasksFragment.inject() {
    (activity?.application as Injector)
        .getTaskComponent()
        .addModule(SearchTasksModule())
        .inject(this)

}

interface Injector{
    fun getTaskComponent():TaskMainComponent
}