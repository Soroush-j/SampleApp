package com.sjavaherian.movie.main.dagger;

import android.arch.lifecycle.ViewModelProviders;
import com.sjavaherian.movie.MovieViewModelFactory;
import com.sjavaherian.movie.main.MoviesFragment;
import com.sjavaherian.movie.main.MoviesViewModel;
import com.sjavaherian.movie.main.adapters.MoviesAdapter;
import com.sjavaherian.movie.main.adapters.SpinnerAdapter;
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
