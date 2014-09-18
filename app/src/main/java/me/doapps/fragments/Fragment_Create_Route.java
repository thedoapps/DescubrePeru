package me.doapps.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import me.doapps.beans.Temp_Place_DTO;
import me.doapps.descubreperu.R;
import me.doapps.dialog.Dialog_Descubre_Peru;
import me.doapps.modules.Module_Google;
import me.doapps.utils.Util_Fonts;
import me.doapps.utils.Util_GPS;
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

        Util_GPS util_gps = new Util_GPS(getActivity());
        String latlong = util_gps.getLatitude()+","+util_gps.getLongitude();

        final Dialog_Descubre_Peru dialog_descubre_peru = new Dialog_Descubre_Peru(getActivity());
        dialog_descubre_peru.show();
        Module_Google module_google = new Module_Google(getActivity());
        module_google.sendRequestNearbySearch(latlong);
        module_google.setInterface_nearbyPlace(new Module_Google.Interface_NearbyPlace() {
            @Override
            public void getNearbyPlace(boolean status, ArrayList<Temp_Place_DTO> place_dtos) {
                dialog_descubre_peru.hide();
                if(status){
                    for (int i = 0; i < place_dtos.size(); i++) {
                        View_Place_Region view_place_region = new View_Place_Region(getActivity(), new Temp_Place_DTO(place_dtos.get(i).getPlace_name(), "oli"));
                        frame_places_region.addView(view_place_region);
                    }
                }
                else{
                    Toast.makeText(getActivity(), "Vacio", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*module_google.sendRequestSearch(latlong);
        module_google.setInterface_search(new Module_Google.Interface_Search() {
            @Override
            public void getSearch(boolean status, ArrayList<Temp_Place_DTO> place_dtos) {
                if(status){
                    for (int i = 0; i < place_dtos.size(); i++) {
                        View_Place_Region view_place_region = new View_Place_Region(getActivity(), new Temp_Place_DTO(place_dtos.get(i).getPlace_name(), "oli"));
                        frame_places_region.addView(view_place_region);
                    }
                }
                else{
                    Toast.makeText(getActivity(), "Vacio", Toast.LENGTH_SHORT).show();
                }
            }
        });*/


        btn_add_place = (Button)getView().findViewById(R.id.btn_add_place);
        btn_create_route = (Button)getView().findViewById(R.id.btn_create_route);

        btn_add_place.setTypeface(Util_Fonts.setNexaLight(getActivity()));
        btn_create_route.setTypeface(Util_Fonts.setNexaLight(getActivity()));

        btn_create_route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog_Descubre_Peru dialog_descubre_peru = new Dialog_Descubre_Peru(getActivity());
                dialog_descubre_peru.show();
            }
        });

        btn_add_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, Fragment_Register_Place.newInstance(),"fragment_register_place").addToBackStack("fragment_register_place").commit();
            }
        });
    }
}
