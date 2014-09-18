package me.doapps.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import me.doapps.beans.Temp_Place_DTO;
import me.doapps.descubreperu.R;
import me.doapps.utils.Util_Fonts;
import me.doapps.views.View_Place_Region;

/**
 * Created by jnolascob on 17/09/2014.
 */
public class Fragment_Create_Route extends Fragment {
    private LinearLayout frame_places_region;
    private Button btn_add_place;
    private Button btn_create_route;

    public static final Fragment_Create_Route newInstance(){
        return new Fragment_Create_Route();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_route, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        frame_places_region = (LinearLayout)getView().findViewById(R.id.frame_places_region);
        for (int i = 0; i < 5; i++) {
            View_Place_Region view_place_region = new View_Place_Region(getActivity(), new Temp_Place_DTO());
            frame_places_region.addView(view_place_region);
        }

        btn_add_place = (Button)getView().findViewById(R.id.btn_add_place);
        btn_create_route = (Button)getView().findViewById(R.id.btn_create_route);

        btn_add_place.setTypeface(Util_Fonts.setNexaLight(getActivity()));
        btn_create_route.setTypeface(Util_Fonts.setNexaLight(getActivity()));
    }
}
