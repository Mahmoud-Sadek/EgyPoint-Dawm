package com.sadek.dawemapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sadek.dawemapp.R;
import com.sadek.dawemapp.activity.MainActivity;
import com.sadek.dawemapp.adapter.CartItemAdatpter;
import com.sadek.dawemapp.adapter.MenuItemAdatpter;
import com.sadek.dawemapp.adapter.PhotoAdatpter;
import com.sadek.dawemapp.model.response.ResturantResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class CartDetailsFragment extends Fragment {


    Unbinder unbinder;

    @BindView(R.id.cart_item_details_rcy)
    RecyclerView cart_item_details_rcy;
    @BindView(R.id.cart_photos_rcy)
    RecyclerView cart_photos_rcy;



    CartItemAdatpter cartItemAdatpter;
    PhotoAdatpter photoAdatpter;
    List<ResturantResponse> resturantResponseList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart_details, container, false);
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
        final RecyclerView.LayoutManager mLayoutManager_ctegories_name = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        cart_item_details_rcy.setLayoutManager(mLayoutManager_ctegories_name);
        cartItemAdatpter = new CartItemAdatpter(resturantResponseList, getContext());
        cart_item_details_rcy.setAdapter(cartItemAdatpter);

        //my_orders_recycler
        final RecyclerView.LayoutManager mLayoutManager_cart_photos = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        cart_photos_rcy.setLayoutManager(mLayoutManager_cart_photos);
        photoAdatpter = new PhotoAdatpter(resturantResponseList, getContext());
        cart_photos_rcy.setAdapter(photoAdatpter);

    }

    //cart_detail_complete_order_btn
    @OnClick(R.id.cart_detail_complete_order_btn)
    void cart_detail_complete_order_btn(View view) {
        ((MainActivity)getContext()).switchToPage(6,null,R.string.app_name);
    }
}