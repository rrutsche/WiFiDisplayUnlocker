package de.rutsche.wifidisplayunlock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import de.rutsche.wifidisplayunlock.util.ApplicationManager;

public class WifiReceiver extends BroadcastReceiver {

    public WifiReceiver() {
        super();
    }

    @Override
    public void onReceive(Context c, Intent intent) {

        String action = intent.getAction();

        if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(action)) {
        }

        NetworkInfo networkInfo = (NetworkInfo) intent
                .getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);

        if (networkInfo != null
                && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            // connection active
            ApplicationManager.getInstance().getLock().disableKeyguard();
        } else if (networkInfo != null
                && networkInfo.getType() != ConnectivityManager.TYPE_WIFI) {
            // no connection
            ApplicationManager.getInstance().getLock().reenableKeyguard();
        }
    }
}
