package com.example.sjavaherian.myapp.task.taskaddedit

import dagger.Subcomponent

@Subcomponent(modules = [AddEditModule::class])
interface TaskAddEditSubcomponent {
    fun inject(addEditTaskFragment: AddEditTaskFragment)
}