package com.sadek.dawemapp.fragment;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sadek.dawemapp.R;
import com.sadek.dawemapp.adapter.RateAdatpter;
import com.sadek.dawemapp.model.response.ResturantResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ResturantReviewsFragment extends BottomSheetDialogFragment {


    Unbinder unbinder;

    @BindView(R.id.reviews_recycler)
    RecyclerView reviews_recycler;


    RateAdatpter rateAdatpter;
    List<ResturantResponse> resturantResponseList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reviews, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initUI();

    }

    private void initUI() {
        resturantResponseList = new ArrayList<>();


        //reviews_recycler
        final RecyclerView.LayoutManager mLayoutManager_ctegories_name = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        reviews_recycler.setLayoutManager(mLayoutManager_ctegories_name);
        rateAdatpter = new RateAdatpter(getContext(), resturantResponseList);
        reviews_recycler.setAdapter(rateAdatpter);


    }

}