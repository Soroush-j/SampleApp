package com.example.sjavaherian.myapp.task

import com.example.sjavaherian.myapp.dagger.AppComponent
import com.example.sjavaherian.myapp.task.search.SearchTasksModule
import com.example.sjavaherian.myapp.task.search.SearchTasksSubComponent
import com.example.sjavaherian.myapp.task.taskaddedit.AddEditModule
import com.example.sjavaherian.myapp.task.taskaddedit.TaskAddEditSubcomponent
import com.example.sjavaherian.myapp.task.taskdetail.TaskDetailModule
import com.example.sjavaherian.myapp.task.taskdetail.TaskDetailSubcomponent
import com.example.sjavaherian.myapp.task.tasks.TasksModule
import com.example.sjavaherian.myapp.task.tasks.TasksSubComponent
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