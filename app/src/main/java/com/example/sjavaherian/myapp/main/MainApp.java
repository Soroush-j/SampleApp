package com.example.sjavaherian.myapp.main;

import android.app.Application;
import android.content.Context;
import com.example.sjavaherian.myapp.dagger.AppComponent;
import com.example.sjavaherian.myapp.dagger.DaggerAppComponent;
import com.example.sjavaherian.myapp.movie.dagger.DaggerMovieMainComponent;
import com.example.sjavaherian.myapp.movie.dagger.MovieMainComponent;
import com.example.sjavaherian.myapp.search.dagger.SearchComponent;
import com.example.sjavaherian.myapp.task.DaggerTaskMainComponent;
import com.example.sjavaherian.myapp.task.TaskMainComponent;
import com.facebook.stetho.Stetho;

public class MainApp extends Application {

	private AppComponent mAppComponent;
	private TaskMainComponent mTaskMainComponent;
	private MovieMainComponent mMovieMainComponent;

	private static SearchComponent mSearchComponent;

	public static TaskMainComponent getTaskMainComponent(Context context) {
		return ((MainApp) context).mTaskMainComponent;
	}

	public static AppComponent getAppComponent(Context context) {
		return ((MainApp) context).mAppComponent;
	}

	public static MovieMainComponent getMovieMainComponent(Context context) {
		return ((MainApp) context).mMovieMainComponent;
	}

	public static SearchComponent getSearchComponent() {
		return mSearchComponent;
	}

	public static void setSearchComponent(SearchComponent mSearchComponent) {
		MainApp.mSearchComponent = mSearchComponent;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Stetho.initializeWithDefaults(this);

		mAppComponent = DaggerAppComponent
				.builder()
				.application(this)
				.build();

		mTaskMainComponent = DaggerTaskMainComponent
				.builder()
				.appComponent(mAppComponent)
				.build();

		mMovieMainComponent = DaggerMovieMainComponent
				.builder()
				.appComponent(mAppComponent)
				.build();
	}
}
