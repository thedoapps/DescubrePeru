package me.doapps.beans;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

/**
 * Created by Gantz on 17/09/14.
 */

@ParseClassName("Place")
public class Place_DTO extends ParseObject {

    private String place_name;
    private String place_description;
    private String place_json_data_detail;
    private String place_json_array_url_images_google;
    private String place_flag_user;
    private String place_json_array_url_images_user;
    private ParseFile place_parsefile_image;
    private ParseGeoPoint place_geopoint;

    public Place_DTO() {
    }

    public String getPlace_name() {
        return getString("place_name");
    }

    public void setPlace_name(String place_name) {
        put("place_name",place_name);
    }

    public ParseGeoPoint getPlace_geopoint() {
        return getParseGeoPoint("place_geopoint");
    }

    public void setPlace_geopoint(ParseGeoPoint place_geopoint) {
        put("place_geopoint",place_geopoint);
    }

    public ParseFile getPlace_parsefile_image() {
        return getParseFile("place_parsefile_image");
    }

    public void setPlace_parsefile_image(ParseFile place_parsefile_image) {
        put("place_parsefile_image",place_parsefile_image);
    }

    public String getPlace_json_array_url_images_user() {
        return getString("place_json_array_url_images_user");
    }

    public void setPlace_json_array_url_images_user(String place_json_array_url_images_user) {
        put("place_json_array_url_images_user",place_json_array_url_images_user);
    }

    public String getPlace_flag_user() {
        return getString("place_flag_user");
    }

    public void setPlace_flag_user(String place_flag_user) {
        put("place_flag_user",place_flag_user);
    }

    public String getPlace_json_array_url_images_google() {
        return getString("place_json_array_url_images_google");
    }

    public void setPlace_json_array_url_images_google(String place_json_array_url_images_google) {
        put("place_json_array_url_images_google",place_json_array_url_images_google);
    }

    public String getPlace_json_data_detail() {
        return getString("place_json_data_detail");
    }

    public void setPlace_json_data_detail(String place_json_data_detail) {
        put("place_json_data_detail",place_json_data_detail);
    }

    public String getPlace_description() {
        return getString("place_description");
    }

    public void setPlace_description(String place_description) {
        put("place_description",place_description);
    }
}
