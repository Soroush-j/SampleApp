package com.example.sjavaherian.myapp.task.database

import android.arch.persistence.room.*
import java.util.*

@Entity(tableName = "tasks", indices = [Index(value = ["title", "description"])])
data class Task(

        // TODO: fix mid later.
        @PrimaryKey
        @ColumnInfo(name = "mId")
        val id: String,

        @ColumnInfo(name = "title")
        val title: String,

        @ColumnInfo(name = "description")
        val description: String,

        @ColumnInfo(name = "active")
        val isActive: Boolean
) {

    fun isCompleted(): Boolean = !isActive

    val isEmpty: Boolean
        get() = title.isNullOrBlank() && description.isNullOrBlank()

    fun titleForList(): String = if (!title.isNullOrBlank()) {
        title
    } else if (!description.isNullOrBlank()) {
        description
    } else {
        "null"
    }

    @Ignore
    constructor(id: String, title: String, description: String) : this(id, title, description, true)

    @Ignore
    constructor(title: String, description: String) : this(UUID.randomUUID().toString(), title, description, true)

    @Ignore
    constructor(title: String, description: String, completed: Boolean) : this(
            UUID.randomUUID().toString(),
            title,
            description,
            completed
    )


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val task = other as Task
        return (id == task.id) &&
                (title == task.title) &&
                (description == task.description)
    }

    override fun hashCode(): Int = Objects.hash(id, title, description)


    override fun toString(): String = "Task with title $title"

}
