package de.rutsche.wifidisplayunlock.view;

import de.rutsche.wifidisplayunlock.R;
import de.rutsche.wifidisplayunlock.WifiConfig;
import de.rutsche.wifidisplayunlock.R.id;
import de.rutsche.wifidisplayunlock.R.layout;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class LayoutOne extends ListFragment {

    private ViewPagerActivity activity;
    private WifiConfig[] configs;

    public static Fragment newInstance(Context context) {
        LayoutOne f = new LayoutOne();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (ViewPagerActivity) getActivity();
        configs = activity.getConfigs();

        ListAdapter myListAdapter = new ArrayAdapter<WifiConfig>(getActivity(),
                R.layout.list_item, R.id.textview_listitem, configs);
        setListAdapter(myListAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_one, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        Toast.makeText(getActivity(),
                getListView().getItemAtPosition(position).toString(),
                Toast.LENGTH_LONG).show();
    }
}
