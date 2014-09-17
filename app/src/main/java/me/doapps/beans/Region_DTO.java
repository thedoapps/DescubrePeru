package me.doapps.beans;

import com.parse.ParseObject;

/**
 * Created by Gantz on 17/09/14.
 */
public class Region_DTO extends ParseObject{

    private String region_name;
    private String region_url_image;
    private String region_description;

    public Region_DTO() {
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public String getRegion_url_image() {
        return region_url_image;
    }

    public void setRegion_url_image(String region_url_image) {
        this.region_url_image = region_url_image;
    }

    public String getRegion_description() {
        return region_description;
    }

    public void setRegion_description(String region_description) {
        this.region_description = region_description;
    }
}
