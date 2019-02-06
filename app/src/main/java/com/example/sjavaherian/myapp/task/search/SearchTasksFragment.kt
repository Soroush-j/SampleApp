package com.example.sjavaherian.myapp.task.search


import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.sjavaherian.myapp.R
import com.example.sjavaherian.myapp.task.inject
import kotlinx.android.synthetic.main.search_task_fragment.*
import javax.inject.Inject

class SearchTasksFragment : Fragment() {

    @Inject
    lateinit var mAdapter: TasksResultAdapter

    @Inject
    lateinit var mViewModel: SearchTasksViewModel

    lateinit var mNavController: NavController
    lateinit var mToolbar: Toolbar
    lateinit var mRecyclerView: RecyclerView

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        inject()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.search_task_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindResources(view)

        mNavController = findNavController()
        NavigationUI.setupWithNavController(mToolbar, mNavController, AppBarConfiguration(mNavController.graph))

        search_tasks_fab.setOnClickListener { mViewModel.searchTasks(search_tasks_et.text.toString()) }
    }

    override fun onStart() {
        super.onStart()

        mViewModel.mResults.observe(this, Observer { mAdapter.submitList(it) })
        mAdapter.openTaskDetailsEvent.observe(this, Observer {

        })
    }

    private fun bindResources(view: View) {
        mToolbar = view.findViewById(R.id.toolbar)
        mRecyclerView = view.findViewById(R.id.search_tasks_rv)

        mRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = mAdapter
        }
    }

    override fun onStop() {
        super.onStop()
        mViewModel.onStop()
    }


}
