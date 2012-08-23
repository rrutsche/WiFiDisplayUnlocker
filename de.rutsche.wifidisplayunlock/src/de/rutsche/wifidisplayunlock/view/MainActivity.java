package de.rutsche.wifidisplayunlock.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import de.rutsche.wifidisplayunlock.R;
import de.rutsche.wifidisplayunlock.util.ApplicationManager;

public class MainActivity extends PreferenceActivity implements
        SharedPreferences.OnSharedPreferenceChangeListener {

    private SharedPreferences prefs;
    private Intent intent;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);
        boolean serviceChecked = prefs.getBoolean(getString(R.string.pref_key),
                true);

        handleServiceActivation(serviceChecked);

    }

    private void handleServiceActivation(boolean serviceChecked) {
        intent = ApplicationManager.getInstance().getServiceIntent();
        if (serviceChecked) {
            startService(intent);
        } else {
            stopService(intent);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
            String key) {

        boolean serviceChecked = sharedPreferences.getBoolean(
                getString(R.string.pref_key), true);
        handleServiceActivation(serviceChecked);
    }
}