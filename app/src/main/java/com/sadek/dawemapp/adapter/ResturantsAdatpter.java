package com.sadek.dawemapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sadek.dawemapp.R;
import com.sadek.dawemapp.activity.MainActivity;
import com.sadek.dawemapp.model.response.ResturantResponse;


import java.util.List;

import butterknife.ButterKnife;


public class ResturantsAdatpter extends RecyclerView.Adapter<ResturantsAdatpter.ViewHolder> {

    private Context context;
    private List<ResturantResponse> data;



    public ResturantsAdatpter( List<ResturantResponse> data,Context context) {
        this.context = context;
        this.data = data;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_resturant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).switchToPage(3,null,R.string.app_name);
            }
        });


    }


    @Override
    public int getItemCount() {

//        return data.size();
return 3;
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
