package de.rutsche.wifidisplayunlock.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.Toast;
import de.rutsche.wifidisplayunlock.R;
import de.rutsche.wifidisplayunlock.service.WifiService;

public class ViewPagerActivity extends PreferenceActivity implements
        SharedPreferences.OnSharedPreferenceChangeListener {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        boolean serviceChecked = prefs.getBoolean("pref_sync", true);

        if (serviceChecked) {
            startService(new Intent(this, WifiService.class));
        } else {
            stopService(new Intent(this, WifiService.class));
        }

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
            String key) {

        // boolean value = sharedPreferences.getBoolean("pref_sync", true);

        Toast toast = Toast.makeText(this, "prefs changed", Toast.LENGTH_SHORT);
        toast.show();
    }
}