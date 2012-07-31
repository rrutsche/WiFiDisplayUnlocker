package de.rutsche.wifidisplayunlock;

import de.rutsche.wifidisplayunlock.view.WifiListFragment;
import de.rutsche.wifidisplayunlock.view.SettingsFragment;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private Context adapterContext;

    public ViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        adapterContext = context;

    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = new Fragment();
        switch (position) {
        case 0:
            f = WifiListFragment.newInstance(adapterContext);
            break;
        case 1:
            f = SettingsFragment.newInstance(adapterContext);
            break;
        }
        return f;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
