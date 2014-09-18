package me.doapps.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import me.doapps.beans.Temp_Place_DTO;
import me.doapps.descubreperu.DescubrePeru;
import me.doapps.descubreperu.R;
import me.doapps.fragments.Fragment_Detail_Place;

/**
 * Created by jnolascob on 17/09/2014.
 */
public class View_Place extends FrameLayout implements View.OnClickListener {

    private Temp_Place_DTO place_dto;
    private View view;

    public View_Place(Context context, Temp_Place_DTO place_dto){
        super(context);
        this.place_dto = place_dto;
        initView();
    }

    public View_Place(Context context, AttributeSet attrs, Temp_Place_DTO place_dto){
        super(context, attrs);
        this.place_dto = place_dto;
        initView();
    }

    public View_Place(Context context, AttributeSet attrs, int defStyle, Temp_Place_DTO place_dto){
        super(context, attrs, defStyle);
        this.place_dto = place_dto;
        initView();
    }

    private void initView(){
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.item_route_list, this, true);
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ((DescubrePeru)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.container, Fragment_Detail_Place.newInstance(),"fragment_detail_place").commit();
    }
}
