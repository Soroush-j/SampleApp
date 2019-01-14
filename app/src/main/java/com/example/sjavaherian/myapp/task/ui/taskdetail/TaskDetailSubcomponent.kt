package com.example.sjavaherian.myapp.task.ui.taskdetail

import dagger.Subcomponent

@Subcomponent(modules = [TaskDetailModule::class])
interface TaskDetailSubcomponent {
    fun inject(taskDetailFragment: TaskDetailFragment)
}