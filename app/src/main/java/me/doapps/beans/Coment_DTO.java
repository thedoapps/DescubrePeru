package me.doapps.beans;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Gantz on 17/09/14.
 */

@ParseClassName("Comment")
public class Coment_DTO extends ParseObject {

    private String coment_text;
    private String coment_url_image_user;
    private String coment_place_id;

    public Coment_DTO() {

    }

    public String getComent_text() {
        return getString("coment_text");
    }

    public void setComent_text(String coment_text) {
        put("coment_text",coment_text);
    }

    public String getComent_place_id() {
        return getString("coment_place_id");
    }

    public void setComent_place_id(String coment_place_id) {
        put("coment_place_id",coment_place_id);
    }

    public String getComent_url_image_user() {
        return getString("coment_url_image_user");
    }

    public void setComent_url_image_user(String coment_url_image_user) {
        put("coment_url_image_user",coment_url_image_user);
    }
}
