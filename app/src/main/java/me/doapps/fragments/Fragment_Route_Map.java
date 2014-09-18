package me.doapps.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.doapps.beans.Route_DTO;
import me.doapps.descubreperu.DescubrePeru;
import me.doapps.descubreperu.R;
import me.doapps.utils.Util_GPS;

/**
 * Created by jnolascob on 17/09/2014.
 */
public class Fragment_Route_Map extends Fragment_Master {
    private GoogleMap map;
    private LatLng coordinateMap;
    private String strCoordenadas = "-12.056313,-77.037348;-12.056471,-77.036629;-12.057190,-77.036870";

    private Route_DTO route_dto;

    public static final Fragment_Route_Map newInstance(Route_DTO route_dto) {
        Fragment_Route_Map fragment_route_map = new Fragment_Route_Map();
        Bundle bundle = new Bundle();
        bundle.putSerializable("route_dto", route_dto);
        fragment_route_map.setArguments(bundle);
        return fragment_route_map;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        route_dto = (Route_DTO)getArguments().getSerializable("route_dto");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_route_map, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.e("route_dto", String.valueOf(route_dto));
        String array_latlong = "";
        try {
            JSONArray jsonArray = new JSONArray(route_dto.getRoute_json_array_place_thumbail());
            Log.e("jsonarray", jsonArray.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = new JSONObject(jsonArray.getString(i));
                Log.e("json", jsonObject.toString());
                array_latlong = array_latlong+jsonObject.getJSONObject("geometry").getJSONObject("location").getString("lat")+","+jsonObject.getJSONObject("geometry").getJSONObject("location").getString("lng")+";";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        showMarks(array_latlong);
    }

    private List<LatLng> getCoordenadasList(String strCoordenadas) {
        // TODO Auto-generated method stub

        String[] cc = strCoordenadas.split(";");
        List<String> MIstrCoordenadas = new ArrayList<String>();

        for (int i = 0; i < cc.length; i++) {
            MIstrCoordenadas.add(cc[i]);
        }

        List<LatLng> coordenadas = new ArrayList<LatLng>();
        for (int i = 0; i < MIstrCoordenadas.size(); i++) {
            String compCoordenadas[] = MIstrCoordenadas.get(i).split(",");
            coordenadas.add(new LatLng(Double.parseDouble(compCoordenadas[0]),
                    Double.parseDouble(compCoordenadas[1])));
        }

        return coordenadas;
    }

    private void showMarks(String strCoordenadas) {
        LatLng cusco = new LatLng(-13.530614828358695, -71.84523969960935);
        List<LatLng> coordenadas = getCoordenadasList(strCoordenadas);

        map = ((SupportMapFragment) ((DescubrePeru) getActivity()).getSupportFragmentManager().findFragmentById(R.id.route_map)).getMap();

        for (int i = 0; i < coordenadas.size(); i++) {
            map.addMarker(
                    new MarkerOptions().position(coordenadas.get(i))
                            //.title("Starbucks").snippet("food, coffee").draggable(true)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.icono_ruta_gastronomica))
            )
                    .showInfoWindow();
        }
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                coordenadas.get(coordenadas.size() - 1), 13));
        map.getUiSettings().setZoomControlsEnabled(false);
    }


}
