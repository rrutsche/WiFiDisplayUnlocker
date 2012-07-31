package de.rutsche.wifidisplayunlock.view;

import de.rutsche.wifidisplayunlock.R;
import de.rutsche.wifidisplayunlock.R.layout;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SettingsFragment extends Fragment{
	
	public static Fragment newInstance(Context context) {
        SettingsFragment f = new SettingsFragment();
 
        return f;
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.layout_two, null);
        return root;
    }

}
