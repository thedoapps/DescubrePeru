package me.doapps.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

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
        ((DescubrePeru) getActivity()).item_add_route.setVisible(true);
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
        for (int i = 0; i < 10; i++) {
            View_Route view_route = new View_Route(getActivity(),new Route_DTO());
            frame_routes.addView(view_route);
        }
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
