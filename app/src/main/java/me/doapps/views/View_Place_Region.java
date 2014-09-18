package me.doapps.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import me.doapps.beans.Temp_Place_DTO;
import me.doapps.descubreperu.DescubrePeru;
import me.doapps.descubreperu.R;
import me.doapps.fragments.Fragment_Detail_Place;

/**
 * Created by jnolascob on 18/09/2014.
 */
public class View_Place_Region extends FrameLayout {
    private Temp_Place_DTO place_dto;
    private View view;

    public View_Place_Region(Context context, Temp_Place_DTO place_dto){
        super(context);
        this.place_dto = place_dto;
        initView();
    }

    public View_Place_Region(Context context, AttributeSet attrs, Temp_Place_DTO place_dto){
        super(context, attrs);
        this.place_dto = place_dto;
        initView();
    }

    public View_Place_Region(Context context, AttributeSet attrs, int defStyle, Temp_Place_DTO place_dto){
        super(context, attrs, defStyle);
        this.place_dto = place_dto;
        initView();
    }

    private void initView(){
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.view_place, this, true);

        ((TextView)view.findViewById(R.id.txt_name_place)).setText(place_dto.getPlace_name());
        final ImageView img_add_place = (ImageView)view.findViewById(R.id.img_add_place);
        final ImageView img_delete_place = (ImageView)view.findViewById(R.id.img_delete_place);

        img_add_place.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Agregado", Toast.LENGTH_SHORT).show();
                img_add_place.setVisibility(GONE);
                img_delete_place.setVisibility(VISIBLE);
            }
        });

        img_delete_place.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Eliminado", Toast.LENGTH_SHORT).show();
                img_add_place.setVisibility(VISIBLE);
                img_delete_place.setVisibility(GONE);
            }
        });
    }

}
