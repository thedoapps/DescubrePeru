package me.doapps.fragments;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import me.doapps.descubreperu.R;

/**
 * Created by Gantz on 17/09/14.
 */
public class Fragment_Images extends Fragment {

    protected ImageView img_fondo;
    protected int id_image;

    public static Fragment_Images newInstance(int id_image) {
        Fragment_Images fragment_images = new Fragment_Images();
        Bundle bundle = new Bundle();
        bundle.putInt("id_image",id_image);
        fragment_images.setArguments(bundle);
        return fragment_images;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id_image = getArguments().getInt("id_image");
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_images,container,false);
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        img_fondo = (ImageView)getView().findViewById(R.id.image_tutorial);
        img_fondo.setImageResource(id_image);
    }
}
