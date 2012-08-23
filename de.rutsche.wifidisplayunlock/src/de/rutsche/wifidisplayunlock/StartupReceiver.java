package de.rutsche.wifidisplayunlock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import de.rutsche.wifidisplayunlock.util.ApplicationManager;

public class StartupReceiver extends BroadcastReceiver {

    private SharedPreferences prefs;

    public StartupReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        boolean serviceChecked = prefs.getBoolean(
                context.getString(R.string.pref_key), true);
        if (serviceChecked) {
            context.startService(ApplicationManager.getInstance()
                    .getServiceIntent());
        }
    }

}
