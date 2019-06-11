package com.sadek.dawemapp.activity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.sadek.dawemapp.R;

import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MapsActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    private GoogleMap mMap;
    public static LatLng mPosition;
    public static KProgressHUD dialog = null;
    static String location ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
        dialog = new KProgressHUD(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.confirm_address_map_fragment);
        mapFragment.getMapAsync(this);
    }

    @OnClick(R.id.saveBtn)
    void saveBtn(View view) {

//        mPosition.latitude);
//        clinicDoctor.setLang(mPosition.longitude);
//        clinicDoctor.setLocationEN(getCompleteAddressString(mPosition.latitude, mPosition.longitude));
//        clinicDoctor.setLocationAR(getCompleteARAddressString(mPosition.latitude, mPosition.longitude));
        getCompleteAddressString(mPosition.latitude, mPosition.longitude);

    }

    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        dialog.show();
        String strAdd = "";
        Geocoder geocoderEn = new Geocoder(this, Locale.ENGLISH);

        try {
            List<Address> addresses = geocoderEn.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w(" loction ", strReturnedAddress.toString());
            } else {
                Log.w("loction", "No Address returned!");
            }
            location = strAdd;
            dialog.dismiss();
            onBackPressed();

        } catch (Exception e) {
            e.printStackTrace();
            Log.w("loction", "Canont get Address!");
        }
        return strAdd;
    }

    private String getCompleteARAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Locale loc = new Locale("ar");
        Geocoder geocoderAr = new Geocoder(this, loc);
        try {
            List<Address> addresses = geocoderAr.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w(" loction ", strReturnedAddress.toString());
            } else {
                Log.w("loction", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("loction", "Canont get Address!");
        }
        return strAdd;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(31.205753, 29.924526);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 12.0f));

        googleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                // Cleaning all the markers.
                if (googleMap != null) {
                    googleMap.clear();
                }

                mPosition = googleMap.getCameraPosition().target;
//                mZoom = googleMap.getCameraPosition().zoom;

            }
        });
    }


}
