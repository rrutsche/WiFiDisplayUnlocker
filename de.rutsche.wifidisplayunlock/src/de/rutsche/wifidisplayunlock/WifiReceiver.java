package de.rutsche.wifidisplayunlock;

import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.widget.Toast;

public class WifiReceiver extends BroadcastReceiver {

    private KeyguardManager keyguardManager;
    private KeyguardLock lock;

    public WifiReceiver() {
        super();
    }

    @Override
    public void onReceive(Context c, Intent intent) {

        String action = intent.getAction();

        keyguardManager = (KeyguardManager) c
                .getSystemService(Context.KEYGUARD_SERVICE);
        if (lock == null) {
            lock = keyguardManager.newKeyguardLock(Context.KEYGUARD_SERVICE);
        }

        if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(action)) {
            Toast scanToast = Toast.makeText(c, "scan results ready",
                    Toast.LENGTH_SHORT);
            scanToast.show();
        }

        NetworkInfo networkInfo = (NetworkInfo) intent
                .getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);

        if (networkInfo != null
                && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            // connection active
            Toast connectToast = Toast.makeText(c, "connection established",
                    Toast.LENGTH_SHORT);
            connectToast.show();
            lock.disableKeyguard();
        } else if (networkInfo != null
                && networkInfo.getType() != ConnectivityManager.TYPE_WIFI) {
            // no connection
            Toast connectToast = Toast.makeText(c, "no connection",
                    Toast.LENGTH_SHORT);
            connectToast.show();
            lock.reenableKeyguard();
        }
    }
}
