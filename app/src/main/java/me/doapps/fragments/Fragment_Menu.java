package me.doapps.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.doapps.descubreperu.R;
import me.doapps.utils.Util_Fonts;

/**
 * Created by Gantz on 17/09/14.
 */
public class Fragment_Menu extends Fragment {


    public static Fragment_Menu newInstance() {
        return new Fragment_Menu();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView menu_turistica = (TextView)getView().findViewById(R.id.txt_option_rutas_turisticas);
        TextView menu_gastronomicas = (TextView)getView().findViewById(R.id.txt_option_rutas_gastronomicas);
        TextView menu_nocturnas = (TextView)getView().findViewById(R.id.txt_option_rutas_nocturnas);
        TextView nombre_usuario = (TextView)getView().findViewById(R.id.menu_nombre_usuario);

        nombre_usuario.setTypeface(Util_Fonts.setNexaBold(getActivity()));
        menu_turistica.setTypeface(Util_Fonts.setNexaBold(getActivity()));
        menu_gastronomicas.setTypeface(Util_Fonts.setNexaBold(getActivity()));
        menu_nocturnas.setTypeface(Util_Fonts.setNexaBold(getActivity()));
    }
}
