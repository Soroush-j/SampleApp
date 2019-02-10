package com.sjavaherian.movie.search

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.sjavaherian.core.database.movies.Movie
import com.sjavaherian.movie.R
import com.sjavaherian.movie.inject
import com.sjavaherian.movie.search.adapter.ResultsAdapter
import kotlinx.android.synthetic.main.search_movies_fragment.*
import javax.inject.Inject

class SearchMovieFragment : Fragment() {
    private val TAG = "tag SearchMovieFragment"

    @Inject
    lateinit var mViewModel: SearchViewModel

    @Inject
    lateinit var mResultsAdapter: ResultsAdapter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.search_movies_fragment, container, false)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        NavigationUI.setupWithNavController(
            toolbar,
            findNavController(),
            AppBarConfiguration(findNavController().graph)
        )

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        inject()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        search_rv.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = mResultsAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        }

        search_fab.setOnClickListener {
            val query = search_et.text.toString().trim()
            mViewModel.search(query)
        }
    }

    override fun onStart() {
        super.onStart()

        mViewModel.result.observe(this, Observer {
            mResultsAdapter.submitList(it)
            showErrorMessage(it)
        })

        mResultsAdapter.openMovieDetailsEvent.observe(this, Observer { id ->
            Log.d(TAG, "id: $id")
            id?.let {
                //                findNavController().navigate(
//                    SearchMovieFragmentDirections.actionSearchToMovieDetails(it)
//                ) todo
            }
        })
    }

    private fun showErrorMessage(movies: List<Movie>?) {
        if (movies == null || movies.size == 0) {
            search_error_tv.visibility = View.VISIBLE
            search_rv.visibility = View.GONE
        } else {
            search_error_tv.visibility = View.GONE
            search_rv.visibility = View.VISIBLE
        }
    }

    override fun onStop() {
        super.onStop()
        mViewModel.onStop()
    }
}