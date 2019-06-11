package com.sadek.dawemapp.activity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.sadek.dawemapp.R;
import com.sadek.dawemapp.adapter.SpinnerAdapter;
import com.sadek.dawemapp.interfaces.SignupInterface;
import com.sadek.dawemapp.model.body.SignupBody;
import com.sadek.dawemapp.model.response.SignupResponse;
import com.sadek.dawemapp.presentsers.SignupPresenter;
import com.sadek.dawemapp.utils.Common;
import com.sadek.dawemapp.utils.GPSTracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SignupActivity extends BaseActitvty implements SignupInterface {

    Unbinder unbinder;

    @BindView(R.id.signup_phone_number_txt)
    EditText signup_phone_number_txt;
    @BindView(R.id.signup_user_name_txt)
    EditText signup_user_name_txt;
    @BindView(R.id.signup_location_txt)
    TextView signup_location_txt;
    @BindView(R.id.signup_password_txt)
    EditText signup_password_txt;
    @BindView(R.id.signup_confirm_password_txt)
    EditText signup_confirm_password_txt;
    @BindView(R.id.switch_use_current_location)
    Switch switch_use_current_location;
    @BindView(R.id.signup_privacy_checkbox)
    CheckBox signup_privacy_checkbox;
    AutocompleteSupportFragment place_autocomplete_fragment;


    @BindView(R.id.btnSignup)
    Button btnSignup;

    SignupBody signupBody;
    Place addressPlace;

    Spinner spinnerDropDown;
    String[] cities = {
            "AR",
            "EN"
    };
    private String latitude, longitude;
    public static KProgressHUD dialog = null;
    public static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ButterKnife.bind(this);
        dialog = new KProgressHUD(this);
/*        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), "AIzaSyCv_UH20G7Nz2TQfoQM4ePSYTMBkecQ-s4");
        }

        PlacesClient placesClient = Places.createClient(this);
        place_autocomplete_fragment = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        assert place_autocomplete_fragment != null;
        place_autocomplete_fragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));


        place_autocomplete_fragment.setOnPlaceSelectedListener(placeSelectionListener);*/

        // Get reference of SpinnerView from layout/main_activity.xml
        spinnerDropDown = (Spinner) findViewById(R.id.spinner1);
        List<String> stringList = new ArrayList<String>(Arrays.asList(cities));
        ArrayAdapter<String> adapter = new SpinnerAdapter(this,
                R.layout.item_login_language_spinner, stringList);

        spinnerDropDown.setAdapter(adapter);

        spinnerDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // Get select item
                int sid = spinnerDropDown.getSelectedItemPosition();
                Toast.makeText(getBaseContext(), "You have selected City : " + cities[sid],
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        switch_use_current_location.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getDeviceLocation();
                } else {
                    signup_location_txt.setText("");
                    signup_location_txt.setEnabled(true);
                }
            }
        });

    }

    GPSTracker gpsTracker;

    private void getDeviceLocation() {
        gpsTracker = new GPSTracker(this);
        if (!gpsTracker.canGetLocation()) {
            switch_use_current_location.setChecked(false);
            gpsTracker.showSettingsAlert();
        } else {
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 106);
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 125);

        }
        latitude = gpsTracker.getLatitude() + "";
        longitude = gpsTracker.getLongitude() + "";
        if (latitude.equals("0.0") && longitude.equals("0.0")) {
            switch_use_current_location.setChecked(false);
//            Toast.makeText(getContext(), R.string.cant_get_location, Toast.LENGTH_SHORT).show();

        } else {
            dialog.show();
            final Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
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
                    dialog.dismiss();
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

/*
    PlaceSelectionListener placeSelectionListener = new PlaceSelectionListener() {


        @Override
        public void onPlaceSelected(@NonNull Place place) {
            addressPlace = place;
            signup_location_txt.setText(place.getName());

            Log.e("position", addressPlace.getLatLng().latitude + "  " + addressPlace.getLatLng().longitude);
            Toast.makeText(getBaseContext(), addressPlace.getLatLng().latitude + " long" + addressPlace.getLatLng().longitude, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(Status status) {
            Log.i("position", "An error occurred: " + status);
        }
    };
*/

    @OnClick(R.id.btnSignup)
    void btnSignup(View view) {
        if (!validate())
            return;
        SignupPresenter.sendSignup(getBaseContext(), signupBody, this);
    }

    @OnClick(R.id.signup_location_txt)
    void signup_location_txt(View view) {
        Intent mapIntent = new Intent(getBaseContext(), MapsActivity.class);
        startActivityForResult(mapIntent, REQUEST_CODE);
    }


    private Boolean validate() {
        signup_phone_number_txt.setError(null);
        signup_user_name_txt.setError(null);
        signup_location_txt.setError(null);
        signup_password_txt.setError(null);
        signup_confirm_password_txt.setError(null);

        String location = signup_location_txt.getText().toString() + "location";
        String pass = signup_password_txt.getText().toString();
        String cobPass = signup_confirm_password_txt.getText().toString();
        String name = signup_user_name_txt.getText().toString();
        String phone = signup_phone_number_txt.getText().toString();
        if (phone.isEmpty() || phone.length() < 11) {
            signup_phone_number_txt.setError(getString(R.string.enter_valid_phone));
            return false;
        } else if (name.isEmpty() || name.length() < 3) {
            signup_user_name_txt.setError(getString(R.string.enter_valid_name));
            return false;
        } else if (location.isEmpty()) {
            signup_location_txt.setError(getString(R.string.enter_valid_location));
            return false;
        } else if (pass.isEmpty() || pass.length() < 8) {
            signup_password_txt.setError(getString(R.string.enter_valid_password));
            return false;
        } else if (!pass.equals(cobPass)) {
            signup_confirm_password_txt.setError(getString(R.string.enter_valid_password));
            return false;
        } else if (!signup_privacy_checkbox.isChecked()) {
            Toast.makeText(this, R.string.confirm_privacy, Toast.LENGTH_SHORT).show();
            return false;
        }

        signupBody = new SignupBody();
        signupBody.setPhone(phone);
        signupBody.setFullname(name);
        signupBody.setAddress(location);
        signupBody.setPassword(pass);
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

    @Override
    public void onSignupSuccess(SignupResponse response) {
        if (response.getAccountstate().equals(Common.ACCOUNT_STATUS_PENDING)) {
            Intent activeCodeIntent = new Intent(this, ConfirmCodeActivity.class);
            activeCodeIntent.putExtra(Common.USER_ID, response.getUserid());
            activeCodeIntent.putExtra(Common.USER_PHONE, signup_phone_number_txt.getText().toString());
            startActivity(activeCodeIntent);
            finish();
        }
    }

    @Override
    public void onErorr(String err) {
        Toast.makeText(this, err + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress(Boolean isShowing) {
        try {
            if (isShowing)
                dialog.show();
            else dialog.dismiss();
        } catch (Exception e) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            latitude = MapsActivity.mPosition.latitude + "";
            longitude = MapsActivity.mPosition.longitude + "";
            signup_location_txt.setText(MapsActivity.location);
        }

    }
}
