package de.rutsche.wifidisplayunlock.util;

import android.app.Application;
import android.content.Context;

public class WiFiApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        WiFiApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return WiFiApplication.context;
    }

}
