package com.example.sjavaherian.myapp.task.taskdetail

import com.sjavaherian.task.detail.TaskDetailFragment
import com.sjavaherian.task.detail.TaskDetailModule
import dagger.Subcomponent

@Subcomponent(modules = [TaskDetailModule::class])
interface TaskDetailSubcomponent {
    fun inject(taskDetailFragment: TaskDetailFragment)
}