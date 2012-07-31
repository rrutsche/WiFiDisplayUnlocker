package de.rutsche.wifidisplayunlock.view;

import java.util.ArrayList;
import java.util.List;

import de.rutsche.wifidisplayunlock.R;
import de.rutsche.wifidisplayunlock.ViewPagerAdapter;
import de.rutsche.wifidisplayunlock.WifiConfig;
import de.rutsche.wifidisplayunlock.WifiReceiver;
import de.rutsche.wifidisplayunlock.R.id;
import de.rutsche.wifidisplayunlock.R.layout;

import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;

public class ViewPagerActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private ViewPagerAdapter adapter;

    WifiManager wifi;
    BroadcastReceiver receiver;

    private static final String FLAG = "WIFI";
    private List<WifiConfiguration> configs;
    private ArrayList<WifiConfig> confs;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setUpView();
        setTab();

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

        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        KeyguardLock lock = keyguardManager.newKeyguardLock(KEYGUARD_SERVICE);
        lock.disableKeyguard();
    }

    @Override
    public void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }

    private void setUpView() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new ViewPagerAdapter(getApplicationContext(),
                getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
    }

    private void setTab() {
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int position) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                case 0:
                    findViewById(R.id.first_tab).setVisibility(View.VISIBLE);
                    findViewById(R.id.second_tab).setVisibility(View.INVISIBLE);
                    break;

                case 1:
                    findViewById(R.id.first_tab).setVisibility(View.INVISIBLE);
                    findViewById(R.id.second_tab).setVisibility(View.VISIBLE);
                    break;
                }
            }

        });
    }

    public WifiConfig[] getConfigs() {
        return confs.toArray(new WifiConfig[0]);
    }

}