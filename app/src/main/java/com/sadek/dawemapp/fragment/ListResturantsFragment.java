package com.sadek.dawemapp.fragment;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sadek.dawemapp.R;
import com.sadek.dawemapp.adapter.ResturantsAdatpter;
import com.sadek.dawemapp.model.response.ResturantResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.khrystal.library.widget.CircleRecyclerView;
import me.khrystal.library.widget.CircularHorizontalMode;
import me.khrystal.library.widget.ItemViewMode;


public class ListResturantsFragment extends Fragment {


    Unbinder unbinder;

    @BindView(R.id.resturants_rcy)
    RecyclerView resturants_rcy;
    @BindView(R.id.resturants2_rcy)
    RecyclerView resturants2_rcy;


    ResturantsAdatpter resturantsAdatpter;
    ResturantsAdatpter resturants2Adatpter;
    List<ResturantResponse> resturantResponseList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_resturants, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initUI();

    }
    private void initUI() {
        resturantResponseList = new ArrayList<>();




        //my_orders_recycler
        final RecyclerView.LayoutManager mLayoutManager_resturants = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        resturants_rcy.setLayoutManager(mLayoutManager_resturants);
        resturantsAdatpter = new ResturantsAdatpter(resturantResponseList, getContext());
        resturants_rcy.setAdapter(resturantsAdatpter);

        //resturants2_rcy
        final RecyclerView.LayoutManager mLayoutManager_resturants2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        resturants2_rcy.setLayoutManager(mLayoutManager_resturants2);
        resturants2Adatpter = new ResturantsAdatpter(resturantResponseList, getContext());
        resturants2_rcy.setAdapter(resturants2Adatpter);


    }

    @OnClick(R.id.resturants_add_img)
    void docotr_profile_img(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View viewImg = inflater.inflate(R.layout.image_dilog, null);
        builder.setView(viewImg);
        ImageView imgView = (ImageView) viewImg.findViewById(R.id.imageView);
        Glide.with(getContext()).load(R.drawable.logo).into(imgView);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}