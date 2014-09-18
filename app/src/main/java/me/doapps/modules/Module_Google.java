package me.doapps.modules;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.Request;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import me.doapps.beans.Place_DTO;
import me.doapps.beans.Temp_Place_DTO;
import me.doapps.ws.WS_Descubre;

/**
 * Created by jnolascob on 17/09/2014.
 */
public class Module_Google {
    private Context context;

    private Interface_NearbyPlace interface_nearbyPlace;
    private Interface_TextSearch interface_textSearch;
    private Interface_DetailPlace interface_detailPlace;
    private Interface_Search interface_search;

    private String client_id = "3PILMRCUAH0DXRK1U3F0RR34CLIZKL1QYH0KZEPHUEU0DIEQ";
    private String client_secret = "2VMV5UJIUWVOGXU5TEPFLIBNGW002KT3KAL3MBNZ3GZYCZIT";

    /* obtenemos ddmmAAAA */
    Calendar c = Calendar.getInstance();
    SimpleDateFormat d = new SimpleDateFormat("yyyyMMdd");
    String date_fsq = d.format(c.getTime());

    public Module_Google(Context context){
        this.context = context;
    }

    /*Nearby Search - Foursquare*/
    public void sendRequestSearch(final String location){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest jsonObjectRequest = new StringRequest(
                com.android.volley.Request.Method.GET,
                WS_Descubre.WS_FOUR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            ArrayList<Temp_Place_DTO> place_dtos = new ArrayList<Temp_Place_DTO>();
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObjectResult = jsonObject.getJSONObject("response");

                            JSONArray jsonArray = jsonObjectResult.getJSONArray("venues");

                            Log.e("four_request", jsonObjectResult.toString());

                            if(true){
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    Temp_Place_DTO temp_place_dto = new Temp_Place_DTO();

                                    String temp_name = jsonArray.getJSONObject(i).getString("name");
                                    /*String temp_data_detail = jsonArray.getJSONObject(i).toString();
                                    String lat = jsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getString("lat");
                                    String lng = jsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getString("lng");
                                    ParseGeoPoint temp_geopoint = new ParseGeoPoint(Double.valueOf(lat), Double.valueOf(lng));*/

                                    place_dtos.add(new Temp_Place_DTO(temp_name, ""));
                                }
                                interface_search.getSearch(true, place_dtos);
                            }else{
                                interface_search.getSearch(false, null);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            interface_search.getSearch(false, null);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(context, "Ocurrio un error de Conexion", Toast.LENGTH_SHORT).show();
                    }
                }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                Log.e("params", params.toString());
                params.put("ll", "-12.0571345,-77.0373154");
                params.put("client_id", client_id);
                params.put("client_secret", client_secret);
                params.put("v", "20140918");
                Log.e("params", params.toString());
                return params;
            }
        };
        queue.add(jsonObjectRequest);
    }

    /*Nearby Search*/
    public void sendRequestNearbySearch(final String location){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest jsonObjectRequest = new StringRequest(
                com.android.volley.Request.Method.GET,
                WS_Descubre.WS_NEARBY_SEARCH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            ArrayList<Temp_Place_DTO> place_dtos = new ArrayList<Temp_Place_DTO>();
                            JSONObject jsonObject = new JSONObject(response);

                            JSONArray jsonArray = jsonObject.getJSONArray("results");
                            Log.e("response", jsonArray.toString());

                            if(true){
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    Temp_Place_DTO temp_place_dto = new Temp_Place_DTO();

                                    String temp_name = jsonArray.getJSONObject(i).getString("name");
                                    String temp_data_detail = jsonArray.getJSONObject(i).toString();
                                    String lat = jsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getString("lat");
                                    String lng = jsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getString("lng");
                                    ParseGeoPoint temp_geopoint = new ParseGeoPoint(Double.valueOf(lat), Double.valueOf(lng));

                                    place_dtos.add(new Temp_Place_DTO(temp_name, ""));
                                }
                                interface_nearbyPlace.getNearbyPlace(true, place_dtos);
                            }else{
                                interface_nearbyPlace.getNearbyPlace(false, null);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            interface_nearbyPlace.getNearbyPlace(false, null);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(context, "Ocurrio un error de Conexion", Toast.LENGTH_SHORT).show();
                    }
                }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                Log.e("params", params.toString());
                params.put("location", location);
                params.put("radius", "500");
                params.put("types", "");
                params.put("name", "");
                params.put("sensor", "true");
                params.put("key", "AIzaSyC7VTy1fw8NrWq6Nfs_UHj6OuTnZqmjeP4");
                Log.e("params", params.toString());
                return params;
            }
        };
        queue.add(jsonObjectRequest);
    }

    /*Text Search*/
    public void sendRequestTextSearch(){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest jsonObjectRequest = new StringRequest(
                com.android.volley.Request.Method.GET,
                WS_Descubre.WS_TEXT_SEARCH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            ArrayList<Place_DTO> place_dtos = new ArrayList<Place_DTO>();
                            JSONObject jsonObject = new JSONObject(response);

                            String status = jsonObject.getString("status");
                            JSONArray jsonArray = jsonObject.getJSONArray("results");
                            if(status.equals("OK")){
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    Place_DTO place_dto = new Place_DTO();

                                    String temp_name = jsonArray.getJSONObject(i).getString("name");
                                    String temp_data_detail = jsonArray.getJSONObject(i).toString();
                                    String lat = jsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getString("lat");
                                    String lng = jsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getString("lng");
                                    ParseGeoPoint temp_geopoint = new ParseGeoPoint(Double.valueOf(lat), Double.valueOf(lng));
                                    String temp_flag = "google";

                                    place_dto.setPlace_name(temp_name);
                                    place_dto.setPlace_json_data_detail(temp_data_detail);
                                    place_dto.setPlace_geopoint(temp_geopoint);
                                    place_dto.setPlace_flag_user(temp_flag);

                                    place_dtos.add(place_dto);
                                }
                                interface_textSearch.getTextSearch(true, place_dtos);
                            }else{
                                interface_textSearch.getTextSearch(false, null);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            interface_textSearch.getTextSearch(false, null);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(context, "Ocurrio un error de Conexion", Toast.LENGTH_SHORT).show();
                    }
                }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        queue.add(jsonObjectRequest);
    }

    /*Detail Place*/
    public void sendRequestDetailPlace(){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest jsonObjectRequest = new StringRequest(
                com.android.volley.Request.Method.GET,
                WS_Descubre.WS_DETAIL_PLACE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            ArrayList<Place_DTO> place_dtos = new ArrayList<Place_DTO>();
                            JSONObject jsonObject = new JSONObject(response);

                            String status = jsonObject.getString("status");
                            JSONArray jsonArray = jsonObject.getJSONArray("results");
                            if(status.equals("OK")){
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    Place_DTO place_dto = new Place_DTO();

                                    String temp_name = jsonArray.getJSONObject(i).getString("name");
                                    String temp_data_detail = jsonArray.getJSONObject(i).toString();
                                    String lat = jsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getString("lat");
                                    String lng = jsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getString("lng");
                                    ParseGeoPoint temp_geopoint = new ParseGeoPoint(Double.valueOf(lat), Double.valueOf(lng));
                                    String temp_flag = "google";

                                    place_dto.setPlace_name(temp_name);
                                    place_dto.setPlace_json_data_detail(temp_data_detail);
                                    place_dto.setPlace_geopoint(temp_geopoint);
                                    place_dto.setPlace_flag_user(temp_flag);

                                    place_dtos.add(place_dto);
                                }
                                interface_textSearch.getTextSearch(true, place_dtos);
                            }else{
                                interface_textSearch.getTextSearch(false, null);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            interface_textSearch.getTextSearch(false, null);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(context, "Ocurrio un error de Conexion", Toast.LENGTH_SHORT).show();
                    }
                }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("query", "cafe+lima");
                params.put("sensor", "true");
                params.put("key", "");

                return params;
            }
        };
        queue.add(jsonObjectRequest);
    }

    /*Nearby Search*/
    public void setInterface_nearbyPlace(Interface_NearbyPlace interface_nearbyPlace){
        this.interface_nearbyPlace = interface_nearbyPlace;

    }
    /*inner interface - Nearby Search*/
    public interface Interface_NearbyPlace{
        void getNearbyPlace(boolean status, ArrayList<Temp_Place_DTO> place_dtos);
    }

    /*Text Search*/
    public void setInterface_textSearch(Interface_TextSearch interface_textSearch){
        this.interface_textSearch = interface_textSearch;
    }

    /*inner interface - Text Search*/
    public interface Interface_TextSearch{
        void getTextSearch(boolean status, ArrayList<Place_DTO> place_dtos);
    }

    /*Detail Place*/
    public void setInterface_detailPlace(Interface_DetailPlace interface_detailPlace){
        this.interface_detailPlace = interface_detailPlace;
    }

    /*inner interface - Detail Place*/
    public interface Interface_DetailPlace{
        void getDetailPlace(boolean status, ArrayList<Place_DTO> place_dtos);
    }

    /*Search Foursquare*/
    public void setInterface_search(Interface_Search interface_search){
        this.interface_search = interface_search;
    }

    /*inner interface - Search Foursquare*/
    public interface Interface_Search{
        void getSearch(boolean status, ArrayList<Temp_Place_DTO> place_dtos);
    }
}
