package de.rutsche.wifidisplayunlock.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import de.rutsche.wifidisplayunlock.R;
import de.rutsche.wifidisplayunlock.ViewPagerAdapter;
import de.rutsche.wifidisplayunlock.service.WifiService;

public class ViewPagerActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private ViewPagerAdapter adapter;

    private static final String FLAG = "WIFI";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setUpView();
        setTab();
        
        startService(new Intent(this, WifiService.class));

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
}