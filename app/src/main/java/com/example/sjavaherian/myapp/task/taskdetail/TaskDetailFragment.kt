package com.example.sjavaherian.myapp.task.taskdetail

import android.arch.lifecycle.Observer
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.sjavaherian.myapp.R
import com.example.sjavaherian.myapp.common.shortToast
import com.example.sjavaherian.myapp.databinding.TaskDetailFragBinding
import com.example.sjavaherian.myapp.task.inject
import javax.inject.Inject

class TaskDetailFragment : Fragment() {

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        setHasOptionsMenu(true)
        inject()
    }

    @Inject
    lateinit var mViewModel: TaskDetailViewModel

    private lateinit var mBinding: TaskDetailFragBinding
    lateinit var mNavController: NavController
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mNavController = findNavController()

        mBinding = DataBindingUtil.inflate(inflater, R.layout.task_detail_frag, container, false)
        mBinding.apply {
            lifecycleOwner = this@TaskDetailFragment
            model = mViewModel
        }

        val toolbar = mBinding.root.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.setupWithNavController(mNavController, AppBarConfiguration(mNavController.graph))

        return mBinding.root
    }

    private var mId: String? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mId = TaskDetailFragmentArgs.fromBundle(arguments!!).id
        if (mId != null) mViewModel.start(mId!!)
        else {
            shortToast(requireContext(), "Task id wasn't found!")
        }
    }

    override fun onStart() {
        super.onStart()

        mViewModel.openEditTask.observe(this, Observer { id ->
            id?.let {
                mNavController.navigate(TaskDetailFragmentDirections.actionTaskDetailToAddEditTask(it))
            }
        })
    }

    companion object {
        const val ARG_ID = "mId"
        val TAG = "tag TaskDetailFragment"

        fun newInstance() = TaskDetailFragment()
    }
}