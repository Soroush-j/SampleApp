package com.sjavaherian.task.addedit

import com.sjavaherian.task.addedit.AddEditModule
import com.sjavaherian.task.addedit.AddEditTaskFragment
import dagger.Subcomponent

@Subcomponent(modules = [AddEditModule::class])
interface TaskAddEditSubcomponent {
    fun inject(addEditTaskFragment: AddEditTaskFragment)
}