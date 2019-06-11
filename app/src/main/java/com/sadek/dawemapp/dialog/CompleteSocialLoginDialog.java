package com.sadek.dawemapp.dialog;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.sadek.dawemapp.R;
import com.sadek.dawemapp.model.body.SocialSignupBody;
import com.sadek.dawemapp.utils.GPSTracker;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CompleteSocialLoginDialog extends Dialog {


    @BindView(R.id.signup_phone_number_txt)
    EditText signup_phone_number_txt;
    @BindView(R.id.signup_user_name_txt)
    EditText signup_user_name_txt;
    @BindView(R.id.signup_location_txt)
    EditText signup_location_txt;
    @BindView(R.id.switch_use_current_location)
    Switch switch_use_current_location;
    @BindView(R.id.signup_privacy_checkbox)
    CheckBox signup_privacy_checkbox;
    AutocompleteSupportFragment place_autocomplete_fragment;

    @BindView(R.id.btnSignup)
    Button btnSignup;

    SocialSignupBody signupBody;
    Place addressPlace;
    private String latitude, longitude;


    public CompleteSocialLoginDialog(@NonNull Context context, final DialogInterAction dialogInterAction) {
        super(context);
        setContentView(R.layout.complete_social_dialog);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        ButterKnife.bind(this);

        if (!Places.isInitialized()) {
            Places.initialize(context, "AIzaSyCv_UH20G7Nz2TQfoQM4ePSYTMBkecQ-s4");
        }

        PlacesClient placesClient = Places.createClient(context);
        place_autocomplete_fragment = (AutocompleteSupportFragment) ((AppCompatActivity) context).getSupportFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        assert place_autocomplete_fragment != null;
        place_autocomplete_fragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));


        place_autocomplete_fragment.setOnPlaceSelectedListener(placeSelectionListener);

        switch_use_current_location.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getDeviceLocation(context);
                } else {
                    signup_location_txt.setText("");
                    signup_location_txt.setEnabled(true);
                }
            }
        });

        btnSignup = findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validate(context)) {
                    return;
                }

                dialogInterAction.onSubmit(signupBody);
            }
        });

        this.setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialogInterAction.onDismissed();
            }
        });
    }

    private boolean validate(Context context) {
        signup_phone_number_txt.setError(null);
        signup_user_name_txt.setError(null);
        signup_location_txt.setError(null);

        String location = signup_location_txt.getText().toString() + "location";
        String name = signup_user_name_txt.getText().toString();
        String phone = signup_phone_number_txt.getText().toString();
        if (phone.isEmpty() || phone.length() < 11) {
            signup_phone_number_txt.setError(context.getString(R.string.enter_valid_phone));
            return false;
        } else if (name.isEmpty() || name.length() < 3) {
            signup_user_name_txt.setError(context.getString(R.string.enter_valid_name));
            return false;
        } else if (location.isEmpty()) {
            signup_location_txt.setError(context.getString(R.string.enter_valid_location));
            return false;

        } else if (!signup_privacy_checkbox.isChecked()) {
            Toast.makeText(context, R.string.confirm_privacy, Toast.LENGTH_SHORT).show();
            return false;
        }

        signupBody = new SocialSignupBody();
        signupBody.setPhone(phone);
        signupBody.setFullname(name);
        signupBody.setAddress(location);
        signupBody.setLatitude(latitude);
        signupBody.setLongitude(longitude);
//        signupBody.setLatitude(addressPlace.getLatLng().latitude + "");
//        signupBody.setLongitude(addressPlace.getLatLng().longitude + "");
//        else if (!register_confirm_checkbox.isChecked()) {
////            register_phone_txt.setError(getString(R.string.enter_valid_phone));
//            return false;
//        }
        return true;
    }

    GPSTracker gpsTracker;

    private void getDeviceLocation(Context context) {
        gpsTracker = new GPSTracker(context);
        if (!gpsTracker.canGetLocation()) {
            switch_use_current_location.setChecked(false);
            gpsTracker.showSettingsAlert();
        } else {
            ((AppCompatActivity) context).requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 106);
            ((AppCompatActivity) context).requestPermissions(new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 125);

        }
        latitude = gpsTracker.getLatitude() + "";
        longitude = gpsTracker.getLongitude() + "";
        if (latitude.equals("0.0") && longitude.equals("0.0")) {
            switch_use_current_location.setChecked(false);
//            Toast.makeText(context, R.string.cant_get_location, Toast.LENGTH_SHORT).show();

        } else {
//            dialog.show();
            final Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            String strAdd = "";

            try {
                List<Address> addresses = geocoder.getFromLocation(gpsTracker.getLatitude(), gpsTracker.getLongitude(), 1);
                if (addresses != null) {
                    Address returnedAddress = addresses.get(0);
                    StringBuilder strReturnedAddress = new StringBuilder("");

                    for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                        strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                    }
                    strAdd = strReturnedAddress.toString();
//                    dialog.dismiss();
                    Log.w("Current loction", strReturnedAddress.toString());
                } else {
                    Log.w("Current loction", "No Address returned!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.w("Current loction", "Canont get Address!");
            }
            signup_location_txt.setText(strAdd + "");
            signup_location_txt.setEnabled(false);
        }
    }

    PlaceSelectionListener placeSelectionListener = new PlaceSelectionListener() {


        @Override
        public void onPlaceSelected(@NonNull Place place) {
            addressPlace = place;
            signup_location_txt.setText(place.getName());

            Log.e("position", addressPlace.getLatLng().latitude + "  " + addressPlace.getLatLng().longitude);
//            Toast.makeText(context, addressPlace.getLatLng().latitude + " long" + addressPlace.getLatLng().longitude, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(Status status) {
            Log.i("position", "An error occurred: " + status);
        }
    };

    public interface DialogInterAction {
        void onDismissed();

        void onSubmit(SocialSignupBody socialSignupBody);
    }
}