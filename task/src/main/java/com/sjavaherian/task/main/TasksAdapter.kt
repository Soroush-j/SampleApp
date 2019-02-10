package com.sjavaherian.task.main

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import com.sjavaherian.core.SingleLiveEvent
import com.sjavaherian.core.database.tasks.Task
import com.sjavaherian.task.R

class TasksAdapter(
    private val mViewModel: TasksViewModel
) : ListAdapter<Task, TasksAdapter.ViewHolder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Task>() {
            override fun areItemsTheSame(p0: Task, p1: Task): Boolean {
                return p0.title == p1.title && p0.description == p1.description
            }

            override fun areContentsTheSame(p0: Task, p1: Task): Boolean {
                return p0.title == p1.title && p0.description == p1.description && p0.id == p1.id && p0.isActive == p1.isActive
            }
        }
    }

    private val TAG = "tag TasksAdapter"
    val openTaskDetailsEvent = SingleLiveEvent<Task>()
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = getItem(position)

        val listener = object : OnTaskItemClickListener {
            override fun onCheckBoxClicked(view: View, task: Task) {
                mViewModel.updateTask((view as CheckBox).isChecked, task)
            }

            override fun onTaskClicked(view: View, task: Task) {
                openTaskDetailsEvent.value = task
            }
        }

        holder.bind(task, listener)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvTitle: TextView = itemView.findViewById(R.id.task_item_title)
        var checkbox: CheckBox = itemView.findViewById(R.id.task_item_checkbox)
        var root: LinearLayout = itemView.findViewById(R.id.task_item_root)

        fun bind(task: Task, listener: OnTaskItemClickListener) {
            tvTitle.text = task.title
            checkbox.isChecked = task.isActive
            root.setOnClickListener { listener.onTaskClicked(it, task) }
            checkbox.setOnClickListener { listener.onCheckBoxClicked(it, task) }
        }

    }

    interface OnTaskItemClickListener {
        fun onCheckBoxClicked(view: View, task: Task)
        fun onTaskClicked(view: View, task: Task)
    }
}