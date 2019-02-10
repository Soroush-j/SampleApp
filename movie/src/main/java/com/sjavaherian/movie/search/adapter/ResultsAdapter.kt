package com.sjavaherian.movie.search.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sjavaherian.core.SingleLiveEvent
import com.sjavaherian.core.database.movies.Movie
import com.sjavaherian.movie.R
import com.sjavaherian.movie.databinding.MovieItemBinding
import com.sjavaherian.movie.main.MovieClickListener
import com.squareup.picasso.Picasso

class ResultsAdapter : ListAdapter<Movie, ResultsAdapter.ViewHolder>(DIFF_UTIL) {

    val openMovieDetailsEvent = SingleLiveEvent<Int>()

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(p0: Movie, p1: Movie): Boolean =
                p0.id == p1.id

            override fun areContentsTheSame(p0: Movie, p1: Movie): Boolean =
                p0 == p1
        }
        private const val TAG = "tag ResultsAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder =
        ViewHolder(MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    inner class ViewHolder(private val mBinding: MovieItemBinding) : RecyclerView.ViewHolder(mBinding.root) {

        fun bind(movie: Movie?) {
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