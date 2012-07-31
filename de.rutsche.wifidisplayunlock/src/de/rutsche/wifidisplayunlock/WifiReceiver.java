package de.rutsche.wifidisplayunlock;

import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;
import de.rutsche.wifidisplayunlock.service.WifiService;

public class WifiReceiver extends BroadcastReceiver {
    private static final String TAG = "WiFiScanReceiver";
    WifiService wifiDemo;

    public WifiReceiver(WifiService wifiService) {
        super();
        this.wifiDemo = wifiService;
    }

    @Override
    public void onReceive(Context c, Intent intent) {
        List<ScanResult> results = wifiDemo.getWifi().getScanResults();
        ScanResult bestSignal = null;
        for (ScanResult result : results) {
            if (bestSignal == null
                    || WifiManager.compareSignalLevel(bestSignal.level,
                            result.level) < 0)
                bestSignal = result;
        }

        String message = String.format(
                "%s networks found. %s is the strongest.", results.size(),
                bestSignal.SSID);
        Toast.makeText(wifiDemo, message, Toast.LENGTH_LONG).show();

        Log.d(TAG, "onReceive() message: " + message);
    }
}
