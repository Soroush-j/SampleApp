package com.example.sjavaherian.myapp.task.search

import com.example.sjavaherian.myapp.task.database.TasksDao
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [SearchTasksModule::class])
interface SearchTasksSubComponent {
    fun inject(searchTasksFragment: SearchTasksFragment)
}


@Module
class SearchTasksModule {

    @Provides
    fun provideTasksResultAdapter(): TasksResultAdapter =
            TasksResultAdapter()

    @Provides
    fun provideSearchTasksViewModel(tasksDao: TasksDao): SearchTasksViewModel =
            SearchTasksViewModel(tasksDao)
}