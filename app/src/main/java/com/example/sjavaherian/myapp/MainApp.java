package com.example.sjavaherian.myapp;

import com.facebook.stetho.Stetho;
import com.sjavaherian.core.BaseApplication;
import com.sjavaherian.movie.dagger.DaggerMovieMainComponent;
import com.sjavaherian.movie.dagger.MovieMainComponent;
import com.sjavaherian.task.common.DaggerTaskMainComponent;
import com.sjavaherian.task.common.TaskMainComponent;
import org.jetbrains.annotations.NotNull;

public class MainApp extends BaseApplication
        implements com.sjavaherian.movie.Injector, com.sjavaherian.task.Injector {

    private TaskMainComponent taskMainComponent;
    private MovieMainComponent movieMainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);

        taskMainComponent = DaggerTaskMainComponent
                .builder()
                .appComponent(getAppComponent())
                .build();

        movieMainComponent = DaggerMovieMainComponent
                .builder()
                .appComponent(getAppComponent())
                .build();
    }

    @NotNull
    @Override
    public MovieMainComponent getMovieComponent() {
        return movieMainComponent;
    }

    @NotNull
    @Override
    public TaskMainComponent getTaskComponent() {
        return taskMainComponent;
    }
}
