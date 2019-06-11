package com.sadek.dawemapp.dialog;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.sadek.dawemapp.adapter.OrderTimeAdatpter;
import com.sadek.dawemapp.adapter.ResturantsAdatpter;
import com.sadek.dawemapp.model.body.SocialSignupBody;
import com.sadek.dawemapp.model.response.ResturantResponse;
import com.sadek.dawemapp.utils.GPSTracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class OrderTimeDialog extends Dialog {


    @BindView(R.id.order_time_recycler)
    RecyclerView order_time_recycler;

    List<ResturantResponse> resturantResponseList;
    OrderTimeAdatpter orderTimeAdatpter;

    public OrderTimeDialog(@NonNull Context context, final DialogInterAction dialogInterAction) {
        super(context);
        setContentView(R.layout.dialog_order_time);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        ButterKnife.bind(this);


        resturantResponseList = new ArrayList<>();

        //order_time_recycler
        final RecyclerView.LayoutManager mLayoutManager_order_time = new GridLayoutManager(getContext(),6);
        order_time_recycler.setLayoutManager(mLayoutManager_order_time);
        orderTimeAdatpter = new OrderTimeAdatpter(resturantResponseList, getContext());
        order_time_recycler.setAdapter(orderTimeAdatpter);


    }


    public interface DialogInterAction {
        void onDismissed();

//        void onSubmit(SocialSignupBody socialSignupBody);
    }
}