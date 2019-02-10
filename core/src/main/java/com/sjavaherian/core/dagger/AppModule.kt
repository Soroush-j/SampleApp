package com.sjavaherian.core.dagger

import android.app.Application
import android.content.Context
import com.sjavaherian.core.database.movies.GenreDao
import com.sjavaherian.core.database.movies.MovieDao
import com.sjavaherian.core.database.tasks.TasksDao
import com.sjavaherian.core.database.tasks.TasksRepository
import com.sjavaherian.core.data.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideContext(app: Application): Context = app.baseContext

    @Provides
    fun provideDatabase(context: Context): AppDatabase = AppDatabase.getInstance(context)

    @Provides
    fun provideTaskDao(database: AppDatabase): TasksDao = database.taskDao()

    @Provides
    fun provideTasksRepository(tasksDao: TasksDao): TasksRepository = TasksRepository(tasksDao)

    @Provides
    fun provideMovieDao(appDatabase: AppDatabase): MovieDao = appDatabase.movieDao()

    @Provides
    fun provideGenreDao(appDatabase: AppDatabase): GenreDao = appDatabase.genreDao()
}