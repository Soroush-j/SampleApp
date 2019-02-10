package com.sjavaherian.core.database.movies

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.sjavaherian.core.database.movies.Genre

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