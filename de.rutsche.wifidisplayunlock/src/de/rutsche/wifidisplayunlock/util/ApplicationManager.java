package de.rutsche.wifidisplayunlock.util;

import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;
import android.content.Intent;
import de.rutsche.wifidisplayunlock.service.WifiService;

public class ApplicationManager {

    private static ApplicationManager instance;
    private KeyguardManager keyguardManager;
    private KeyguardLock lock;
    private Intent serviceIntent;

    private ApplicationManager() {
        keyguardManager = (KeyguardManager) WiFiApplication.getAppContext()
                .getSystemService(Context.KEYGUARD_SERVICE);
        lock = keyguardManager.newKeyguardLock(Context.KEYGUARD_SERVICE);
        serviceIntent = new Intent(WiFiApplication.getAppContext(),
                WifiService.class);
    }

    public static ApplicationManager getInstance() {
        if (instance == null) {
            instance = new ApplicationManager();
        }
        return instance;
    }

    public KeyguardLock getLock() {
        return lock;
    }

    public Intent getServiceIntent() {
        return serviceIntent;
    }

}
