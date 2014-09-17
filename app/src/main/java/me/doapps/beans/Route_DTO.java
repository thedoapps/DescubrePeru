package me.doapps.beans;

import com.parse.ParseObject;

import org.json.JSONArray;

/**
 * Created by Gantz on 17/09/14.
 */
public class Route_DTO extends ParseObject {

    private String route_name;
    private int route_likes_count;
    private String route_url_first_image;
    private String route_user_name;
    private String route_id_user;
    private String route_url_user_image;
    private String route_json_array_place_thumbail;
    private String route_region_id;

    public Route_DTO() {
    }

    public String getRoute_name() {
        return route_name;
    }

    public void setRoute_name(String route_name) {
        this.route_name = route_name;
    }

    public String getRoute_region_id() {
        return route_region_id;
    }

    public void setRoute_region_id(String route_region_id) {
        this.route_region_id = route_region_id;
    }

    public String getRoute_json_array_place_thumbail() {
        return route_json_array_place_thumbail;
    }

    public void setRoute_json_array_place_thumbail(String route_json_array_place_thumbail) {
        this.route_json_array_place_thumbail = route_json_array_place_thumbail;
    }

    public String getRoute_url_user_image() {
        return route_url_user_image;
    }

    public void setRoute_url_user_image(String route_url_user_image) {
        this.route_url_user_image = route_url_user_image;
    }

    public String getRoute_id_user() {
        return route_id_user;
    }

    public void setRoute_id_user(String route_id_user) {
        this.route_id_user = route_id_user;
    }

    public String getRoute_user_name() {
        return route_user_name;
    }

    public void setRoute_user_name(String route_user_name) {
        this.route_user_name = route_user_name;
    }

    public String getRoute_url_first_image() {
        return route_url_first_image;
    }

    public void setRoute_url_first_image(String route_url_first_image) {
        this.route_url_first_image = route_url_first_image;
    }

    public int getRoute_likes_count() {
        return route_likes_count;
    }

    public void setRoute_likes_count(int route_likes_count) {
        this.route_likes_count = route_likes_count;
    }
}
