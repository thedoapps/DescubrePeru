package me.doapps.fragments;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.squareup.picasso.Picasso;

import me.doapps.beans.User_DTO;
import me.doapps.descubreperu.DescubrePeru;
import me.doapps.descubreperu.R;
import me.doapps.dialog.Dialog_Descubre_Peru;
import me.doapps.dialog.Dialog_Descubre_Peru_Logout;
import me.doapps.utils.RoundedTransformation;
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
        ImageView imageViewuser = (ImageView)getView().findViewById(R.id.menu_user_image);

        if(ParseUser.getCurrentUser() != null && ParseFacebookUtils.getSession() != null){
            User_DTO user_dto = (User_DTO) ParseUser.getCurrentUser();
            nombre_usuario.setText(user_dto.getUser_name());
            Picasso.with(getActivity()).load(user_dto.getUser_url_image()).placeholder(R.drawable.user).error(R.drawable.user).centerCrop().fit().transform(new RoundedTransformation(65,0)).into(imageViewuser);
        }

        nombre_usuario.setTypeface(Util_Fonts.setNexaLight(getActivity()));
        menu_turistica.setTypeface(Util_Fonts.setNexaLight(getActivity()));
        menu_gastronomicas.setTypeface(Util_Fonts.setNexaLight(getActivity()));
        menu_nocturnas.setTypeface(Util_Fonts.setNexaLight(getActivity()));

        imageViewuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ParseUser.getCurrentUser() != null && ParseFacebookUtils.getSession() != null){
                    Dialog_Descubre_Peru_Logout dialog_descubre_peru_logout = new Dialog_Descubre_Peru_Logout(getActivity(),(DescubrePeru)getActivity());
                    dialog_descubre_peru_logout.show();
                }
            }
        });

        getView().findViewById(R.id.option_rutas_turisticas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.container, Fragment_Principal.newInstance(), "fragment_route_map").commit();
                ((DescubrePeru)getActivity()).sm_menu.toggle();
            }
        });

        getView().findViewById(R.id.option_rutas_nocturnas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.container, Fragment_Detail_Place.newInstance(), "fragment_detail_place").commit();
                ((DescubrePeru)getActivity()).sm_menu.toggle();
            }
        });
    }
}
