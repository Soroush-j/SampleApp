package com.example.sjavaherian.myapp.search

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
import com.example.sjavaherian.myapp.R
import com.example.sjavaherian.myapp.movie.database.Movie
import com.example.sjavaherian.myapp.search.adapter.ResultsAdapter
import kotlinx.android.synthetic.main.search_frag.*
import javax.inject.Inject

class SearchFragment : Fragment() {
    private val TAG = "tag SearchFragment"

    @Inject
    lateinit var mViewModel: SearchViewModel

    @Inject
    lateinit var mResultsAdapter: ResultsAdapter

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        inject()

        setHasOptionsMenu(true)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.search_frag, container, false)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        NavigationUI.setupWithNavController(
            toolbar,
            findNavController(),
            AppBarConfiguration(findNavController().graph)
        )

        return view
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
                findNavController().navigate(
                    SearchFragmentDirections.actionSearchToMovieDetails(it)
                )
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