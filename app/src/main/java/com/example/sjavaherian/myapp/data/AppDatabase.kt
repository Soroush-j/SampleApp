package com.example.sjavaherian.myapp.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.example.sjavaherian.myapp.movie.database.Genre
import com.example.sjavaherian.myapp.movie.database.GenreDao
import com.example.sjavaherian.myapp.movie.database.Movie
import com.example.sjavaherian.myapp.movie.database.MovieDao
import com.example.sjavaherian.myapp.task.database.Task
import com.example.sjavaherian.myapp.task.database.TasksDao

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
