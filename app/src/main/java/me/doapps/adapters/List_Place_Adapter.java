package me.doapps.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import me.doapps.beans.Temp_Place_DTO;

/**
 * Created by jnolascob on 17/09/2014.
 */
public class List_Place_Adapter extends BaseAdapter {
    private Context context;
    private ArrayList<Temp_Place_DTO> place_dtos;
    private LayoutInflater inflater;

    public List_Place_Adapter(){}

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
