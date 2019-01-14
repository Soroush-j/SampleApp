package com.example.sjavaherian.myapp.movie.moviedetails

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.sjavaherian.myapp.movie.database.Movie
import com.example.sjavaherian.myapp.movie.database.MovieDao
import com.example.sjavaherian.myapp.movie.moviedetails.network.MovieDetailsApiEndpoint
import com.example.sjavaherian.myapp.movie.moviedetails.network.MovieDetailsRetro
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsViewModel(
    private val mDetailsEndpoint: MovieDetailsApiEndpoint,
    private val mMovieDao: MovieDao
) : ViewModel() {

    companion object {
        const val TAG = "tag MovieDetailsViewMod"
    }

    private val mDisposable = CompositeDisposable()
    private var mId: Int = 0

    val mMovie = MutableLiveData<Movie>()
//            = mMovieDao.getMovieByIdLive(mId)
//    val movie: LiveData<Movie>
//        get() = mMovie

    fun start(id: Int) {
        mId = id

        mDisposable.add(
            mMovieDao.getMovieByIdRx(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .distinctUntilChanged { old, new -> old == new }
                .doOnNext { checkDatabaseResult(id, it) }
                .doOnComplete { Log.d(TAG, "fetching movie detail from database has just completed.") }
                .subscribe()
        )
    }

    var mKey: Int = 0
    private fun checkDatabaseResult(id: Int, m: Movie): Boolean {
        mMovie.value = m
        mKey = m.key!!
        return if (m.fullyLoaded) true else {
            getMovieFromServer(id)
            false
        }
    }

    private fun getMovieFromServer(id: Int) {
        mDetailsEndpoint.getMovieDetailsById(id).enqueue(object : Callback<MovieDetailsRetro> {
            override fun onFailure(call: Call<MovieDetailsRetro>, t: Throwable) {
                Log.d(TAG, "fetching movie detail failed! the movie mId is: $id")
            }

            override fun onResponse(call: Call<MovieDetailsRetro>, response: Response<MovieDetailsRetro>) {
                val mov = response.body()
                Log.d(TAG, "onResponse: " + mov.toString())
                mov?.let {
                    val m = mov.convertToMovie(mKey)
                    Log.d(TAG, "onResponse: " + m.toString())
                    mDisposable.add(
                        Observable.just(m)
                            .subscribeOn(Schedulers.io())
                            .map { mMovieDao.updateMovie(m) }
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnComplete { Log.d(TAG, "movie from server is successfully saved.") }
                            .subscribe()
                    )
                }
            }
        })
    }

    fun onStop() {
        mDisposable.clear()
    }
}
