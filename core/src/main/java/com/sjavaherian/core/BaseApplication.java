package com.sjavaherian.core;

import android.app.Application;
import com.sjavaherian.core.dagger.AppComponent;
import com.sjavaherian.core.dagger.DaggerAppComponent;

public class BaseApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
