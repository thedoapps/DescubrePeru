package me.doapps.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import me.doapps.beans.Region_DTO;
import me.doapps.descubreperu.R;
import me.doapps.utils.Util_Fonts;

/**
 * Created by Gantz on 17/09/14.
 */
public class View_Region extends FrameLayout {

    private Region_DTO region_dto;
    private View view;

    protected ImageView img_fondo;
    protected TextView txt_mensaje;
    protected int id_image = R.drawable.lima;
    protected int id_mensaje = R.string.region_lima;

    public View_Region(Context context, Region_DTO region_dto) {
        super(context);
        this.region_dto = region_dto;
        initView();
    }

    public View_Region(Context context, AttributeSet attrs, Region_DTO region_dto) {
        super(context, attrs);
        this.region_dto = region_dto;
        initView();

    }

    public View_Region(Context context, AttributeSet attrs, int defStyle, Region_DTO region_dto) {
        super(context, attrs, defStyle);
        this.region_dto = region_dto;
        initView();
    }

    private void initView(){
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.view_region, this, true);

        img_fondo = (ImageView)view.findViewById(R.id.image_tutorial);
        txt_mensaje = (TextView)view.findViewById(R.id.mensaje_tutorial);;

        img_fondo.setImageResource(id_image);

        txt_mensaje.setText(getContext().getString(id_mensaje).toUpperCase());
        txt_mensaje.setTypeface(Util_Fonts.setNexaBold(getContext()));
    }
}
