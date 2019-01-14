package com.example.sjavaherian.myapp.dagger

import android.app.Application
import android.content.Context
import com.example.sjavaherian.myapp.data.AppDatabase
import com.example.sjavaherian.myapp.task.database.TasksDao
import com.example.sjavaherian.myapp.task.database.TasksRepository
import dagger.BindsInstance
import dagger.Component


@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {

    fun provideApplication(): Application
    fun provideContext(): Context
    fun provideDatabase(): AppDatabase
    fun provideTaskDao(): TasksDao

    fun provideTasksRepository(): TasksRepository


    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        @BindsInstance
        fun application(app: Application): Builder
    }

}