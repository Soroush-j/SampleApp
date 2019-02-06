package com.example.sjavaherian.myapp.task.taskaddedit

import android.arch.lifecycle.ViewModelProviders
import com.example.sjavaherian.myapp.task.ViewModelFactoryTask
import dagger.Module
import dagger.Provides

@Module
class AddEditModule(private val addEditTaskFragment: AddEditTaskFragment) {

    @Provides
    fun providesAddEditTaskViewModel(factory: ViewModelFactoryTask) =
        ViewModelProviders.of(addEditTaskFragment, factory).get(AddEditTaskViewModel::class.java)
}