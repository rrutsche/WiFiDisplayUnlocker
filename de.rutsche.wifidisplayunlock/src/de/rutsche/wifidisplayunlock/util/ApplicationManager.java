package de.rutsche.wifidisplayunlock.util;

import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;

public class ApplicationManager {

    private static ApplicationManager instance;
    private KeyguardManager keyguardManager;
    private KeyguardLock lock;

    private ApplicationManager() {
        keyguardManager = (KeyguardManager) WiFiApplication.getAppContext()
                .getSystemService(Context.KEYGUARD_SERVICE);
        lock = keyguardManager.newKeyguardLock(Context.KEYGUARD_SERVICE);
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
}
