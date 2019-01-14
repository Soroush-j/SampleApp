package com.example.sjavaherian.myapp.task.ui.tasks

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.*
import android.view.*
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.sjavaherian.myapp.R
import com.example.sjavaherian.myapp.common.logd
import com.example.sjavaherian.myapp.databinding.TasksFragmentBinding
import com.example.sjavaherian.myapp.task.TasksFilterType
import com.example.sjavaherian.myapp.task.database.Task
import com.example.sjavaherian.myapp.task.inject
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

    lateinit var mNavController: NavController

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        setHasOptionsMenu(true)

        inject()

        mNavController = NavHostFragment.findNavController(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = TasksFragmentBinding.inflate(layoutInflater, container, false)
        binding.apply {
            setLifecycleOwner(this@TasksFragment)
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
            mNavController.navigate(TasksFragmentDirections.actionTasksToAddEditTask(null))
        }

        mAdapter.openTaskDetailsEvent.observe(this, Observer {
            it?.run {
                NavHostFragment.findNavController(this@TasksFragment).navigate(
                    TasksFragmentDirections.actionTasksToTaskDetail(it.id)
                )
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
            R.id.tasks_refresh_menu -> mViewModel.loadTasks()
            R.id.tasks_filtering_menu -> showPopUpMenu()
            R.id.tasks_new_menu -> {
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
        logd(TAG, "popup menu: ")
    }
}