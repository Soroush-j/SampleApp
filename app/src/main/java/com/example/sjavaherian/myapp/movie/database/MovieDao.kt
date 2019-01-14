package com.example.sjavaherian.myapp.movie.database

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.persistence.room.*
import io.reactivex.Flowable

@Dao
interface MovieDao {

    @Query("SELECT COUNT(*) FROM movie")
    fun rowCount(): Long

    @Query("SELECT * FROM movie")
    fun loadAllMovies(): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM movie WHERE id = :movieId")
    fun isMovieInDatabase(movieId: Int): List<Movie>?

    @Query("SELECT * FROM movie WHERE id= :movieId")
    fun getMovieByIdRx(movieId: Int): Flowable<Movie>

    @Query("SELECT * FROM movie WHERE id= :movieId")
    fun getMovieByIdLive(movieId: Int): LiveData<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(movie: Movie)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMovie(movie: Movie)
}