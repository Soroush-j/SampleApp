package com.example.sjavaherian.myapp.movie.movies.dagger;

import android.arch.lifecycle.ViewModelProviders;
import com.example.sjavaherian.myapp.movie.MovieViewModelFactory;
import com.example.sjavaherian.myapp.movie.movies.MoviesFragment;
import com.example.sjavaherian.myapp.movie.movies.MoviesViewModel;
import com.example.sjavaherian.myapp.movie.movies.adapters.MoviesAdapter;
import com.example.sjavaherian.myapp.movie.movies.adapters.SpinnerAdapter;
import dagger.Module;
import dagger.Provides;

@Module
public class MoviesModule {


    private MoviesFragment mMoviesFragment;

    public MoviesModule(MoviesFragment fragment) {
        mMoviesFragment = fragment;

    }

    @Provides
    MoviesViewModel providesMoviesViewModel(MovieViewModelFactory factory) {
        return ViewModelProviders.of(mMoviesFragment, factory).get(MoviesViewModel.class);
    }

    @Provides
    MoviesAdapter providesMoviesAdapter() {
        return new MoviesAdapter();
    }

    @Provides
    SpinnerAdapter provideSpinnerAdapter() {
        return new SpinnerAdapter(mMoviesFragment.requireContext());
    }
}
