package me.doapps.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import me.doapps.adapters.Route_Place_Adapter;
import me.doapps.beans.Place_DTO;
import me.doapps.beans.Route_DTO;
import me.doapps.beans.Temp_Place_DTO;
import me.doapps.descubreperu.R;
import me.doapps.utils.Util_Fonts;
import me.doapps.views.View_Place;
import me.doapps.views.View_Route;

/**
 * Created by jnolascob on 17/09/2014.
 */
public class Fragment_Route_List extends Fragment_Master {
    private ArrayList<Temp_Place_DTO> place_dtos = new ArrayList<Temp_Place_DTO>();
    private LinearLayout frame_places;

    public static final Fragment_Route_List newInstance(){
        return new Fragment_Route_List();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_route_list, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        frame_places = (LinearLayout)getView().findViewById(R.id.frame_places);
        for (int i = 0; i < 4; i++) {
            View_Place view_place = new View_Place(getActivity(),new Temp_Place_DTO());
            frame_places.addView(view_place);
        }

        TextView txt_user_name = (TextView)getView().findViewById(R.id.txt_user_name);
        TextView txt_count_likes = (TextView)getView().findViewById(R.id.txt_count_likes);

        txt_user_name.setTypeface(Util_Fonts.setNexaBold(getActivity()));
        txt_count_likes.setTypeface(Util_Fonts.setNexaLight(getActivity()));



    }
}
