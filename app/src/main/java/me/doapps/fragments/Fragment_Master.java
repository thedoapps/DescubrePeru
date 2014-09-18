package me.doapps.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.widget.Toast;

import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import me.doapps.descubreperu.DescubrePeru;
import me.doapps.descubreperu.R;

/**
 * Created by Gantz on 17/09/14.
 */
public class Fragment_Master extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((DescubrePeru) getActivity()).getSupportActionBar().setIcon(R.drawable.logo_440_150);
        ((DescubrePeru) getActivity()).getSupportActionBar().setDisplayShowCustomEnabled(true);
        ((DescubrePeru) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((DescubrePeru)getActivity()).getSupportActionBar().show();

        ((DescubrePeru) getActivity()).item_add_route.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(ParseUser.getCurrentUser() != null && ParseFacebookUtils.getSession() != null){
                    Toast.makeText(getActivity(), "Usuario Registrado", Toast.LENGTH_SHORT).show();
                }else{
                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container,Fragment_Login.newInstance()).addToBackStack(null).commit();
                }
                return false;
            }
        });
    }

    public void showMenu(boolean visible) {
        ((DescubrePeru) getActivity()).item_add_route.setVisible(visible);
    }
}
