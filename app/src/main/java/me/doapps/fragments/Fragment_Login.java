package me.doapps.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.model.GraphUser;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import me.doapps.beans.User_DTO;
import me.doapps.descubreperu.DescubrePeru;
import me.doapps.descubreperu.R;
import me.doapps.dialog.Dialog_Descubre_Peru;
import me.doapps.utils.Util_Fonts;

/**
 * Created by Gantz on 17/09/14.
 */
public class Fragment_Login extends Fragment_Master {

    public static Fragment_Login newInstance() {
        return new Fragment_Login();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showMenu(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button btntiwtter = (Button)getView().findViewById(R.id.btnentrartwitter);
        Button btnfacebook = (Button)getView().findViewById(R.id.btnentrarfacebook);

        btntiwtter.setTypeface(Util_Fonts.setNexaBold(getActivity()));
        btnfacebook.setTypeface(Util_Fonts.setNexaBold(getActivity()));

        getView().findViewById(R.id.btnentrarfacebook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog_Descubre_Peru dialog_descubre_peru = new Dialog_Descubre_Peru(getActivity());
                dialog_descubre_peru.show();

                List<String> permissions = Arrays.asList("email", "public_profile", "user_friends");
                ParseFacebookUtils.logIn(permissions,getActivity(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException err) {
                        dialog_descubre_peru.hide();
                        if (user == null) {
                            Log.e("MyApp", "Uh oh. The user cancelled the Facebook login.");
                        } else if (user.isNew()) {
                            makeMeRequest();
                            FragmentManager manager = ((DescubrePeru)getActivity()).getSupportFragmentManager();
                            FragmentTransaction trans = manager.beginTransaction();
                            trans.remove(Fragment_Login.this);
                            trans.commit();
                            manager.popBackStack();

                        } else {
                            makeMeRequest();
                            FragmentManager manager = ((DescubrePeru)getActivity()).getSupportFragmentManager();
                            FragmentTransaction trans = manager.beginTransaction();
                            trans.remove(Fragment_Login.this);
                            trans.commit();
                            manager.popBackStack();
                        }
                    }
                });
            }
        });

        getView().findViewById(R.id.btnentrartwitter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void makeMeRequest() {
        Request request = Request.newMeRequest(ParseFacebookUtils.getSession(),
                new Request.GraphUserCallback() {
                    @Override
                    public void onCompleted(GraphUser user, Response response) {
                        if (user != null) {

                            String nombre = (String) user.getProperty("first_name");
                            String apellido = user.getLastName();
                            String sexo = (String) user.getProperty("gender");
                            String nacimiento = user.getBirthday();
                            String email = (String) user.asMap().get("email");
                            String password = "";
                            String nick = (String) user.getProperty("middle_name");
                            String facebookId = user.getId();

                            try {

                                JSONObject jsonObjectUsuario = new JSONObject();
                                jsonObjectUsuario.put("nombre", nombre);
                                jsonObjectUsuario.put("apellido", apellido);
                                jsonObjectUsuario.put("sexo", sexo);
                                jsonObjectUsuario.put("nacimiento", nacimiento);
                                jsonObjectUsuario.put("email", email);
                                jsonObjectUsuario.put("password", password);
                                jsonObjectUsuario.put("nick", nick);
                                jsonObjectUsuario.put("facebook_id", facebookId);
                                jsonObjectUsuario.put("tipo_registro", "2");

                                User_DTO user_dto = (User_DTO) User_DTO.getCurrentUser();
                                user_dto.setUser_name(nombre);
                                user_dto.setUser_social_id(user.getId());
                                user_dto.setUsername(nombre + " " + apellido);
                                user_dto.setUser_url_image("https://graph.facebook.com/" + user.getId() + "/picture");
                                user_dto.setUser_json_data(jsonObjectUsuario.toString());
                                user_dto.saveInBackground();

                                //progressDialog = ProgressDialog.show(getActivity(), null, "Registrando...", true);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
        request.executeAsync();
    }
}
