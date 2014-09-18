package me.doapps.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

import java.util.List;

import me.doapps.beans.Place_DTO;
import me.doapps.beans.Route_DTO;
import me.doapps.descubreperu.DescubrePeru;
import me.doapps.descubreperu.R;
import me.doapps.views.View_Route;

/**
 * Created by Gantz on 17/09/14.
 */
public class Fragment_Principal extends Fragment_Master {

    protected Adapter_Fragment mAdapter;
    protected ViewPager mPager;
    protected PageIndicator mIndicator;
    protected LinearLayout frame_routes;

    public static Fragment_Principal newInstance() {
        return new Fragment_Principal();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_principal, container, false);
        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new Adapter_Fragment(getChildFragmentManager());
        mPager = (ViewPager) getView().findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
        mIndicator = (CirclePageIndicator) getView().findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);

        frame_routes = (LinearLayout)getView().findViewById(R.id.frame_routes);
        /*set timeline*/
        ParseQuery<Route_DTO> queryRoutes = Route_DTO.getQuery();
        queryRoutes.findInBackground(new FindCallback<Route_DTO>() {
            @Override
            public void done(List<Route_DTO> route_dtos, ParseException e) {
                for (int i = 0; i < route_dtos.size(); i++) {
                    View_Route view_route = new View_Route(getActivity(),route_dtos.get(i));
                    view_route.setInterface_route(new View_Route.Interface_Route() {
                        @Override
                        public void getRoute(Route_DTO route_dto) {
                            Log.e("route", String.valueOf(route_dto));
                            ((DescubrePeru)getActivity()).getSupportFragmentManager().beginTransaction().add(R.id.container, Fragment_Route_Map.newInstance(route_dto),"fragment_producto").addToBackStack("detalle_producto").commit();
                        }
                    });
                    frame_routes.addView(view_route);
                }
            }
        });

    }

    class Adapter_Fragment extends FragmentPagerAdapter {

        int[] id_mensajes = {R.string.region_lima,R.string.region_ica,R.string.region_cusco};
        int[] id_images = {R.drawable.lima,R.drawable.lalibertad,R.drawable.lambayeque};

        public Adapter_Fragment(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return Fragment_Images_Region.newInstance(id_images[position],id_mensajes[position]);
        }

        @Override
        public int getCount() {
            return id_images.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return id_images + "";
        }

        public void setCount(int count) {
            if (count > 0 && count <= 10) {
                id_images[count] = count;
                notifyDataSetChanged();
            }
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
        }
    }
}
