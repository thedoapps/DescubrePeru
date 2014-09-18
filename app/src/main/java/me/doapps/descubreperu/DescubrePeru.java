package me.doapps.descubreperu;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import com.parse.ParseFacebookUtils;

import me.doapps.fragments.Fragment_Menu;
import me.doapps.fragments.Fragment_Tutorial;


public class DescubrePeru extends ActionBarActivity {

    public SlidingMenu sm_menu;
    public MenuItem item_add_route;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
        setContentView(R.layout.descubre_peru);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, Fragment_Tutorial.newInstance()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, Fragment_Menu.newInstance()).commit();

        sm_menu = new SlidingMenu(this);
        sm_menu.setMode(SlidingMenu.LEFT);
        sm_menu.setMenu(R.layout.menu_frame);
        sm_menu.setShadowWidthRes(R.dimen.navigation_drawer_width);
        sm_menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        sm_menu.setFadeDegree(0.35f);
        sm_menu.attachToActivity(this,SlidingMenu.SLIDING_WINDOW);
        sm_menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.descubre_peru, menu);
        item_add_route = menu.getItem(0);
        item_add_route.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                sm_menu.toggle(true);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(sm_menu.isMenuShowing()){
                sm_menu.toggle(true);
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initActionBar(){
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4D000000")));
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#4D000000")));

        //getSupportActionBar().setDisplayShowCustomEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setIcon(R.drawable.icono_75_75);
        getSupportActionBar().setTitle("");
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
    }
}
