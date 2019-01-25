package com.example.sjavaherian.myapp.movie.database

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.persistence.room.*
import io.reactivex.Flowable

@Dao
interface MovieDao {

    //    SELECT * FROM movie WHERE country LIKE "%usa%" ORDER BY id
    @Query("SELECT * FROM movie WHERE genres LIKE :genre ORDER BY id")
    fun loadByGenre(genre: String): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM movie WHERE title LIKE :title ORDER BY id")
    fun loadByTitle(title: String): DataSource.Factory<Int, Movie>

    @Query("SELECT COUNT(*) FROM movie ")
    fun rowCount(): Long

    @Query("SELECT * FROM movie ORDER BY id")
    fun loadAllMovies(): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM movie WHERE id = :movieId ORDER BY id")
    fun isMovieInDatabase(movieId: Int): List<Movie>?

    @Query("SELECT * FROM movie WHERE id= :movieId ORDER BY id")
    fun getMovieByIdRx(movieId: Int): Flowable<Movie>

    @Query("SELECT * FROM movie WHERE id= :movieId ORDER BY id")
    fun getMovieByIdLive(movieId: Int): LiveData<Movie>

    @Query("SELECT * FROM movie WHERE title= :name ORDER BY id")
    fun getMovieByName(name: String): List<Movie>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(movie: Movie)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMovie(movie: Movie)
}