package com.example.sjavaherian.myapp.movie.movies

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.databinding.ObservableField
import android.util.Log
import android.view.View
import com.example.sjavaherian.myapp.movie.database.MovieDao
import com.example.sjavaherian.myapp.movie.movies.network.MovieBoundaryCallback
import com.example.sjavaherian.myapp.movie.movies.network.MoviesApiEndPoint
import com.example.sjavaherian.myapp.movie.movies.network.pojo.GenreRetro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel(
    private val mMoviesApiEndPoint: MoviesApiEndPoint,
    private val mMovieDao: MovieDao,
    private val mBoundaryCallback: MovieBoundaryCallback
) : ViewModel() {

    // todo: handle empty successful responses from the server.

    fun start() {
        loadAllGenres()
    }

    val messageVisibility = ObservableField<Int>(View.GONE)
    val message = ObservableField<String>("Loading")

    fun updateLoadingState(msg: String, msgVis: Int) {
        messageVisibility.set(msgVis)
        message.set(msg)
    }

    private val config = PagedList.Config.Builder()
        .setInitialLoadSizeHint(60)
        .setPageSize(20)
        .setPrefetchDistance(10)
        .build()

    val movies = LivePagedListBuilder(mMovieDao.loadAllMovies(), config)
        .setBoundaryCallback(mBoundaryCallback)
        .build()

    // todo: the items are not sorted by id. I don't know how they are sorted. have to check it later

    private val mGenres = MutableLiveData<List<GenreRetro>>()
    val genres: LiveData<List<GenreRetro>>
        get() = mGenres

    private fun loadAllGenres() {
        mMoviesApiEndPoint.getAllGenres().enqueue(object : Callback<List<GenreRetro>?> {
            override fun onFailure(call: Call<List<GenreRetro>?>, t: Throwable) {
                Log.d(TAG, "failed to load genres", t)
            }

            override fun onResponse(call: Call<List<GenreRetro>?>, response: Response<List<GenreRetro>?>) {
                mGenres.value = response.body()
                Log.d(TAG, "genres were successfully loaded, count: " + response.body()?.size)
            }
        })
    }

    fun loadGenre(id: Int) {
        Log.d(TAG, "loading specific genres is not supported for now.")

//        mMoviesApiEndPoint.getMoviesByGenre(id)
//            .enqueue(object : Callback<GenreResponse> {
//                override fun onFailure(call: Call<GenreResponse>, t: Throwable) {
//                    t.printStackTrace()
//                    Log.e(TAG, "get movies by genre failed.", t)
//                }
//
//                override fun onResponse(call: Call<GenreResponse>, response: Response<GenreResponse>) {
//                    Log.d(TAG, "get movies by genre was successful.")
//
//                    val movies = response.body()?.movies
//                    mMoviesLive.value = movies
//                    updateLoadingState("", View.GONE)
//                }
//            })
    }

    fun onStop() {
        Log.d(TAG, "view model onStop called.")
        mBoundaryCallback.disposable.clear()
    }

    companion object {
        private val TAG = "tag MoviesViewModel"
    }
}
