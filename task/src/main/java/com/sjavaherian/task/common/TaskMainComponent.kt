package com.sjavaherian.task.common

import com.sjavaherian.task.search.SearchTasksModule
import com.sjavaherian.task.search.SearchTasksSubComponent
import com.sjavaherian.task.addedit.AddEditModule
import com.sjavaherian.task.addedit.TaskAddEditSubcomponent
import com.sjavaherian.task.detail.TaskDetailModule
import com.example.sjavaherian.myapp.task.taskdetail.TaskDetailSubcomponent
import com.sjavaherian.core.dagger.AppComponent
import com.sjavaherian.task.main.TasksModule
import com.sjavaherian.task.main.TasksSubComponent
import dagger.Component

@Component(
    modules = [TaskMainModule::class]
    , dependencies = [AppComponent::class]
)
interface TaskMainComponent {

    fun addModule(taskDetailModule: TaskDetailModule): TaskDetailSubcomponent
    fun addModule(addEditModule: AddEditModule): TaskAddEditSubcomponent
    fun addModule(tasksModule: TasksModule): TasksSubComponent
    fun addModule(searchTasksModule: SearchTasksModule): SearchTasksSubComponent

    @Component.Builder
    interface Builder {
        fun build(): TaskMainComponent
        fun appComponent(appComponent: AppComponent): Builder
    }

}