package com.example.sjavaherian.myapp.movie.moviedetails

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sjavaherian.myapp.R
import com.example.sjavaherian.myapp.databinding.MovieDetailsFragmentBinding
import com.example.sjavaherian.myapp.movie.database.Movie
import com.example.sjavaherian.myapp.movie.inject
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
//todo: loading indicator while loading details
    @Inject
    lateinit var mMovieBannerAdapter: MovieBannerAdapter

    @Inject
    lateinit var mViewModel: MovieDetailsViewModel

    lateinit var mBinding: MovieDetailsFragmentBinding

    lateinit var toolbar: Toolbar
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        inject()

        val id: Int = MovieDetailsFragmentArgs.fromBundle(arguments!!).id
        mViewModel.start(id)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = MovieDetailsFragmentBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar = view.findViewById(R.id.movie_details_toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.title = ""

        movie_details_viewpager.adapter = mMovieBannerAdapter
        movie_details_indicator.setupWithViewPager(movie_details_viewpager)
    }

    override fun onStart() {
        super.onStart()

        mViewModel.mMovie.observe(this, Observer<Movie> { details ->
            mBinding.movie = details
            details?.images?.let { mMovieBannerAdapter.addImagesUrl(it) }
            details?.title?.let {
                toolbar.title = it
            }
        })
    }

    override fun onStop() {
        super.onStop()
        mViewModel.onStop()
    }

}
