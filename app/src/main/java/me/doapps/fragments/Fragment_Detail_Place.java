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
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import me.doapps.descubreperu.DescubrePeru;
import me.doapps.descubreperu.R;
import me.doapps.utils.Util_Fonts;
import me.doapps.utils.Util_GPS;

/**
 * Created by jnolascob on 17/09/2014.
 */
public class Fragment_Detail_Place extends Fragment_Master {
    private GoogleMap map;
    private LatLng latLng;

    public static final Fragment_Detail_Place newInstance(){
        return new Fragment_Detail_Place();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_place, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView txt_name_place = (TextView)getView().findViewById(R.id.txt_name_place);
        TextView txt_description_place = (TextView)getView().findViewById(R.id.txt_description_place);
        TextView txt_show_comments = (TextView)getView().findViewById(R.id.txt_show_comments);

        txt_name_place.setTypeface(Util_Fonts.setNexaBold(getActivity()));
        txt_description_place.setTypeface(Util_Fonts.setNexaLight(getActivity()));
        txt_show_comments.setTypeface(Util_Fonts.setNexaLight(getActivity()));

        setUpMapIfNeeded();
    }

    /*Google Maps*/
    private void setUpMapIfNeeded() {
        if (map == null) {
            map = ((SupportMapFragment) ((DescubrePeru)getActivity()).getSupportFragmentManager().findFragmentById(R.id.detail_map)).getMap();
            if (map != null) {
                setUpMap();
            }
        }
    }


    private void setUpMap() {
        Util_GPS util_gps = new Util_GPS(getActivity());

        if(util_gps.canGetLocation()){
            //mMap.setMyLocationEnabled(true);
            map.getUiSettings().setZoomControlsEnabled(false);

            Marker marker = map.addMarker(new MarkerOptions()
                    .position(new LatLng(util_gps.getLatitude(), util_gps.getLongitude()))
                            //.title("NOMADAPP")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.icono_ruta_gastronomica)));
                    //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

            CameraPosition camPos = new CameraPosition.Builder()
                    .target(new LatLng(util_gps.getLatitude(),util_gps.getLongitude()))
                    .zoom(16)
                    .build();
            CameraUpdate camUpd3 = CameraUpdateFactory.newCameraPosition(camPos);
            map.animateCamera(camUpd3);
            marker.showInfoWindow();

        }else{
            showSettingsAlert();
        }
    }

    public void showSettingsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Aviso");
        alertDialog.setMessage("Nomadapp necesita que actives tu GPS");
        alertDialog.setPositiveButton("Activar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 1);
            }
        });
        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }
}
