package com.sjavaherian.movie.details.dagger;

import android.arch.lifecycle.ViewModelProviders;
import com.sjavaherian.movie.MovieViewModelFactory;
import com.sjavaherian.movie.details.MovieDetailsFragment;
import com.sjavaherian.movie.details.MovieDetailsViewModel;
import com.sjavaherian.movie.details.MovieBannerAdapter;
import dagger.Module;
import dagger.Provides;

@Module
public class MovieDetailsModule {

    private MovieDetailsFragment mMovieDetailsFragment;

    public MovieDetailsModule(MovieDetailsFragment movieDetailsFragment) {
        mMovieDetailsFragment = movieDetailsFragment;
    }

    @Provides
    MovieDetailsViewModel providesMovieDetailsViewModel(MovieViewModelFactory factory) {
        return ViewModelProviders.of(mMovieDetailsFragment, factory).get(MovieDetailsViewModel.class);
    }

    @Provides
    MovieBannerAdapter provideViewPagerAdapter() {
        return new MovieBannerAdapter(mMovieDetailsFragment.requireContext());
    }
}
