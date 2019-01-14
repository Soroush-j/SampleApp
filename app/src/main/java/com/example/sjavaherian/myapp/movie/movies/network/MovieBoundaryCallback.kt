package com.example.sjavaherian.myapp.movie.movies.network

import android.arch.paging.PagedList
import android.util.Log
import com.example.sjavaherian.myapp.movie.database.Movie
import com.example.sjavaherian.myapp.movie.database.MovieDao
import com.example.sjavaherian.myapp.movie.movies.network.pojo.MovieRetro
import com.example.sjavaherian.myapp.movie.movies.network.pojo.MoviesResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieBoundaryCallback(
    private val mMoviesApiEndPoint: MoviesApiEndPoint,
    private val mMovieDao: MovieDao
) : PagedList.BoundaryCallback<Movie>() {

    private val TAG = "tag MovieBoundaryCallba"
    // todo: is disposable handled correctly?
    val disposable = CompositeDisposable()

    override fun onZeroItemsLoaded() {
        Log.d(TAG, "onZeroItemsLoaded")
        loadMoviesFromServer(1)
    }

    override fun onItemAtEndLoaded(itemAtEnd: Movie) {
        Log.d(TAG, "onItemAtEndLoaded")
        loadMoviesFromServer(itemAtEnd.page + 1)
    }

    private fun loadMoviesFromServer(page: Int) {
        mMoviesApiEndPoint.loadAllMovies(page).enqueue(object : Callback<MoviesResponse> {

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.d(TAG, "loading movies from server failed!")
                Log.e(TAG, "error message: " + t.message)
                t.printStackTrace()
            }

            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                if (response.isSuccessful) {

                    val metadata = response.body()?.metadata
                    val data = response.body()?.data

                    Log.d(TAG, "data: ${data?.size}")
                    Log.d(TAG, "metadata: ${metadata?.toString()}")

                    var pageCount: Int = 1
                    var currentPage: Int = 1
                    metadata?.let {
                        pageCount = it.pageCount
                        currentPage = it.currentPage
                    }

                    disposable.add(
                        Observable.fromIterable(data)
                            .subscribeOn(Schedulers.io())
                            .filter { movie -> filterMovies(movie) }
                            .map { movie -> mapMoviesToDatabase(movie, currentPage, pageCount) }
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({}, { Log.e(TAG, it.message, it) }, {
                                Log.d(
                                    TAG, "movies were loaded to the db. current page: $currentPage"
                                )
                            })
                    )
                } else {
                    Log.d(
                        TAG, "response was unsuccessful,\n error: ${response.errorBody()} \n" +
                                " message: ${response.message()}"
                    )
                }
            }
        })
    }

    private fun mapMoviesToDatabase(m: MovieRetro, currentPage: Int, pageCount: Int) {
        val movie = Movie(
            null, m.id, m.title, m.poster, m.year, m.country, m.imdbRating,
            m.genres, m.images, page = currentPage, totalPageCount = pageCount
        )
        mMovieDao.saveMovie(movie)
        Log.d(TAG, "movie id:${m.id} was saved.")
    }

    private fun filterMovies(movie: MovieRetro): Boolean {
        val id = movie.id
        return if (movie.id != null) {
            isMovieInDatabase(movie.id)
        } else {
            Log.d(TAG, "movie id:$id was filtered out.")
            false
        }
    }

    private fun isMovieInDatabase(id: Int): Boolean {
        val result = mMovieDao.isMovieInDatabase(id).isNullOrEmpty()
        if (result) Log.d(TAG, "Nothing happened to movie id:$id.") else Log.d(TAG, "movie id:$id was filtered out.")
        return result
    }
}