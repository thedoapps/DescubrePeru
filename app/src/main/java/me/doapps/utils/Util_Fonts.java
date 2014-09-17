package me.doapps.utils;

import android.content.Context;
import android.graphics.Typeface;

public class Util_Fonts {

    public static Typeface setNexaLight(Context mContext){
        return Typeface.createFromAsset(mContext.getAssets(), "fonts/Nexa Light.otf");
    }

    public static Typeface setNexaBold(Context mContext){
        return Typeface.createFromAsset(mContext.getAssets(), "fonts/Nexa Bold.otf");
    }
}
