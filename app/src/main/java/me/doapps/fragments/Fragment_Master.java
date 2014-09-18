package me.doapps.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import me.doapps.descubreperu.DescubrePeru;
import me.doapps.descubreperu.R;

/**
 * Created by Gantz on 17/09/14.
 */
public class Fragment_Master extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ((DescubrePeru) getActivity()).getSupportActionBar().setIcon(R.drawable.ic_launcher);
        ((DescubrePeru) getActivity()).getSupportActionBar().setDisplayShowCustomEnabled(true);
        ((DescubrePeru) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void showMenu(boolean visible) {
        ((DescubrePeru) getActivity()).item_add_route.setVisible(visible);
    }
}
