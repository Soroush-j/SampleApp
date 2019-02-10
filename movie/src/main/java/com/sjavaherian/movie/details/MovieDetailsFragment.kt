package com.sjavaherian.movie.details

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.sjavaherian.movie.inject
import com.sjavaherian.core.database.movies.Movie
import com.sjavaherian.movie.databinding.MovieDetailsFragmentBinding
import kotlinx.android.synthetic.main.movie_details_fragment.*
import javax.inject.Inject

class MovieDetailsFragment : Fragment() {

    companion object {
        const val TAG: String = "tag MovieDetailsFragmen"
        private const val ARG_ID = "ARG_ID"
//        fun newInstance(id: Int) = MovieDetailsFragment().apply {
//            arguments = Bundle().apply { putInt(ARG_ID, id) }
//        }
    }
    //todo: save images in database
    // todo: change font size dynamically

    @Inject
    lateinit var mMovieBannerAdapter: MovieBannerAdapter

    @Inject
    lateinit var mViewModel: MovieDetailsViewModel

    private lateinit var mBinding: MovieDetailsFragmentBinding

    private lateinit var mNavController: NavController

    lateinit var toolbar: Toolbar
    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = MovieDetailsFragmentBinding.inflate(layoutInflater, container, false)
        mBinding.apply { viewModel = mViewModel }

        mNavController = findNavController()

        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        inject()
//        val id: Int = MovieDetailsFragmentArgs.fromBundle(arguments!!).id todo
        mViewModel.start(id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        toolbar = view.findViewById(R.id.movie_details_toolbar)
//        (activity as AppCompatActivity).setSupportActionBar(toolbar)
//        toolbar.title = ""

        movie_details_collapsing_toolbar.setupWithNavController(
            movie_details_toolbar, mNavController,
            AppBarConfiguration(mNavController.graph)
        )
        movie_details_toolbar.setupWithNavController(mNavController, AppBarConfiguration(mNavController.graph))

        movie_details_viewpager.adapter = mMovieBannerAdapter
        movie_details_indicator.setupWithViewPager(movie_details_viewpager)
    }

    override fun onStart() {
        super.onStart()

        mViewModel.mMovie.observe(this, Observer<Movie> { details ->
            mBinding.movie = details
            mMovieBannerAdapter.addImagesUrl(details)
            if (details?.images == null) movie_details_indicator.visibility = View.INVISIBLE
            Log.d(TAG, "title: ${details?.title}")
            details?.title?.let {
                //                toolbar.title = it
                movie_details_collapsing_toolbar.title = it
            }
        })
    }

    override fun onStop() {
        super.onStop()
        mViewModel.onStop()
    }

}
