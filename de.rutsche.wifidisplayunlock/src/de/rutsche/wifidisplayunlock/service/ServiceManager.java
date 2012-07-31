package de.rutsche.wifidisplayunlock.service;

import java.util.ArrayList;
import java.util.List;

import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import de.rutsche.wifidisplayunlock.WifiConfig;

public class ServiceManager {

    private static ServiceManager instance;
    private ArrayList<WifiConfig> confs;
    private List<WifiConfiguration> configs;

    private WifiManager wifi;

    private ServiceManager() {
    }

    public static ServiceManager getInstance() {
        if (instance == null) {
            instance = new ServiceManager();
        }
        return instance;
    }

    public void setWifiManager(WifiManager wm) {
        wifi = wm;
        configs = wifi.getConfiguredNetworks();
        confs = new ArrayList<WifiConfig>();
        for (WifiConfiguration config : configs) {
            confs.add(new WifiConfig(config.SSID, config.networkId));
        }
    }

    public WifiConfig[] getConfigs() {
        return confs.toArray(new WifiConfig[0]);
    }

}
