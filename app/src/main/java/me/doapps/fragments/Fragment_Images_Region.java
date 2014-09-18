package me.doapps.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import me.doapps.descubreperu.R;
import me.doapps.utils.Util_Fonts;

/**
 * Created by Gantz on 17/09/14.
 */
public class Fragment_Images_Region extends Fragment {

    protected ImageView img_fondo;
    protected TextView txt_mensaje;
    protected int id_image;
    protected int id_mensaje;

    public static Fragment_Images_Region newInstance(int id_image,int id_mensaje) {
        Fragment_Images_Region fragment_images = new Fragment_Images_Region();
        Bundle bundle = new Bundle();
        bundle.putInt("id_image",id_image);
        bundle.putInt("id_mensaje",id_mensaje);
        fragment_images.setArguments(bundle);
        return fragment_images;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id_image = getArguments().getInt("id_image");
        id_mensaje = getArguments().getInt("id_mensaje");
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_images_region,container,false);
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        img_fondo = (ImageView)getView().findViewById(R.id.image_tutorial);
        txt_mensaje = (TextView)getView().findViewById(R.id.mensaje_tutorial);;

        img_fondo.setImageResource(id_image);

        txt_mensaje.setText(getString(id_mensaje).toUpperCase());
        txt_mensaje.setTypeface(Util_Fonts.setNexaBold(getActivity()));
    }
}
