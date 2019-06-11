package com.sadek.dawemapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sadek.dawemapp.R;
import com.sadek.dawemapp.model.response.ResturantResponse;

import java.util.List;

import butterknife.ButterKnife;


public class OrderTimeAdatpter extends RecyclerView.Adapter<OrderTimeAdatpter.ViewHolder> {

    private Context context;
    private List<ResturantResponse> data;


    List<ResturantResponse> resturantResponseList;

    public OrderTimeAdatpter(List<ResturantResponse> data, Context context) {
        this.context = context;
        this.data = data;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_order_time, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {



    }


    @Override
    public int getItemCount() {

//        return data.size();
return 14;
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        private ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
