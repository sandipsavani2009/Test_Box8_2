package com.test.box8New.ui

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.test.box8New.Constants
import com.test.box8New.R
import com.test.box8New.db.ToDoEntity
import com.test.box8New.db.TodoWithTasks
import com.test.box8New.viewModels.TodoViewModel
import kotlinx.android.synthetic.main.activity_task.*

class TaskActivity : BaseActivity() {

    private val TAG = TaskActivity::class.java.simpleName

    private lateinit var mViewModel: TodoViewModel
    private var mToDoId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        initMembers()
    }

    private fun initMembers() {
        mViewModel = ViewModelProviders.of(this).get(TodoViewModel::class.java)
        mViewModel.todoWithTaskList.observe(this, Observer { onDataReceived(it) })
        mViewModel.readAllData()

        save_task_button.setOnClickListener { saveTask() }
    }

    private fun saveTask() {
        task_title_editView.text.toString().isNotEmpty().let {

            if (mToDoId > 0) {
                mViewModel.getTodo(mToDoId)?.let {
                    mViewModel.updateTodo(it)
                    Log.d(TAG, "todo Updated")
                }
            } else {
                // create new task
                val todo = ToDoEntity(title = task_title_editView.text.toString(), modifiedTime = System.currentTimeMillis().toString())
                mViewModel.insertTodo(todo)
                Log.d(TAG, "todo Saved")
            }
            finish()
        }
    }

    private fun onDataReceived(todoTasksList: List<TodoWithTasks?>?) {
        intent?.let {
            mToDoId = it.getIntExtra(Constants.VIEW_TODO_DETAILS_ID, -1)
            if (mToDoId > 0) {
                mViewModel.getTodo(mToDoId)?.let {

                    task_title_editView.text = Editable.Factory.getInstance().newEditable(it.todo?.title)
                }
            } else {
                // create new task
            }
        }
    }


}
