package me.doapps.beans;

import com.parse.ParseClassName;
import com.parse.ParseUser;

/**
 * Created by Gantz on 17/09/14.
 */

@ParseClassName("_User")
public class User_DTO extends ParseUser {

    private String user_name;
    private String user_url_image;
    private String user_social_id;
    private String user_json_data;

    public User_DTO() {
    }

    public String getUser_name() {
        return getString("user_name");
    }

    public void setUser_name(String user_name) {
        put("user_name",user_name);
    }

    public String getUser_json_data() {
        return getString("user_json_data");
    }

    public void setUser_json_data(String user_json_data) {
        put("user_json_data",user_json_data);
    }

    public String getUser_social_id() {
        return getString("user_social_id");
    }

    public void setUser_social_id(String user_social_id) {
        put("user_social_id",user_social_id);
    }

    public String getUser_url_image() {
        return getString("user_url_image");
    }

    public void setUser_url_image(String user_url_image) {
        put("user_url_image",user_url_image);
    }
}

