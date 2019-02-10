package com.sjavaherian.task.main

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.*
import android.util.Log
import android.view.*
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.sjavaherian.core.database.tasks.Task
import com.sjavaherian.task.R
import com.sjavaherian.task.common.TasksFilterType
import com.sjavaherian.task.databinding.TasksFragmentBinding
import com.sjavaherian.task.inject
import kotlinx.android.synthetic.main.tasks_fragment.*
import javax.inject.Inject

class TasksFragment : Fragment() {
    //todo: how items are ordered in recycler view?
    companion object {
        val TAG = "tag TasksFragment"

        fun newInstance() = TasksFragment()
    }

    @Inject
    lateinit var mViewModel: TasksViewModel

    @Inject
    lateinit var mAdapter: TasksAdapter

    private lateinit var navController: NavController

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        setHasOptionsMenu(true)

        inject()

        navController = NavHostFragment.findNavController(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = TasksFragmentBinding.inflate(layoutInflater, container, false)
        binding.apply {
            lifecycleOwner = this@TasksFragment
            model = mViewModel
        }
        val toolbar = binding.root.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        activity?.title = "TO-DOs"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tasks_recycler_view.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = this@TasksFragment.mAdapter
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }

        new_task_fab.setOnClickListener {
//            navController.navigate(TasksFragmentDirections.actionTasksToAddEditTask(null)) todo
        }

        mAdapter.openTaskDetailsEvent.observe(this, Observer {
            it?.run {
//                NavHostFragment.findNavController(this@TasksFragment).navigate(
//                    TasksFragmentDirections.actionTasksToTaskDetail(it.id)
//                ) todo
            }
        })

        mViewModel.start()

        mViewModel.tasks.observe(this, Observer<List<Task>> { t -> mAdapter.submitList(t) })


    }

    override fun onStop() {
        super.onStop()
        mViewModel.clearDisposable()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.tasks_frag_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.tasks_insert_fakes_menu -> mViewModel.insertFakeTasks()
            R.id.tasks_filtering_menu -> showPopUpMenu()
            R.id.tasks_search_menu -> {
//                navController.navigate(R.id.action_nav_tasks_fragment_to_nav_search_tasks_fragment)todo
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showPopUpMenu() {
        val popupMenu =
            PopupMenu(context!!, activity!!.findViewById<View>(R.id.tasks_filtering_menu))
        popupMenu.menuInflater.inflate(R.menu.tasks_filtering_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { it ->
            when (it.itemId) {
                R.id.filter_all_tasks_menu -> mViewModel.setFilterType(TasksFilterType.ALL_TASKS)
                R.id.filter_completed_menu -> mViewModel.setFilterType(TasksFilterType.COMPLETED)
                R.id.filter_active_menu -> mViewModel.setFilterType(TasksFilterType.ACTIVE)
            }
            return@setOnMenuItemClickListener true
        }
        popupMenu.show()
        Log.d(TAG, "popup menu: ")
    }
}