package com.example.sjavaherian.myapp.task.ui.taskdetail

import android.arch.lifecycle.ViewModelProviders
import com.example.sjavaherian.myapp.task.ViewModelFactoryTask
import dagger.Module
import dagger.Provides

@Module
class TaskDetailModule (private val taskDetailFragment: TaskDetailFragment){

    @Provides
    fun providesTaskDetailViewModel(factory: ViewModelFactoryTask) =
        ViewModelProviders.of(taskDetailFragment, factory).get(TaskDetailViewModel::class.java)
}