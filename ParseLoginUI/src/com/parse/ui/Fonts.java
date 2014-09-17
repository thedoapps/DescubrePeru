package com.parse.ui;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by jnolascob on 28/07/2014.
 */

public class Fonts {
    public static Typeface setSweetness(Context context){
        return Typeface.createFromAsset(context.getAssets(), "fonts/sweetness.ttf");
    }

    public static Typeface setPNALight(Context mContext){
        return Typeface.createFromAsset(mContext.getAssets(),"fonts/pna_light.otf");
    }

    public static Typeface setPNASemiBold(Context mContext){
        return Typeface.createFromAsset(mContext.getAssets(),"fonts/pna_semi_bold.otf");
    }

    public static Typeface setPNACursivaLight(Context mContext){
        return Typeface.createFromAsset(mContext.getAssets(),"fonts/pna_cursiva_light.otf");
    }
}
