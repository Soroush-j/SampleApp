package com.example.sjavaherian.myapp.movie.dagger

import android.app.Application
import com.example.sjavaherian.myapp.data.AppDatabase
import com.example.sjavaherian.myapp.movie.ViewModelFactoryMovie
import com.example.sjavaherian.myapp.movie.database.MovieDao
import com.example.sjavaherian.myapp.movie.moviedetails.network.MovieDetailsApiEndpoint
import com.example.sjavaherian.myapp.movie.moviedetails.network.MovieDetailsApiEndpoint.Companion.MOVIE_DETAILS_ENDPOINT
import com.example.sjavaherian.myapp.movie.movies.network.MovieBoundaryCallback
import com.example.sjavaherian.myapp.movie.movies.network.MoviesApiEndPoint
import com.example.sjavaherian.myapp.movie.movies.network.MoviesApiEndPoint.Companion.MOVIES_API_BASE_URL
import com.example.sjavaherian.myapp.movie.movies.network.MoviesApiEndPoint.Companion.MOVIES_ENDPOINT
import com.example.sjavaherian.myapp.movie.movies.network.MoviesApiEndPoint.Companion.MOVIE_API_RETROFIT
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class MovieMainModule {

    @Provides
    fun providesViewModelFactoryMovie(
        app: Application,
        @Named(MOVIES_ENDPOINT) moviesEndPoint: MoviesApiEndPoint,
        @Named(MOVIE_DETAILS_ENDPOINT) detailsEndpoint: MovieDetailsApiEndpoint,
        movieDao: MovieDao,
        movieBoundaryCallback: MovieBoundaryCallback
    ): ViewModelFactoryMovie {
        return ViewModelFactoryMovie(app, moviesEndPoint, detailsEndpoint, movieDao, movieBoundaryCallback)
    }

    @Named(MOVIE_API_RETROFIT)
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(MOVIES_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Named(MOVIES_ENDPOINT)
    @Provides
    fun provideMoviesEndPoint(@Named(MOVIE_API_RETROFIT) retrofit: Retrofit): MoviesApiEndPoint =
        retrofit.create(MoviesApiEndPoint::class.java)


    @Named(MOVIE_DETAILS_ENDPOINT)
    @Provides
    fun provideMovieDetailsEndpoint(@Named(MOVIE_API_RETROFIT) retrofit: Retrofit): MovieDetailsApiEndpoint =
        retrofit.create(MovieDetailsApiEndpoint::class.java)

    @Provides
    fun provideMovieDao(appDatabase: AppDatabase): MovieDao = appDatabase.movieDao()

    @Provides
    fun provideMovieBoundaryCallback(@Named(MOVIES_ENDPOINT) moviesEndPoint: MoviesApiEndPoint,
                                     movieDao: MovieDao): MovieBoundaryCallback =
        MovieBoundaryCallback(moviesEndPoint,movieDao)

}