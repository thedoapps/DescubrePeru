package me.doapps.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

import me.doapps.descubreperu.R;

/**
 * Created by Gantz on 17/09/14.
 */
public class Fragment_Tutorial extends Fragment {

    protected Adapter_Fragment mAdapter;
    protected ViewPager mPager;
    protected PageIndicator mIndicator;

    public static Fragment_Tutorial newInstance() {
        return new Fragment_Tutorial();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tutorial, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new Adapter_Fragment(getActivity().getSupportFragmentManager());
        mPager = (ViewPager) getView().findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
        mIndicator = (CirclePageIndicator) getView().findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
    }

    class Adapter_Fragment extends FragmentPagerAdapter {

        int[] id_images = {R.drawable.portada_ruta_gastronomica,R.drawable.portada_ruta_turistica,R.drawable.portada_ruta_turistica};

        public Adapter_Fragment(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return Fragment_Images.newInstance(id_images[position]);
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
