package com.sjavaherian.movie.main

import android.arch.lifecycle.Observer
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.Spinner
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.sjavaherian.movie.inject
import com.sjavaherian.movie.R
import com.sjavaherian.movie.databinding.MoviesFragmentBinding
import com.sjavaherian.movie.main.adapters.MoviesAdapter
import com.sjavaherian.movie.main.adapters.SpinnerAdapter
import kotlinx.android.synthetic.main.movies_fragment.*
import org.jetbrains.anko.AnkoLogger
import javax.inject.Inject

class MoviesFragment : Fragment(), AnkoLogger {
    // todo: save the selected genre when closing this fragment
    // todo: upgrade to androidx.room to take advantage of new full-text search feature.

    companion object {
        private val TAG: String = "tag MoviesFragment"
        fun newInstance() = MoviesFragment()
    }

    @Inject
    lateinit var mViewModel: MoviesViewModel

    @Inject
    lateinit var mAdapter: MoviesAdapter

    @Inject
    lateinit var mSpinnerAdapter: SpinnerAdapter

    private lateinit var mBinding: MoviesFragmentBinding
    private lateinit var mNavController: NavController

    private var mGenre: String = "ALL"

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        inject()
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.movies_fragment, container, false)
        mBinding.viewModel = mViewModel

        mNavController = findNavController()
        mViewModel.start()

        Log.d(TAG, "onCreateView")

        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.title = "Movies"

        movies_rv.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = mAdapter
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }
        movies_loading_indicator.visibility = View.VISIBLE

//        movies_fab.setOnClickListener { findNavController().navigate(R.id.action_movies_to_search) } todo

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")


        mViewModel.movies.observe(this, Observer { pagedList ->
            movies_loading_indicator.visibility = View.INVISIBLE

            Log.d(TAG, "paged list size: " + pagedList?.size)

            val size = pagedList?.size
            if (size == null || size <= 0) {
                mViewModel.updateLoadingState("Loading failed.", View.VISIBLE)
            } else mViewModel.updateLoadingState("Loading Successful.", View.GONE)

            if (mGenre != "All") {
                pagedList?.filter { movie ->
                    Log.d(TAG, "filter: " + movie?.id?.toString())
                    movie?.genres?.let {
                        Log.d(TAG, "contains? ${it.contains(mGenre)}")
                        return@filter it.contains(mGenre)
                    }
                    return@filter false
                }
            }
            mAdapter.submitList(pagedList)
        })

        mViewModel.genres.observe(this, Observer { genres ->
            genres?.let { mSpinnerAdapter.addAll(it) }
        })

        mAdapter.openMovieDetailsEvent.observe(this, Observer { id ->
            id?.let {
//                mNavController.navigate(
//                    MoviesFragmentDirections.actionMoviesToMovieDetails(id)
//                ) todo
            }
        })
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
        mViewModel.onStop()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater?.inflate(R.menu.menu_movies_frag, menu)
        // todo: change to popup menu.
        val spinner = menu?.findItem(R.id.menu_movies_genres)?.actionView as Spinner
        spinner.adapter = mSpinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                mViewModel.updateLoadingState("", View.GONE)
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val genre = mSpinnerAdapter.getItem(position)
                mGenre = genre.name
                Log.d(TAG, genre.name)
                Log.d(TAG, "genre id: ${genre.id}")
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
        }
        return super.onOptionsItemSelected(item)
    }
}