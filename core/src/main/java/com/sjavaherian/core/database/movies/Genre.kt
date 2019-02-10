package com.sjavaherian.core.database.movies

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "genre")
data class Genre(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "genre_key") val key: Int?,
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String
) {
    @Ignore
    constructor(id: Int, name: String) : this(null, id, name)

    fun equals(genre: Genre): Boolean {
        return (this.id == genre.id) && (this.name == genre.name)
    }
}