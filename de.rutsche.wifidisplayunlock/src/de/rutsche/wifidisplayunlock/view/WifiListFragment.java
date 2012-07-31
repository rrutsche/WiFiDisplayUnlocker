package de.rutsche.wifidisplayunlock.view;

import android.content.Context;
import android.content.Intent;
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
import de.rutsche.wifidisplayunlock.R;
import de.rutsche.wifidisplayunlock.WifiConfig;
import de.rutsche.wifidisplayunlock.service.ServiceManager;
import de.rutsche.wifidisplayunlock.service.WifiService;

public class WifiListFragment extends ListFragment {

    private WifiConfig[] configs;

    public static Fragment newInstance(Context context) {
        WifiListFragment f = new WifiListFragment();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity()
                .startService(new Intent(getActivity(), WifiService.class));
        configs = ServiceManager.getInstance().getConfigs();

        if (configs.length > 0) {
            ListAdapter myListAdapter = new ArrayAdapter<WifiConfig>(
                    getActivity(), R.layout.list_item, R.id.textview_listitem,
                    configs);
            setListAdapter(myListAdapter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_one, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast toast = Toast.makeText(getActivity(), getListView()
                .getItemAtPosition(position).toString(), Toast.LENGTH_LONG);
        toast.show();
    }
}
