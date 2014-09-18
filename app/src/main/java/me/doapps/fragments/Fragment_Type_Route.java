package me.doapps.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import me.doapps.descubreperu.R;

/**
 * Created by jnolascob on 18/09/2014.
 */
public class Fragment_Type_Route extends Fragment {

    public static final Fragment_Type_Route newInstance(){
        return new Fragment_Type_Route();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_type_route, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FrameLayout img_turistic_route = (FrameLayout)getView().findViewById(R.id.img_turistic_route);
        FrameLayout img_nocturn_route = (FrameLayout)getView().findViewById(R.id.img_nocturn_route);
        FrameLayout img_gastronomic_route = (FrameLayout)getView().findViewById(R.id.img_gastronomic_route);

        img_turistic_route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.container, Fragment_Create_Route.newInstance(), "fragment_create_route").commit();
            }
        });

        img_nocturn_route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.container, Fragment_Create_Route.newInstance(), "fragment_create_route").commit();
            }
        });

        img_gastronomic_route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.container, Fragment_Create_Route.newInstance(), "fragment_create_route").commit();
            }
        });
    }
}
