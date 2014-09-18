package me.doapps.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import java.util.ArrayList;
import java.util.List;

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

    public static final Fragment_Route_Map newInstance() {
        return new Fragment_Route_Map();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_route_map, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        showMarks(strCoordenadas);
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
                            .title("Starbucks").snippet("food, coffee").draggable(true)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.icono_ruta_gastronomica))
            )
                    .showInfoWindow();
        }
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                coordenadas.get(coordenadas.size() - 1), 16));
        map.getUiSettings().setZoomControlsEnabled(false);
    }


}
