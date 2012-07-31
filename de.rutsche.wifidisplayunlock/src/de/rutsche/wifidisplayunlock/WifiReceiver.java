package de.rutsche.wifidisplayunlock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.widget.Toast;
import de.rutsche.wifidisplayunlock.service.WifiService;

public class WifiReceiver extends BroadcastReceiver {

    WifiService wifiDemo;

    public WifiReceiver(WifiService wifiService) {
        super();
        this.wifiDemo = wifiService;
    }

    @Override
    public void onReceive(Context c, Intent intent) {

        String action = intent.getAction();

        if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(action)) {
            Toast scanToast = Toast.makeText(c, "scan results ready",
                    Toast.LENGTH_SHORT);
            scanToast.show();
        }

        NetworkInfo networkInfo = (NetworkInfo) intent
                .getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);

        if (networkInfo != null
                && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            Toast connectToast = Toast.makeText(c, "connection established",
                    Toast.LENGTH_SHORT);
            connectToast.show();
        } else if (networkInfo != null
                && networkInfo.getType() != ConnectivityManager.TYPE_WIFI) {
            Toast connectToast = Toast.makeText(c, "no connection",
                    Toast.LENGTH_SHORT);
            connectToast.show();
        }
    }
}
