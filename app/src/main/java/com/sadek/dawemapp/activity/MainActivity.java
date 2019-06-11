package com.sadek.dawemapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.sadek.dawemapp.fragment.CartDetailsFragment;
import com.sadek.dawemapp.fragment.CompleteOrderFragment;
import com.sadek.dawemapp.fragment.DetailsResturantFragment;
import com.sadek.dawemapp.fragment.ListResturantsFragment;
import com.sadek.dawemapp.fragment.MainFragment;
import com.sadek.dawemapp.R;
import com.sadek.dawemapp.fragment.MenuItemDetailsFragment;
import com.sadek.dawemapp.fragment.OrderTrakingFragment;
import com.sadek.dawemapp.fragment.ResturantInfoFragment;
import com.sadek.dawemapp.fragment.ResturantReviewsFragment;

public class MainActivity extends BaseActitvty {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        switchToPage(1, null, R.string.app_name);
    }

    public void switchToPage(int page, Bundle bundle, int nameId) {

        FragmentTransaction transaction = getTransaction();
        String name = getResources().getString(nameId);
        Fragment nextFragment;
        switch (page) {

            case 1:
                nextFragment = new MainFragment();
                nextFragment.setArguments(bundle);
                transaction.replace(R.id.head_container, nextFragment, name);
                transaction.commit();
                break;

            case 2:
                nextFragment = new ListResturantsFragment();
                nextFragment.setArguments(bundle);
                transaction.replace(R.id.head_container, nextFragment, name);
                transaction.addToBackStack(name);
                transaction.commit();
                break;
            case 3:
                nextFragment = new DetailsResturantFragment();
                nextFragment.setArguments(bundle);
                transaction.replace(R.id.head_container, nextFragment, name);
                transaction.addToBackStack(name);
                transaction.commit();
                break;
            case 4:
                nextFragment = new MenuItemDetailsFragment();
                nextFragment.setArguments(bundle);
                transaction.replace(R.id.head_container, nextFragment, name);
                transaction.addToBackStack(name);
                transaction.commit();
                break;
            case 5:
                nextFragment = new CartDetailsFragment();
                nextFragment.setArguments(bundle);
                transaction.replace(R.id.head_container, nextFragment, name);
                transaction.addToBackStack(name);
                transaction.commit();
                break;
            case 6:
                nextFragment = new CompleteOrderFragment();
                nextFragment.setArguments(bundle);
                transaction.replace(R.id.head_container, nextFragment, name);
                transaction.addToBackStack(name);
                transaction.commit();
                break;
            case 7:
                nextFragment = new ResturantReviewsFragment();
                nextFragment.setArguments(bundle);
                transaction.replace(R.id.head_container, nextFragment, name);
                transaction.addToBackStack(name);
                transaction.commit();
                break;
            case 8:
                nextFragment = new ResturantInfoFragment();
                nextFragment.setArguments(bundle);
                transaction.replace(R.id.head_container, nextFragment, name);
                transaction.addToBackStack(name);
                transaction.commit();
                break;
            case 9:
                nextFragment = new OrderTrakingFragment();
                nextFragment.setArguments(bundle);
                transaction.replace(R.id.head_container, nextFragment, name);
                transaction.addToBackStack(name);
                transaction.commit();
                break;
        }
    }

}
