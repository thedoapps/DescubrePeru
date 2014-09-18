package me.doapps.ws;

/**
 * Created by jnolascob on 17/09/2014.
 */
public class WS_Descubre {
    /*Google places - PLACES*/
    public static final String WS_NEARBY_SEARCH_ = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";
    public static final String WS_NEARBY_SEARCH = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-12.053932,-77.041071&radius=500&types=&name=s&sensor=true&key=AIzaSyC7VTy1fw8NrWq6Nfs_UHj6OuTnZqmjeP4";

    public static final String WS_TEXT_SEARCH = "https://maps.googleapis.com/maps/api/place/textsearch/json";
    public static final String WS_RADAR_SEARCH = "https://maps.googleapis.com/maps/api/place/radarsearch/json";
    /*Google places - DETAIL PLACE*/
    public static final String WS_DETAIL_PLACE = "https://maps.googleapis.com/maps/api/place/details/json";
    /*Google places - PHOTOS PLACE*/
    public static final String WS_PHOTOS_PLACE = "https://maps.googleapis.com/maps/api/place/photo";


    /*Search Foursquare*/
    public static final String WS_FOUR = "https://api.foursquare.com/v2/venues/search?ll=-12.0571345,-77.0373154&client_id=3PILMRCUAH0DXRK1U3F0RR34CLIZKL1QYH0KZEPHUEU0DIEQ&client_secret=2VMV5UJIUWVOGXU5TEPFLIBNGW002KT3KAL3MBNZ3GZYCZIT&v=20140918";
}
