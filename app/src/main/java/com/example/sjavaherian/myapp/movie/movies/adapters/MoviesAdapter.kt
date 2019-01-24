package com.example.sjavaherian.myapp.movie.movies.adapters

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.sjavaherian.myapp.R
import com.example.sjavaherian.myapp.common.SingleLiveEvent
import com.example.sjavaherian.myapp.databinding.MovieItemBinding
import com.example.sjavaherian.myapp.movie.database.Movie
import com.example.sjavaherian.myapp.movie.movies.MovieClickListener
import com.squareup.picasso.Picasso

class MoviesAdapter() : PagedListAdapter<Movie, MoviesAdapter.ViewHolder>(DIFF_ITEM) {

    val openMovieDetailsEvent = SingleLiveEvent<Int>()
    val positionLive = MutableLiveData<Int>()
    private val TAG = "tag MoviesAdapter"

    companion object {
        val DIFF_ITEM = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(p0: Movie, p1: Movie): Boolean =
                p0.id == p1.id

            override fun areContentsTheSame(p0: Movie, p1: Movie): Boolean =
                p0 == p1
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val binding: MovieItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.movie_item, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // todo: do something if item is null?!
//        Log.d(TAG, "onBindViewHolder, $position")
        positionLive.value = position
        val movie = getItem(position)
        holder.bind(movie)
    }

    inner class ViewHolder(private val mBinding: MovieItemBinding) : RecyclerView.ViewHolder(mBinding.root) {

        fun bind(movie: Movie?) {
//            Log.d(TAG, "ViewHolder, $adapterPosition")
            mBinding.movie = movie

            mBinding.listener = object : MovieClickListener {
                override fun onMovieClicked(movie: Movie) {
                    openMovieDetailsEvent.value = movie.id
                }
            }
            val picasso = Picasso.get()
            picasso.setIndicatorsEnabled(true)
            picasso
                .load(movie?.poster)
                .placeholder(R.drawable.ic_movie)
                .resize(100, 200)
                .into(mBinding.movieItemPoster)

            mBinding.executePendingBindings()
        }
    }


}