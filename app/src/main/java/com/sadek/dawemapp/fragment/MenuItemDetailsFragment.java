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
import com.sadek.dawemapp.adapter.CategoryMenuAdatpter;
import com.sadek.dawemapp.adapter.CategoryNameAdatpter;
import com.sadek.dawemapp.adapter.MenuItemAdatpter;
import com.sadek.dawemapp.model.response.ResturantResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MenuItemDetailsFragment extends Fragment {


    Unbinder unbinder;

    @BindView(R.id.menu_item_details_rcy)
    RecyclerView menu_item_details_rcy;



    MenuItemAdatpter menuItemAdatpter;
    List<ResturantResponse> resturantResponseList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu_item_details, container, false);
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
        menu_item_details_rcy.setLayoutManager(mLayoutManager_ctegories_name);
        menuItemAdatpter = new MenuItemAdatpter(resturantResponseList, getContext());
        menu_item_details_rcy.setAdapter(menuItemAdatpter);

    }

    //cart_txt

    @OnClick(R.id.cart_txt)
    void cart_txt(View view) {
                ((MainActivity)getContext()).switchToPage(5,null,R.string.app_name);

    }
}