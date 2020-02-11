package com.test.box8New.db

import androidx.room.Embedded
import androidx.room.Relation

data class TodoWithTasks (

    @Embedded
    val todo: ToDoEntity?,

    @Relation(parentColumn = DbConstants.TODO_ID, entityColumn = DbConstants.TODO_ID_FOREIGN_KEY, entity = TaskEntity::class)
    val tasks: List<TaskEntity>?
)