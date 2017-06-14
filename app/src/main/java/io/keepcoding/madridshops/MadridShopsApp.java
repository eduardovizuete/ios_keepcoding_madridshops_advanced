package io.keepcoding.madridshops;

import android.app.Application;
import android.util.Log;

public class MadridShopsApp extends Application {

    private final String APP_NAME = MadridShopsApp.class.getCanonicalName();

    @Override
    public void onCreate() {
        super.onCreate();

        // init app

        Log.d(APP_NAME, "App starting");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

        // low memory: dump something
    }
}
