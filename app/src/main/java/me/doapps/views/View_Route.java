package me.doapps.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import me.doapps.beans.Route_DTO;
import me.doapps.descubreperu.R;

/**
 * Created by Gantz on 17/09/14.
 */
public class View_Route extends FrameLayout {

    private Route_DTO route_dto;
    private View view;

    public View_Route(Context context, Route_DTO route_dto) {
        super(context);
        this.route_dto = route_dto;
        initView();
    }

    public View_Route(Context context, AttributeSet attrs, Route_DTO route_dto) {
        super(context, attrs);
        this.route_dto = route_dto;
        initView();

    }

    public View_Route(Context context, AttributeSet attrs, int defStyle, Route_DTO route_dto) {
        super(context, attrs, defStyle);
        this.route_dto = route_dto;
        initView();
    }

    private void initView(){
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.view_route, this, true);
    }
}
