package com.example.sjavaherian.myapp.task

import com.example.sjavaherian.myapp.dagger.AppComponent
import com.example.sjavaherian.myapp.task.ui.taskaddedit.AddEditModule
import com.example.sjavaherian.myapp.task.ui.taskaddedit.TaskAddEditSubcomponent
import com.example.sjavaherian.myapp.task.ui.taskdetail.TaskDetailModule
import com.example.sjavaherian.myapp.task.ui.taskdetail.TaskDetailSubcomponent
import com.example.sjavaherian.myapp.task.ui.tasks.TasksModule
import com.example.sjavaherian.myapp.task.ui.tasks.TasksSubcomponent
import dagger.Component

@Component(
    modules = [TaskMainModule::class]
    , dependencies = [AppComponent::class]
)
interface TaskMainComponent {

    fun addModule(taskDetailModule: TaskDetailModule): TaskDetailSubcomponent
    fun addModule(addEditModule: AddEditModule): TaskAddEditSubcomponent
    fun addModule(tasksModule: TasksModule): TasksSubcomponent

    @Component.Builder
    interface Builder {
        fun build(): TaskMainComponent
        fun appComponent(appComponent: AppComponent): Builder
    }

}