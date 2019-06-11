package com.sadek.dawemapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.sadek.dawemapp.R;
import com.sadek.dawemapp.activity.MainActivity;
import com.sadek.dawemapp.dialog.OrderTimeDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class CompleteOrderFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.complete_order_delivery_layout)
    View complete_order_delivery_layout;
    @BindView(R.id.complete_order_recive_layout)
    View complete_order_recive_layout;


    @BindView(R.id.complete_order_order_time_radio)
    RadioGroup complete_order_order_time_radio;

    @BindView(R.id.complete_order_delivery_status_radio)
    RadioGroup complete_order_delivery_status_radio;

    @BindView(R.id.complete_order_order_time_start_spinner)
    Spinner complete_order_order_time_start_spinner;
    @BindView(R.id.complete_order_order_time_end_spinner)
    Spinner complete_order_order_time_end_spinner;

    String[] time = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_complete_order, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        initUi();
        CartDetailsCompleteFragment bottomSheetFragment = new CartDetailsCompleteFragment();
        bottomSheetFragment.show(getChildFragmentManager(), bottomSheetFragment.getTag());

    }

    private void initUi() {

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter startAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, time);
        startAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        complete_order_order_time_start_spinner.setAdapter(startAdapter);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter endAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, time);
        endAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        complete_order_order_time_end_spinner.setAdapter(endAdapter);


        complete_order_delivery_status_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.complete_order_deilvery_radio) {
                    complete_order_recive_layout.setVisibility(View.GONE);
                    complete_order_delivery_layout.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.complete_order_recive_radio) {
                    complete_order_recive_layout.setVisibility(View.VISIBLE);
                    complete_order_delivery_layout.setVisibility(View.GONE);
                }
            }
        });

        complete_order_order_time_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                OrderTimeDialog orderTimeDialog = new OrderTimeDialog(getContext(), new OrderTimeDialog.DialogInterAction() {
                    @Override
                    public void onDismissed() {

                    }
                });
                orderTimeDialog.show();


            }
        });

    }

    //complete_order_order_details_btn
    @OnClick(R.id.complete_order_order_details_btn)
    void complete_order_order_details_btn(View view) {
        CartDetailsCompleteFragment bottomSheetFragment = new CartDetailsCompleteFragment();
        bottomSheetFragment.show(getChildFragmentManager(), bottomSheetFragment.getTag());

    }

    //complete_order_complete_order_btn
    @OnClick(R.id.complete_order_complete_order_btn)
    void complete_order_complete_order_btn(View view) {
        ((MainActivity)getContext()).switchToPage(9,null,R.string.app_name);

    }

}
