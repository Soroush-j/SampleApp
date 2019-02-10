package com.sjavaherian.task.search

import com.sjavaherian.core.database.tasks.TasksDao
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