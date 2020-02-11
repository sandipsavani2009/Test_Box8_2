package com.test.box8New.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = DbConstants.TASK_TABLE,
    foreignKeys = arrayOf(ForeignKey(entity = ToDoEntity::class,
    parentColumns = arrayOf(DbConstants.TODO_ID),
    childColumns = arrayOf(DbConstants.TODO_ID_FOREIGN_KEY),
    onDelete = ForeignKey.CASCADE,
    onUpdate = ForeignKey.CASCADE)))
data class TaskEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DbConstants.TASK_ID)
    val taskId: Int,

    @ColumnInfo(name = DbConstants.TODO_ID_FOREIGN_KEY)
    val todoId: Int,

    @ColumnInfo(name = DbConstants.TASK_DESC)
    val desc: String? = "",

    @ColumnInfo(name = DbConstants.TASK_STATUS)
    val status: String = DbConstants.TASK_STATUS_CREATED
)