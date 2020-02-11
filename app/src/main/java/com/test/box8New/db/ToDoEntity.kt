package com.test.box8New.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DbConstants.TODO_TABLE)
data class ToDoEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DbConstants.TODO_ID)
    val id: Int = 0,

    @ColumnInfo(name = DbConstants.TODO_TITLE)
    val title: String? = "",

    @ColumnInfo(name = DbConstants.MODIFIED_TIME)
    val modifiedTime: String? = ""

)