package com.test.box8New.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.test.box8New.Constants
import com.test.box8New.R
import com.test.box8New.adapters.TodoAdapter
import com.test.box8New.db.ToDoEntity
import com.test.box8New.db.TodoWithTasks
import com.test.box8New.viewModels.TodoViewModel
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : BaseActivity() {

    private lateinit var mViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        mViewModel = ViewModelProviders.of(this).get(TodoViewModel::class.java)
        mViewModel.todoList.observe(this, Observer { onDataReceived(it) })

        add_todo_fab.setOnClickListener { launchTaskActivity(-1) }
    }

    private fun onDataReceived(todoTasksList: List<ToDoEntity?>?) {
        todoTasksList?.isNotEmpty().apply {

            todoRecyclerView.adapter = TodoAdapter(todoTasksList) { clickedTodoWithTask ->
                launchTaskActivity(clickedTodoWithTask?.id!!)
            }

        } ?:let {
            Snackbar.make(todo_root, "No List", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun launchTaskActivity(todoId: Int) {
        val intent = Intent(this, TaskActivity::class.java)
        if (todoId > 0) {
            intent.putExtra(Constants.VIEW_TODO_DETAILS_ID, todoId)
        }
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        mViewModel.readAllTodo()
    }
}
