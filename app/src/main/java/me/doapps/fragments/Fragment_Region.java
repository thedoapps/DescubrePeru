package me.doapps.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

import me.doapps.beans.Region_DTO;
import me.doapps.beans.Route_DTO;
import me.doapps.descubreperu.DescubrePeru;
import me.doapps.descubreperu.R;
import me.doapps.views.View_Region;
import me.doapps.views.View_Route;

/**
 * Created by Gantz on 17/09/14.
 */
public class Fragment_Region extends Fragment_Master {

    protected LinearLayout frame_routes;
    protected FrameLayout fragme_region_header;

    public static Fragment_Region newInstance() {
        return new Fragment_Region();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_region, container, false);
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

        frame_routes = (LinearLayout)getView().findViewById(R.id.frame_routes);
        fragme_region_header = (FrameLayout)getView().findViewById(R.id.frame_region_header);

        View_Region view_region = new View_Region(getActivity(),new Region_DTO());
        fragme_region_header.addView(view_region);

        for (int i = 0; i < 10; i++) {
            View_Route view_route = new View_Route(getActivity(),new Route_DTO());
            frame_routes.addView(view_route);
        }

        /**
         * Event Item
         */
        getMenuInstance().setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container,Fragment_Login.newInstance()).addToBackStack(null).commit();
                return false;
            }
        });
    }
}
