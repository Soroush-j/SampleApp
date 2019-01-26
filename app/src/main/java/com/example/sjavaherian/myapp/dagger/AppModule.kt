package com.example.sjavaherian.myapp.dagger

import android.app.Application
import android.content.Context
import com.example.sjavaherian.myapp.data.AppDatabase
import com.example.sjavaherian.myapp.movie.database.GenreDao
import com.example.sjavaherian.myapp.movie.database.MovieDao
import com.example.sjavaherian.myapp.task.database.TasksDao
import com.example.sjavaherian.myapp.task.database.TasksRepository
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