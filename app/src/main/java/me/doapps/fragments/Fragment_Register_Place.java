package me.doapps.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.SaveCallback;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import me.doapps.beans.Place_DTO;
import me.doapps.descubreperu.DescubrePeru;
import me.doapps.descubreperu.R;
import me.doapps.dialog.Dialog_Descubre_Peru;
import me.doapps.utils.FileHelper;
import me.doapps.utils.Util_GPS;

/**
 * Created by jnolascob on 18/09/2014.
 */
public class Fragment_Register_Place extends Fragment_Master implements View.OnClickListener {

    private GoogleMap map;
    private EditText nombrelocal;
    private EditText desclocal;
    private EditText comentlocal;
    private static View view;

    static final int REQUEST_TAKE_PHOTO = 11111;
    public TextView addImage;
    public ImageView imgLocal;

    public static final Fragment_Register_Place newInstance(){
        return new Fragment_Register_Place();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.fragment_register_place, container, false);
            setUpMapIfNeeded();
        } catch (InflateException e) {}

        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpMapIfNeeded();

        nombrelocal = (EditText)getView().findViewById(R.id.edtnombrelocal);
        desclocal = (EditText)getView().findViewById(R.id.edtdescripcionlocal);
        comentlocal = (EditText)getView().findViewById(R.id.edtcomentariolocal);
        addImage = (TextView) getView().findViewById(R.id.btn_add_image_place);
        imgLocal = (ImageView) getView().findViewById(R.id.imagelocal);

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        getView().findViewById(R.id.btnregistrarplace).setOnClickListener(this);
    }

    /*Google Maps*/
    private void setUpMapIfNeeded() {
        if (map == null) {
            map = ((SupportMapFragment) ((DescubrePeru)getActivity()).getSupportFragmentManager().findFragmentById(R.id.place_map)).getMap();
            if (map != null) {
                setUpMap();
            }
        }
    }

    private DescubrePeru getActivityInstance(){
        return (DescubrePeru)getActivity();
    }

    private void setUpMap() {
        Util_GPS util_gps = new Util_GPS(getActivity());

        if(util_gps.canGetLocation()){
            //mMap.setMyLocationEnabled(true);
            map.getUiSettings().setZoomControlsEnabled(false);

            Marker marker = map.addMarker(new MarkerOptions()
                    .position(new LatLng(util_gps.getLatitude(), util_gps.getLongitude()))
                            //.title("NOMADAPP")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.icono_ruta_gastronomica)));
            //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

            CameraPosition camPos = new CameraPosition.Builder()
                    .target(new LatLng(util_gps.getLatitude(),util_gps.getLongitude()))
                    .zoom(16)
                    .build();
            CameraUpdate camUpd3 = CameraUpdateFactory.newCameraPosition(camPos);
            map.animateCamera(camUpd3);
            marker.showInfoWindow();

        }else{
            showSettingsAlert();
        }
    }

    public void showSettingsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Aviso");
        alertDialog.setMessage("Descubre necesita que actives tu GPS");
        alertDialog.setPositiveButton("Activar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 1);
            }
        });
        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    /**
     * Called when a view has been clicked.
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        final Dialog_Descubre_Peru dialog_descubre_peru = new Dialog_Descubre_Peru(getActivity());
        dialog_descubre_peru.show();

        DescubrePeru activity = (DescubrePeru)getActivity();
        Place_DTO place_dto = new Place_DTO();

        place_dto.setPlace_name(nombrelocal.getText().toString());
        place_dto.setPlace_description(desclocal.getText().toString());
        place_dto.setPlace_flag_user("USER");
        place_dto.setPlace_geopoint(new ParseGeoPoint(-12.0571345,-77.0373154));

        BitmapFactory.Options bitmapFactory = new BitmapFactory.Options();
        bitmapFactory.inSampleSize = 4;
        bitmapFactory.inPurgeable = true;
        bitmapFactory.inInputShareable = true;
        Bitmap bitmap = BitmapFactory.decodeFile(activity.getCurrentPhotoPath(),bitmapFactory);

        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        double width = bitmap.getWidth();
        double height = bitmap.getHeight();
        double ratio = 300 / width;
        int newheight = (int) (ratio * height);
        bitmap = Bitmap.createScaledBitmap(bitmap,400,newheight, true);
        bitmap.compress(Bitmap.CompressFormat.JPEG,20, bao);
        byte[] fileBytes = bao.toByteArray();

        String fileName = FileHelper.getFileName(getActivity(),activity.getCapturedImageURI(),"");
        ParseFile file = new ParseFile(fileName, fileBytes);

        place_dto.setPlace_parsefile_image(file);
        place_dto.setPlace_json_array_url_images_user("NONE");

        place_dto.setPlace_json_array_url_images_google("NONE");

        place_dto.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                dialog_descubre_peru.hide();
            }
        });
    }

    /**
     * Start the camera by dispatching a camera intent.
     */
    protected void dispatchTakePictureIntent() {

        // Check if there is a camera.
        Context context = getActivity();
        PackageManager packageManager = context.getPackageManager();
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA) == false){
            Toast.makeText(getActivity(), "This device does not have a camera.", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        // Camera exists? Then proceed...
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Ensure that there's a camera activity to handle the intent
        DescubrePeru activity = (DescubrePeru)getActivity();
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            // Create the File where the photo should go.
            // If you don't do this, you may get a crash in some devices.
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast toast = Toast.makeText(activity, "There was a problem saving the photo...", Toast.LENGTH_SHORT);
                toast.show();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri fileUri = Uri.fromFile(photoFile);
                activity.setCapturedImageURI(fileUri);
                activity.setCurrentPhotoPath(fileUri.getPath());
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,activity.getCapturedImageURI());
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    /**
     * The activity returns with the photo.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == Activity.RESULT_OK) {
            addPhotoToGallery();
            DescubrePeru activity = (DescubrePeru)getActivity();
            Picasso.with(activity).load("file://" + Uri.parse(activity.getCurrentPhotoPath())).into(imgLocal);
            addImage.setVisibility(View.GONE);
            imgLocal.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(getActivity(), "Image Capture Failed", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    /**
     * Creates the image file to which the image must be saved.
     * @return
     * @throws IOException
     */
    protected File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        DescubrePeru activity = (DescubrePeru)getActivity();
        activity.setCurrentPhotoPath("file:" + image.getAbsolutePath());
        return image;
    }

    /**
     * Add the picture to the photo gallery.
     * Must be called on all camera images or they will
     * disappear once taken.
     */
    protected void addPhotoToGallery() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        DescubrePeru activity = (DescubrePeru)getActivity();
        File f = new File(activity.getCurrentPhotoPath());
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.getActivity().sendBroadcast(mediaScanIntent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Fragment fragment = (getFragmentManager().findFragmentById(R.id.place_map));
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.remove(fragment);
        ft.commit();
    }
}
