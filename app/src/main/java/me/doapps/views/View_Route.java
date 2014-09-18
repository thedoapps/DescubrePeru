package me.doapps.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import me.doapps.beans.Route_DTO;
import me.doapps.beans.Temp_Place_DTO;
import me.doapps.descubreperu.R;
import me.doapps.utils.RoundedTransformation;

/**
 * Created by Gantz on 17/09/14.
 */
public class View_Route extends FrameLayout implements View.OnClickListener {

    private Route_DTO route_dto;
    private View view;

    private Interface_Route interface_route;

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
        setOnClickListener(this);

        ((TextView)view.findViewById(R.id.route_name)).setText(route_dto.getRoute_name());
        ImageView image_tutorial = (ImageView)view.findViewById(R.id.image_tutorial);

        Picasso.with(getContext()).load(route_dto.getRoute_url_image()).centerCrop().fit().into((image_tutorial));
    }

    /*method*/
    public void setInterface_route(Interface_Route interface_route){
        this.interface_route = interface_route;
    }

    @Override
    public void onClick(View v) {
        interface_route.getRoute(route_dto);
    }

    /*inner interface*/
    public interface Interface_Route{
        void getRoute(Route_DTO route_dto);
    }
}
