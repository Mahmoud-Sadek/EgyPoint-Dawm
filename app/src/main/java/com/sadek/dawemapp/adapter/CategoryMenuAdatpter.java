package com.sadek.dawemapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sadek.dawemapp.R;
import com.sadek.dawemapp.model.response.ResturantResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CategoryMenuAdatpter extends RecyclerView.Adapter<CategoryMenuAdatpter.ViewHolder> {

    private Context context;
    private List<ResturantResponse> data;

    CategoryMenuItemAdatpter categoryMenuAdatpter;

    List<ResturantResponse> resturantResponseList;

    public CategoryMenuAdatpter(List<ResturantResponse> data, Context context) {
        this.context = context;
        this.data = data;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_details_category_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        //ctegories_menu_rcy
        final RecyclerView.LayoutManager mLayoutManager_ctegories_menu = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.ctegories_menu_rcy.setLayoutManager(mLayoutManager_ctegories_menu);
        categoryMenuAdatpter = new CategoryMenuItemAdatpter(resturantResponseList, context);
        holder.ctegories_menu_rcy.setAdapter(categoryMenuAdatpter);
        holder.ctegories_menu_rcy.setVisibility(View.GONE);

        holder.cardView_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.ctegories_menu_rcy.getVisibility() == View.GONE){
                    holder.ctegories_menu_rcy.setVisibility(View.VISIBLE);
                }else holder.ctegories_menu_rcy.setVisibility(View.GONE);
            }
        });

    }


    @Override
    public int getItemCount() {

//        return data.size();
return 4;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardView_menu)
        View cardView_menu;

        @BindView(R.id.ctegories_menu_rcy)
        RecyclerView ctegories_menu_rcy;

        private ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
