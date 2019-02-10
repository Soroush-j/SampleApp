package com.sjavaherian.task.common

import android.app.Application
import com.sjavaherian.core.database.tasks.TasksRepository
import dagger.Module
import dagger.Provides

@Module
class TaskMainModule {

    //    @Provides
//    fun providesViewModelFactory(app: Application, database: AppDatabase, repository: TasksRepository) =
//        ViewModelFactoryTask(app, database, repository)
    @Provides
    fun providesViewModelFactory(app: Application, repository: TasksRepository) =
        ViewModelFactoryTask(app, repository)

}