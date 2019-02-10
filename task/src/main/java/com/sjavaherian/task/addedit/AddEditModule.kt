package com.sjavaherian.task.addedit

import android.arch.lifecycle.ViewModelProviders
import com.sjavaherian.task.common.ViewModelFactoryTask
import dagger.Module
import dagger.Provides

@Module
class AddEditModule(private val addEditTaskFragment: AddEditTaskFragment) {

    @Provides
    fun providesAddEditTaskViewModel(factory: ViewModelFactoryTask) =
        ViewModelProviders.of(addEditTaskFragment, factory).get(AddEditTaskViewModel::class.java)
}