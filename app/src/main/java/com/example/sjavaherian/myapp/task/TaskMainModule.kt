package com.example.sjavaherian.myapp.task

import android.app.Application
import com.example.sjavaherian.myapp.task.database.TasksRepository
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