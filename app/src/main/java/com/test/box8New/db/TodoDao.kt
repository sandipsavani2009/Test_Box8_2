package com.test.box8New.db

import androidx.annotation.WorkerThread
import androidx.room.*

@Dao
abstract class TodoDao {

    @WorkerThread
    @Query("SELECT todo.*, task.*" +
            " from ${DbConstants.TODO_TABLE} as todo, ${DbConstants.TASK_TABLE} as task " +
            " WHERE " +
            "todo.${DbConstants.TODO_ID} = task.${DbConstants.TASK_ID}")
    abstract suspend fun getAllData(): List<TodoWithTasks>?

    @WorkerThread
    @Query("SELECT * FROM ${DbConstants.TODO_TABLE}")
    abstract suspend fun getAllTodo(): List<ToDoEntity>?

    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertTodo(todo: ToDoEntity)

    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertTask(task: TaskEntity)

    @WorkerThread
    @Update
    abstract suspend fun updateTaks(tasks: List<TaskEntity>)

    @WorkerThread
    @Update
    abstract suspend fun updateTodo(todo: ToDoEntity)

    @WorkerThread
    @Query("DELETE FROM ${DbConstants.TODO_TABLE} WHERE ${DbConstants.TODO_ID} = :id" )
    abstract suspend fun deleteTodo(id: Int)
}