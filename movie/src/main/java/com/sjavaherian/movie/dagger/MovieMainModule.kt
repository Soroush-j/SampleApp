package com.example.sjavaherian.myapp.movie.dagger

import android.app.Application
import com.sjavaherian.movie.MovieViewModelFactory
import com.sjavaherian.core.database.movies.GenreDao
import com.sjavaherian.core.database.movies.MovieDao
import com.sjavaherian.movie.details.network.MovieDetailsApiEndpoint
import com.sjavaherian.movie.details.network.MovieDetailsApiEndpoint.Companion.MOVIE_DETAILS_ENDPOINT
import com.sjavaherian.movie.main.network.MovieBoundaryCallback
import com.sjavaherian.movie.main.network.MoviesApiEndPoint
import com.sjavaherian.movie.main.network.MoviesApiEndPoint.Companion.MOVIES_API_BASE_URL
import com.sjavaherian.movie.main.network.MoviesApiEndPoint.Companion.MOVIES_ENDPOINT
import com.sjavaherian.movie.main.network.MoviesApiEndPoint.Companion.MOVIE_API_RETROFIT
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
        genreDao: GenreDao,
        movieBoundaryCallback: MovieBoundaryCallback
    ): MovieViewModelFactory {
        return MovieViewModelFactory(app, moviesEndPoint, detailsEndpoint, movieDao,genreDao, movieBoundaryCallback)
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
    fun provideMovieBoundaryCallback(
        @Named(MOVIES_ENDPOINT) moviesEndPoint: MoviesApiEndPoint,
        movieDao: MovieDao
    ): MovieBoundaryCallback =
        MovieBoundaryCallback(moviesEndPoint, movieDao)
}