package com.parse.ui;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Gantz on 7/08/14.
 */

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Dialog_Joyful extends AlertDialog {


    public Dialog_Joyful(Context context) {
        super(context);
        initDialog();
    }

    public Dialog_Joyful(Context context, int theme) {
        super(context, theme);
        initDialog();
    }

    private void initDialog() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_joyful, null);
        setView(view);
        TextView txtdescripcionlife = (TextView) view.findViewById(R.id.txtavisodialoglife);
        txtdescripcionlife.setTypeface(Fonts.setPNALight(getContext()));
    }
}
