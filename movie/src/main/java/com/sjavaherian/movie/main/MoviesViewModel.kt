package com.sjavaherian.movie.main

import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.databinding.ObservableField
import android.util.Log
import android.view.View
import com.sjavaherian.movie.main.network.MovieBoundaryCallback
import com.sjavaherian.movie.main.network.MoviesApiEndPoint
import com.sjavaherian.movie.main.network.pojo.GenreRetro
import com.sjavaherian.core.database.movies.Genre
import com.sjavaherian.core.database.movies.GenreDao
import com.sjavaherian.core.database.movies.MovieDao
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel(
    private val mMoviesApiEndPoint: MoviesApiEndPoint,
    private val mMovieDao: MovieDao,
    private val mGenreDao: GenreDao,
    private val mBoundaryCallback: MovieBoundaryCallback
) : ViewModel() {

    // todo: handle empty successful responses from the server.
    private val TAG = "tag MoviesViewModel"

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

    val genres = mGenreDao.loadAllGenres()

    private val mDisposable = CompositeDisposable()

    private fun loadAllGenres() {
        mMoviesApiEndPoint.getAllGenres().enqueue(object : Callback<List<GenreRetro>?> {
            override fun onFailure(call: Call<List<GenreRetro>?>, t: Throwable) {
                Log.d(TAG, "failed to load genres", t)
            }

            override fun onResponse(call: Call<List<GenreRetro>?>, response: Response<List<GenreRetro>?>) {

                val internet = response.body()?.map { it.convertToGenre() }
                var final: List<Genre>?

                final = internet?.filter { g ->
                    return@filter shouldBeFiltered(g)
                }

                Log.d(TAG, "final : $final")

                if (final != null) mDisposable.add(
                    Observable.just(final)
                        .subscribeOn(Schedulers.io())
                        .map { list ->
                            list.map { mGenreDao.deleteById(it.id) }
                            list.map { mGenreDao.insert(it) }
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnComplete { Log.d(TAG, "finished loading genres into database.") }
                        .subscribe()
                )
                Log.d(TAG, "genres, count: " + response.body()?.size)
            }
        })
    }

    private fun shouldBeFiltered(g: Genre): Boolean {
        genres.value?.let {
            for (i in it) {
                if (i.equals(g)) return false
            }
        }
        return true
    }

    fun onStop() {
        Log.d(TAG, "view model onStop.")
        mBoundaryCallback.disposable.clear()
    }
}
