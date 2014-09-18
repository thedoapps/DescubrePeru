package me.doapps.dialog;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import me.doapps.descubreperu.DescubrePeru;
import me.doapps.descubreperu.R;
import me.doapps.utils.Util_Fonts;

/**
 * Created by Gantz on 7/08/14.
 */

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Dialog_Descubre_Peru_Logout extends AlertDialog {

    private EditText edtcodigolife;
    private ActionBarActivity actionBarActivity;

    public Dialog_Descubre_Peru_Logout(Context context,ActionBarActivity actionBarActivity) {
        super(context);
        this.actionBarActivity = actionBarActivity;
        initDialog();
    }

    public Dialog_Descubre_Peru_Logout(Context context, int theme,ActionBarActivity actionBarActivity) {
        super(context, theme);
        this.actionBarActivity = actionBarActivity;
        initDialog();
    }

    private void initDialog() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_descubre_peru_logout, null);
        setView(view);

        TextView txtdescripcionlife = (TextView) view.findViewById(R.id.txtavisodialoglife);
        Button btncerrarsesion = (Button) view.findViewById(R.id.btn_cerrar_sesion);

        btncerrarsesion.setTypeface(Util_Fonts.setNexaBold(getContext()));

        btncerrarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ParseUser.getCurrentUser() != null && ParseFacebookUtils.getSession() != null){
                    ParseFacebookUtils.getSession().closeAndClearTokenInformation();
                    ParseUser.getCurrentUser().logOut();
                    Dialog_Descubre_Peru_Logout.this.hide();

                    actionBarActivity.finish();
                    actionBarActivity.startActivity(new Intent(getContext(),DescubrePeru.class));
                }
            }
        });
    }
}
