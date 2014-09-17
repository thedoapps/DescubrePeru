package me.doapps.beans;

import com.parse.ParseFile;
import com.parse.ParseGeoPoint;

/**
 * Created by jnolascob on 17/09/2014.
 */
public class Temp_Place_DTO {
    private String place_name;
    private String place_description;
    private String place_json_data_detail;
    private String place_json_array_url_images_google;
    private String place_flag_user;
    private String place_json_array_url_images_user;
    private ParseFile place_parsefile_image;
    private ParseGeoPoint place_geopoint;

    public Temp_Place_DTO(){}

    public Temp_Place_DTO(String place_name, String place_json_data_detail, ParseGeoPoint place_geopoint){
        this.place_name = place_name;
        this.place_json_data_detail = place_json_data_detail;
        this.place_geopoint = place_geopoint;
    }

    public Temp_Place_DTO(String place_name, String place_description, String place_json_data_detail, String place_json_array_url_images_google, String place_json_array_url_images_user, String place_flag_user, ParseFile place_parsefile_image, ParseGeoPoint place_geopoint) {
        this.place_name = place_name;
        this.place_description = place_description;
        this.place_json_data_detail = place_json_data_detail;
        this.place_json_array_url_images_google = place_json_array_url_images_google;
        this.place_json_array_url_images_user = place_json_array_url_images_user;
        this.place_flag_user = place_flag_user;
        this.place_parsefile_image = place_parsefile_image;
        this.place_geopoint = place_geopoint;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String getPlace_description() {
        return place_description;
    }

    public void setPlace_description(String place_description) {
        this.place_description = place_description;
    }

    public String getPlace_json_data_detail() {
        return place_json_data_detail;
    }

    public void setPlace_json_data_detail(String place_json_data_detail) {
        this.place_json_data_detail = place_json_data_detail;
    }

    public String getPlace_json_array_url_images_google() {
        return place_json_array_url_images_google;
    }

    public void setPlace_json_array_url_images_google(String place_json_array_url_images_google) {
        this.place_json_array_url_images_google = place_json_array_url_images_google;
    }

    public String getPlace_flag_user() {
        return place_flag_user;
    }

    public void setPlace_flag_user(String place_flag_user) {
        this.place_flag_user = place_flag_user;
    }

    public String getPlace_json_array_url_images_user() {
        return place_json_array_url_images_user;
    }

    public void setPlace_json_array_url_images_user(String place_json_array_url_images_user) {
        this.place_json_array_url_images_user = place_json_array_url_images_user;
    }

    public ParseFile getPlace_parsefile_image() {
        return place_parsefile_image;
    }

    public void setPlace_parsefile_image(ParseFile place_parsefile_image) {
        this.place_parsefile_image = place_parsefile_image;
    }

    public ParseGeoPoint getPlace_geopoint() {
        return place_geopoint;
    }

    public void setPlace_geopoint(ParseGeoPoint place_geopoint) {
        this.place_geopoint = place_geopoint;
    }
}
