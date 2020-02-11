package com.test.box8New.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.box8New.R
import com.test.box8New.db.ToDoEntity
import kotlinx.android.synthetic.main.todo_adapter_item.view.*

class TodoAdapter(val dataList: List<ToDoEntity?>?,
                  val todoClickListener: (todoTasks: ToDoEntity?) -> Unit): RecyclerView.Adapter<TodoAdapter.TodoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        return TodoHolder(LayoutInflater.from(parent.context).inflate(R.layout.todo_adapter_item, parent, false))
    }

    override fun getItemCount(): Int { return dataList?.size ?: 0 }

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        val todo = dataList!![position]
        todo?.let{
            holder.itemView.todo_title_textView.text = it.title
        }
    }

    inner class TodoHolder(view: View): RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener { todoClickListener(dataList?.get(adapterPosition)) }
        }
    }
}