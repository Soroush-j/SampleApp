package com.example.sjavaherian.myapp.movie.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface GenreDao {

    @Query("SELECT * FROM genre ORDER BY id")
    fun loadAllGenres(): LiveData<List<Genre>>

    @Query("DELETE FROM genre WHERE id = :rowId")
    fun deleteById(rowId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(genre: Genre): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(genre: Genre): Int

}