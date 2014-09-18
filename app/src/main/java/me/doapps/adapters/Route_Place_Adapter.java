package me.doapps.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import me.doapps.beans.Place_DTO;
import me.doapps.beans.Temp_Place_DTO;
import me.doapps.descubreperu.R;
import me.doapps.utils.RoundedTransformation;
import me.doapps.utils.Util_Fonts;

/**
 * Created by jnolascob on 17/09/2014.
 */
public class Route_Place_Adapter extends BaseAdapter {
    private Context context;
    private ArrayList<Temp_Place_DTO> place_dtos;
    private LayoutInflater inflater;

    public Route_Place_Adapter(ArrayList<Temp_Place_DTO> place_dtos, Context context) {
        this.place_dtos = place_dtos;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return place_dtos.size();
    }

    @Override
    public Object getItem(int position) {
        return place_dtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        Temp_Place_DTO place_dto = place_dtos.get(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_route_list, parent, false);
            holder = new Holder();

            holder.txt_name_place = (TextView) convertView.findViewById(R.id.txt_name_place);
            holder.txt_description_place = (TextView) convertView.findViewById(R.id.txt_description_place);
            holder.img_user_image = (ImageView)convertView.findViewById(R.id.img_user_image);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.txt_name_place.setText("test");
        holder.txt_description_place.setText("test");
        holder.txt_name_place.setTypeface(Util_Fonts.setNexaBold(context));
        holder.txt_description_place.setTypeface(Util_Fonts.setNexaLight(context));

        /*String imageurl = "http://blog.costamar.com/wp-content/uploads/2012/01/Laguna-de-Huacachina.jpg";
        Picasso.with(context).load(imageurl).placeholder(R.drawable.cristian).centerCrop().fit().transform(new RoundedTransformation(75, 0)).into(holder.img_user_image);*/

        return convertView;
    }

    class Holder {
        TextView txt_name_place;
        TextView txt_description_place;
        ImageView img_user_image;
    }
}
