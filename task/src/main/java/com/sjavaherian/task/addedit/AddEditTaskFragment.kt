package com.sjavaherian.task.addedit

import android.arch.lifecycle.Observer
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.sjavaherian.task.inject
import com.sjavaherian.task.R
import com.sjavaherian.task.databinding.AddEditTaskFragmentBinding
import javax.inject.Inject

class AddEditTaskFragment : Fragment() {
    companion object {
        const val ARGUMENT_ID = "ARG_ID"
        const val NO_ID = "no mId"
        const val TAG = "tag AddEditTaskFragment"

        fun newInstance() = AddEditTaskFragment()
    }

    private var mId: String? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        setHasOptionsMenu(true)
        inject()

        //todo
//        mId = AddEditTaskFragmentArgs.fromBundle(arguments!!).id
    }

    @Inject
    lateinit var mViewModel: AddEditTaskViewModel

    private lateinit var mBinding: AddEditTaskFragmentBinding
    lateinit var toolbar: Toolbar
    lateinit var mNavController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? {

        mBinding = DataBindingUtil.inflate(layoutInflater, R.layout.add_edit_task_fragment, container, false)
        mNavController = findNavController()

        mBinding.apply {
            setLifecycleOwner(this@AddEditTaskFragment)
            model = mViewModel
        }

        toolbar = mBinding.root.findViewById(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.setupWithNavController(mNavController, AppBarConfiguration(mNavController.graph))


        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (mId != null) {
            mViewModel.getTaskById(mId!!)
            toolbar.title = "Edit to-do"
        } else toolbar.title = "New to-do"
    }

    override fun onStart() {
        super.onStart()

        mViewModel.saveEvent.observe(this, Observer {
            Log.d(TAG, "save event")
//            navController.navigate(R.id.action_add_edit_task_pop_tasks) todo
        })
    }

    override fun onDestroy() {
        mViewModel.clearDisposable()
        super.onDestroy()
    }
}