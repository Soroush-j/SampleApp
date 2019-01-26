package com.example.sjavaherian.myapp.movie.moviedetails.dagger;

import android.arch.lifecycle.ViewModelProviders;
import com.example.sjavaherian.myapp.movie.MovieViewModelFactory;
import com.example.sjavaherian.myapp.movie.moviedetails.MovieDetailsFragment;
import com.example.sjavaherian.myapp.movie.moviedetails.MovieDetailsViewModel;
import com.example.sjavaherian.myapp.movie.moviedetails.MovieBannerAdapter;
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
