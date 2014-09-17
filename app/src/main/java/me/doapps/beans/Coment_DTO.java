package me.doapps.beans;

import com.parse.ParseObject;

/**
 * Created by Gantz on 17/09/14.
 */

public class Coment_DTO extends ParseObject {

    private String coment_text;
    private String coment_url_image_user;
    private String coment_place_id;

    public Coment_DTO() {

    }

    public String getComent_text() {
        return coment_text;
    }

    public void setComent_text(String coment_text) {
        this.coment_text = coment_text;
    }

    public String getComent_place_id() {
        return coment_place_id;
    }

    public void setComent_place_id(String coment_place_id) {
        this.coment_place_id = coment_place_id;
    }

    public String getComent_url_image_user() {
        return coment_url_image_user;
    }

    public void setComent_url_image_user(String coment_url_image_user) {
        this.coment_url_image_user = coment_url_image_user;
    }
}
