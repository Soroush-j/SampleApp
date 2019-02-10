package com.sjavaherian.core.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.sjavaherian.core.database.movies.Genre
import com.sjavaherian.core.database.movies.GenreDao
import com.sjavaherian.core.database.movies.Movie
import com.sjavaherian.core.database.movies.MovieDao
import com.sjavaherian.core.database.tasks.Task
import com.sjavaherian.core.database.tasks.TasksDao

@Database(entities = [Task::class, Movie::class, Genre::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TasksDao
    abstract fun movieDao(): MovieDao
    abstract fun genreDao(): GenreDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(AppDatabase::class.java) {
                if (INSTANCE == null) {
                    INSTANCE = Room
                        .databaseBuilder(context, AppDatabase::class.java, "todo.db")
                        .build()
                }
            }
            return INSTANCE!!
        }

//        fun destroy() {
//            INSTANCE = null
//        }
    }
}
