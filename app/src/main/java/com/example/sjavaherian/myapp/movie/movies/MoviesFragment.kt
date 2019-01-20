package com.example.sjavaherian.myapp.movie.movies

import android.app.SearchManager
import android.app.Service
import android.arch.lifecycle.Observer
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.*
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.Spinner
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.sjavaherian.myapp.R
import com.example.sjavaherian.myapp.databinding.MoviesFragmentBinding
import com.example.sjavaherian.myapp.movie.inject
import com.example.sjavaherian.myapp.movie.movies.adapters.MoviesAdapter
import com.example.sjavaherian.myapp.movie.movies.adapters.SpinnerAdapter
import kotlinx.android.synthetic.main.movies_fragment.*
import javax.inject.Inject

class MoviesFragment : Fragment() {
    // todo: save the selected genre when closing this fragment
    // todo: save genres in db
    // todo: check integrity of genres with server at the beginning.
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

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        setHasOptionsMenu(true)
        inject()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.movies_fragment, container, false)
        mBinding.viewModel = mViewModel

        mNavController = findNavController()
        mViewModel.start()

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.title = "Movies"

        movies_rv.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = mAdapter
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }
        movies_loading_indicator.visibility = View.VISIBLE
    }

    override fun onStart() {
        super.onStart()

        mViewModel.movies.observe(this, Observer { pagedList ->
            movies_loading_indicator.visibility = View.INVISIBLE
            Log.d(TAG, "paged list size: " + pagedList?.size)
            val size = pagedList?.size
            if (size == null || size <= 0) {
                mViewModel.updateLoadingState("Loading failed.", View.VISIBLE)
            } else mViewModel.updateLoadingState("Loading Successful.", View.GONE)

            mAdapter.submitList(pagedList)
        })

        mViewModel.genres.observe(this, Observer { genres ->
            genres?.let { mSpinnerAdapter.addAll(it) }
        })

        mAdapter.openMovieDetailsEvent.observe(this, Observer { id ->
            id?.let {
                mNavController.navigate(
                    MoviesFragmentDirections.actionMoviesToMovieDetails(id)
                )
            }
        })
    }

    override fun onStop() {
        super.onStop()
        mViewModel.onStop()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater?.inflate(R.menu.menu_movies_frag, menu)

        val spinner = menu?.findItem(R.id.menu_movie_genres)?.actionView as Spinner
        spinner.adapter = mSpinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                mViewModel.updateLoadingState("", View.GONE)
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val genre = mSpinnerAdapter.getItem(position)
                Log.d(TAG, genre.toString())
                Log.d(TAG, "genre id: ${genre.id}")
                mViewModel.loadGenre(genre.id)
            }
        }

        val searchView = menu.findItem(R.id.menu_movie_search)?.actionView as SearchView
        val searchManager = activity?.getSystemService(Service.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
    }
}