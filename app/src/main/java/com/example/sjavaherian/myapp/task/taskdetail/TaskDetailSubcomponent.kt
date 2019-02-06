package com.example.sjavaherian.myapp.task.taskdetail

import dagger.Subcomponent

@Subcomponent(modules = [TaskDetailModule::class])
interface TaskDetailSubcomponent {
    fun inject(taskDetailFragment: TaskDetailFragment)
}