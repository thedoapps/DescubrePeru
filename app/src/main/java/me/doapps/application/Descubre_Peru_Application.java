package me.doapps.application;

import android.app.Application;
import me.doapps.beans.Coment_DTO;
import me.doapps.beans.Place_DTO;
import me.doapps.beans.Region_DTO;
import me.doapps.beans.Route_DTO;
import me.doapps.beans.User_DTO;
import me.doapps.descubreperu.R;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParseUser;


/**
 * Created by Gantz on 17/09/14.
 */
public class Descubre_Peru_Application extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Coment_DTO.class);
        ParseObject.registerSubclass(Place_DTO.class);
        ParseObject.registerSubclass(Region_DTO.class);
        ParseObject.registerSubclass(Route_DTO.class);
        ParseObject.registerSubclass(User_DTO.class);

        Parse.initialize(this,getString(R.string.app_id),getString(R.string.client_key));
        ParseFacebookUtils.initialize(getString(R.string.facebook_app_id));
        ParseUser.enableAutomaticUser();

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}
