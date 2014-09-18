package me.doapps.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.SaveCallback;

import org.json.JSONArray;

import java.util.ArrayList;

import me.doapps.beans.Route_DTO;
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
public class Fragment_Create_Route extends Fragment_Master {
    private LinearLayout frame_places_region;
    private Button btn_add_place;
    private Button btn_create_route;

    private ArrayList<Temp_Place_DTO> jsonArrayPlaces = new ArrayList<Temp_Place_DTO>();
    private String arrayPlaces;

    public static final Fragment_Create_Route newInstance(){
        return new Fragment_Create_Route();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showMenu(true);
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
                        view_place_region.setInterface_place(new View_Place_Region.Interface_Place() {
                            @Override
                            public void getPlace(boolean flag, Temp_Place_DTO place_dto) {
                                Log.e("place_dto", place_dto.toString());
                                if(flag){
                                    jsonArrayPlaces.add(place_dto);
                                    Log.e("size", String.valueOf(jsonArrayPlaces.size()));
                                }
                                else{
                                    jsonArrayPlaces.remove(place_dto);
                                    Log.e("size", String.valueOf(jsonArrayPlaces.size()));
                                }

                            }
                        });
                        view_place_region.setTag(place_dtos.get(i));
                        frame_places_region.addView(view_place_region);
                    }
                }
                else{
                    Toast.makeText(getActivity(), "Vacio", Toast.LENGTH_SHORT).show();
                }
            }
        });



        btn_add_place = (Button)getView().findViewById(R.id.btn_add_place);
        btn_create_route = (Button)getView().findViewById(R.id.btn_create_route);

        btn_add_place.setTypeface(Util_Fonts.setNexaLight(getActivity()));
        btn_create_route.setTypeface(Util_Fonts.setNexaLight(getActivity()));

        btn_create_route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(jsonArrayPlaces.size()<=0){
                    Toast.makeText(getActivity(), "No ha seleccionado lugares!", Toast.LENGTH_SHORT).show();
                }
                else{

                    final Dialog_Descubre_Peru dialog_descubre_peru = new Dialog_Descubre_Peru(getActivity());
                    dialog_descubre_peru.show();
                    JSONArray jsonArray = new JSONArray();
                    for (int i = 0; i < jsonArrayPlaces.size(); i++) {
                        jsonArray.put(jsonArrayPlaces.get(i).getPlace_json_data_detail());
                    }

                    Route_DTO route_dto =  new Route_DTO();
                    route_dto.setRoute_likes_count(0);
                    route_dto.setRoute_name("Ruta de Angel");
                    route_dto.setRoute_json_array_place_thumbail(jsonArray.toString());
                    route_dto.setRoute_url_image("http://www.mesamenu.com/images/dishes/Mayta-SalmonTostado.jpg");
                    route_dto.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e == null){
                                dialog_descubre_peru.hide();
                                Toast.makeText(getActivity(), "Se creo ruta!", Toast.LENGTH_SHORT).show();
                                FragmentManager manager = getActivity().getSupportFragmentManager();
                                manager.beginTransaction().replace(R.id.container, Fragment_Principal.newInstance(), "fragment_route_map").commit();
                            }
                            else{
                                dialog_descubre_peru.hide();
                                Toast.makeText(getActivity(), "Ocurrió un error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
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
