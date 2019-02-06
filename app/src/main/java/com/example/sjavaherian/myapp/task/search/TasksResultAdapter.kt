package com.example.sjavaherian.myapp.task.search

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sjavaherian.myapp.common.SingleLiveEvent
import com.example.sjavaherian.myapp.databinding.TaskItemBinding
import com.example.sjavaherian.myapp.task.database.Task

class TasksResultAdapter : ListAdapter<Task, TasksResultAdapter.ViewHolder>(DIFF_UTIL) {

    val openTaskDetailsEvent = SingleLiveEvent<String>()

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Task>() {
            override fun areItemsTheSame(p0: Task, p1: Task): Boolean =
                    p0.id == p1.id

            override fun areContentsTheSame(p0: Task, p1: Task): Boolean =
                    p0 == p1
        }
        private const val TAG = "tag TasksResultAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder =
            ViewHolder(TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task)
    }

    inner class ViewHolder(private val mBinding: TaskItemBinding) : RecyclerView.ViewHolder(mBinding.root) {

        fun bind(task: Task?) {
            mBinding.taskItemCheckbox.visibility = View.GONE
            mBinding.task = task

            mBinding.taskItemRoot.setOnClickListener { task?.let { openTaskDetailsEvent.value = it.id } }

            mBinding.executePendingBindings()
        }

    }
}