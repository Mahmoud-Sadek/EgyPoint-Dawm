package com.sadek.dawemapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sadek.dawemapp.R;
import com.sadek.dawemapp.activity.MainActivity;
import com.sadek.dawemapp.adapter.CategoryMenuAdatpter;
import com.sadek.dawemapp.adapter.CategoryNameAdatpter;
import com.sadek.dawemapp.adapter.ResturantsAdatpter;
import com.sadek.dawemapp.model.response.ResturantResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class DetailsResturantFragment extends Fragment {


    Unbinder unbinder;

    //rate bottomsheet


    @BindView(R.id.ctegories_name_rcy)
    RecyclerView ctegories_name_rcy;

    @BindView(R.id.ctegories_menu_rcy)
    RecyclerView ctegories_menu_rcy;

    CategoryNameAdatpter categoryNameAdatpter;
    CategoryMenuAdatpter categoryMenuAdatpter;
    List<ResturantResponse> resturantResponseList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details_resturant, container, false);
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
        final RecyclerView.LayoutManager mLayoutManager_ctegories_name = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        ctegories_name_rcy.setLayoutManager(mLayoutManager_ctegories_name);
        categoryNameAdatpter = new CategoryNameAdatpter(resturantResponseList, getContext());
        ctegories_name_rcy.setAdapter(categoryNameAdatpter);

        //ctegories_menu_rcy
        final RecyclerView.LayoutManager mLayoutManager_ctegories_menu = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        ctegories_menu_rcy.setLayoutManager(mLayoutManager_ctegories_menu);
        categoryMenuAdatpter = new CategoryMenuAdatpter(resturantResponseList, getContext());
        ctegories_menu_rcy.setAdapter(categoryMenuAdatpter);


    }

    //details_restaurant_info_img
    @OnClick(R.id.details_restaurant_info_img)
    void details_restaurant_info_img(View view) {
        ((MainActivity)getContext()).switchToPage(8,null,R.string.app_name);
    }
    //details_restaurant_rating_layout
    @OnClick(R.id.details_restaurant_rating_layout)
    void details_restaurant_rating_layout(View view) {
        ResturantReviewsFragment bottomSheetFragment = new ResturantReviewsFragment();
        bottomSheetFragment.show(getChildFragmentManager(), bottomSheetFragment.getTag());

    }
}