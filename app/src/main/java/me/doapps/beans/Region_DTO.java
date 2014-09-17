package me.doapps.beans;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

/**
 * Created by Gantz on 17/09/14.
 */

@ParseClassName("Region")
public class Region_DTO extends ParseObject{

    private String region_name;
    private String region_url_image;
    private String region_description;
    private ParseFile region_parsefile_image;

    public Region_DTO() {
    }

    public String getRegion_name() {
        return getString("region_name");
    }

    public void setRegion_name(String region_name) {
        put("region_name",region_name);
    }

    public String getRegion_url_image() {
        return getString("region_url_image");
    }

    public void setRegion_url_image(String region_url_image) {
        put("region_url_image",region_url_image);
    }

    public String getRegion_description() {
        return getString("region_description");
    }

    public void setRegion_description(String region_description) {
        put("region_description",region_description);
    }

    public ParseFile getRegion_parsefile_image() {
        return getParseFile("region_parsefile_image");
    }

    public void setRegion_parsefile_image(ParseFile region_parsefile_image) {
        put("region_parsefile_image",region_parsefile_image);
    }
}
