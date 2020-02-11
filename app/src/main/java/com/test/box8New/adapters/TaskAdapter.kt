package com.test.box8New.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.box8New.R
import com.test.box8New.db.TaskEntity
import kotlinx.android.synthetic.main.task_adapter_item.view.*

class TaskAdapter(val taskList: List<TaskEntity?>?,
                  val taskClickListener: (tasks: TaskEntity?) -> Unit): RecyclerView.Adapter<TaskAdapter.TaskHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        return TaskHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_adapter_item, parent, false))
    }

    override fun getItemCount(): Int { return taskList?.size ?: 0 }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        taskList?.let {
            val task = it[position]
            holder.itemView.task_desc_textView.text = task?.desc
        }

    }

    inner class TaskHolder(view: View): RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener { taskClickListener(taskList?.get(adapterPosition)) }
        }
    }
}