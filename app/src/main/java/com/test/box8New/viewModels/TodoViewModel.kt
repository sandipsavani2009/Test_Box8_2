package com.test.box8New.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.box8New.TodoApplication
import com.test.box8New.db.*
import kotlinx.coroutines.*

class TodoViewModel: ViewModel() {

    override fun onCleared() {
        super.onCleared()
    }

    /**
     * Database Data-Access-Object to perform DB operations
     */
    private val dataAccessObject: TodoDao = TodoDatabase.getDatabase(TodoApplication.INSTANT, viewModelScope).todoDataAccessObject()

    /**
     * variable which holds data from database and supplies to ui-controllers
     */
    val todoWithTaskList = MutableLiveData<List<TodoWithTasks?>?>()

    val todoList = MutableLiveData<List<ToDoEntity>?>()

    fun readAllData() {
        viewModelScope.launch {
            todoWithTaskList.value = withContext(Dispatchers.IO) { dataAccessObject.getAllData() }
        }
    }

    fun readAllTodo() {
        viewModelScope.launch {
            todoList.value = withContext(Dispatchers.IO) { dataAccessObject.getAllTodo() }
        }
    }

    fun insertTodo(todo: ToDoEntity) {
        GlobalScope.launch(Dispatchers.IO) {
            val insertOperation = async {
                dataAccessObject.insertTodo(todo)
            }
            insertOperation.await()
        }
    }

    fun insertTodo(task: TaskEntity) {
        GlobalScope.launch(Dispatchers.IO) {
            val insertOperation = async {
                dataAccessObject.insertTask(task)
            }
            insertOperation.await()
        }
    }

    fun deleteTodo(todoId: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            val deleteOperation = async {
                dataAccessObject.deleteTodo(todoId)
            }
            deleteOperation.await()
        }
    }

    /*fun updateTask(task: TaskEntity) {
        GlobalScope.launch(Dispatchers.IO) {
            async {
                dataAccessObject.updateTaks(task)
            }.await()
        }
    }*/

    fun updateTodo(todoWithTask: TodoWithTasks) {
        GlobalScope.launch(Dispatchers.IO) {
            async {
                dataAccessObject.updateTaks(todoWithTask.tasks!!)
                dataAccessObject.updateTodo(todoWithTask.todo!!)
            }.await()
        }
    }

    fun getTask(taskId: Int): TaskEntity? {
        todoWithTaskList.value?.isNotEmpty().let {
            todoWithTaskList.value!!.forEach { todoWithTask ->

                todoWithTask!!.tasks?.isNotEmpty().let {
                    todoWithTask.tasks!!.forEach {  task ->
                        if (task!!.taskId == taskId) {
                            return task
                        }
                    }
                }
            }
        }

        return null
    }

    fun getTodo(todoId: Int): TodoWithTasks? {
        todoWithTaskList.value?.isNotEmpty().let {
            todoWithTaskList.value!!.forEach { todoWithTask ->

                if (todoWithTask!!.todo!!.id == todoId) {
                    return todoWithTask
                }
            }
        }

        return null
    }

}