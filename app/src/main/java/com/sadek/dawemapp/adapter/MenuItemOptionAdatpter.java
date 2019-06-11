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


public class MenuItemOptionAdatpter extends RecyclerView.Adapter<MenuItemOptionAdatpter.ViewHolder> {

    private Context context;
    private List<ResturantResponse> data;
    int type;


    public MenuItemOptionAdatpter(List<ResturantResponse> data, Context context, int type) {
        this.context = context;
        this.data = data;
        this.type = type;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        if (type == 1)
            view = layoutInflater.inflate(R.layout.item_menu_item_details_check_option, parent, false);
        else
            view = layoutInflater.inflate(R.layout.item_menu_item_details_radio_option, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {


    }


    @Override
    public int getItemCount() {

//        return data.size();
        return 10;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

//        @BindView(R.id.patient_name_TV)
//        TextView patientNameTV;
//

        private ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
