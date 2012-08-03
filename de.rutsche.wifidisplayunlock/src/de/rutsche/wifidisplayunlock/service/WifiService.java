package de.rutsche.wifidisplayunlock.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import de.rutsche.wifidisplayunlock.WifiReceiver;
import de.rutsche.wifidisplayunlock.util.ApplicationManager;

public class WifiService extends Service {

    private WifiManager wifi;
    private BroadcastReceiver receiver;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // Setup WiFi
        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        // Register Broadcast Receiver
        if (receiver == null) {
            receiver = new WifiReceiver();
        }

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION);
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, intentFilter);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        ApplicationManager.getInstance().getLock().reenableKeyguard();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    public WifiManager getWifi() {
        return wifi;
    }
}
