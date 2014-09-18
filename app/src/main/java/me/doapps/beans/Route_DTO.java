package me.doapps.beans;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;

import java.io.Serializable;

/**
 * Created by Gantz on 17/09/14.
 */

@ParseClassName("Route")
public class Route_DTO extends ParseObject implements Serializable {

    private String route_name;
    private int route_likes_count;
    private String route_url_first_image;
    private String route_user_name;
    private String route_id_user;
    private String route_url_user_image;
    private String route_json_array_place_thumbail;
    private String route_region_id;
    private String route_url_image;

    public Route_DTO() {
    }

    public String getRoute_name() {
        return getString("route_name");
    }

    public void setRoute_name(String route_name) {
        put("route_name",route_name);
    }

    public String getRoute_region_id() {
        return getString("route_region_id");
    }

    public void setRoute_region_id(String route_region_id) {
        put("route_region_id",route_region_id);
    }

    public String getRoute_json_array_place_thumbail() {
        return getString("route_json_array_place_thumbail");
    }

    public void setRoute_json_array_place_thumbail(String route_json_array_place_thumbail) {
        put("route_json_array_place_thumbail",route_json_array_place_thumbail);
    }

    public String getRoute_url_user_image() {
        return getString("route_url_user_image");
    }

    public void setRoute_url_user_image(String route_url_user_image) {
        put("route_url_user_image",route_url_user_image);
    }

    public String getRoute_id_user() {
        return getString("route_id_user");
    }

    public void setRoute_id_user(String route_id_user) {
        put("route_id_user",route_id_user);
    }

    public String getRoute_user_name() {
        return getString("route_user_name");
    }

    public void setRoute_user_name(String route_user_name) {
        put("route_user_name",route_user_name);
    }

    public String getRoute_url_first_image() {
        return getString("route_url_first_image");
    }

    public void setRoute_url_first_image(String route_url_first_image) {
        put("route_url_first_image",route_url_first_image);
    }

    public int getRoute_likes_count() {
        return getInt("route_likes_count");
    }

    public void setRoute_likes_count(int route_likes_count) {
        put("route_likes_count",route_likes_count);
    }

    public String getRoute_url_image(){
        return getString("routes_url_image");
    }
    public void setRoute_url_image(String route_url_image){
        put("routes_url_image", route_url_image);
    }

    /*get all routes*/
    public static ParseQuery<Route_DTO> getQuery(){
        return ParseQuery.getQuery(Route_DTO.class);
    }
}
