package com.sjavaherian.core.dagger

import android.app.Application
import android.content.Context
import com.sjavaherian.core.database.movies.GenreDao
import com.sjavaherian.core.database.movies.MovieDao
import com.sjavaherian.core.database.tasks.TasksDao
import com.sjavaherian.core.database.tasks.TasksRepository
import com.sjavaherian.core.data.AppDatabase
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
    fun provideMovieDao(): MovieDao
    fun provideGenreDao(): GenreDao

    fun provideTasksRepository(): TasksRepository


    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        @BindsInstance
        fun application(app: Application): Builder
    }

}