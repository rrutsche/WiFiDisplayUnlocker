package de.rutsche.wifidisplayunlock;

public class WifiConfig {

    private String ssid;
    private int networkId;

    public WifiConfig(String ssid, int networkId) {
        this.ssid = ssid;
        this.networkId = networkId;
    }

    public String getSsid() {
        return ssid;
    }

    public int getNetworkId() {
        return networkId;
    }

    @Override
    public String toString() {
        return ssid;
    }

}
