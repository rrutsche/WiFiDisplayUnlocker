package de.rutsche.wifidisplayunlock.service;

import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.util.Log;
import de.rutsche.wifidisplayunlock.WifiConfig;
import de.rutsche.wifidisplayunlock.WifiReceiver;

public class WifiService extends Service {

    private WifiManager wifi;
    private BroadcastReceiver receiver;

    private List<WifiConfiguration> configs;
    private ArrayList<WifiConfig> confs;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Setup WiFi
        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        configs = wifi.getConfiguredNetworks();
        confs = new ArrayList<WifiConfig>();
        for (WifiConfiguration config : configs) {
            confs.add(new WifiConfig(config.SSID, config.networkId));
            // Log.w(FLAG, config.toString());
        }

        // Register Broadcast Receiver
        if (receiver == null) {
            receiver = new WifiReceiver(this);
        }

        registerReceiver(receiver, new IntentFilter(
                WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // TODO enable keyguard
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        return START_STICKY;
    }

    public WifiManager getWifi() {
        return wifi;
    }

}