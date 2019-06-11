package com.sadek.dawemapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import com.sadek.dawemapp.R;
import com.sadek.dawemapp.model.response.ResturantResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RateAdatpter extends RecyclerView.Adapter<RateAdatpter.ViewHolder> {

    private Context context;

    private List<ResturantResponse> data;

    public RateAdatpter(Context context, List<ResturantResponse> data) {
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_rate, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
//                Bundle bundle = new Bundle();
//                bundle.putSerializable(Common.FIREBASE_DOCTOR, data.get(position));
//                ((MainActivity) context).switchToPage(3, bundle, R.string.about_the_doctor);

            }
        });

        try {


//        holder.user_name_tv.setText(data.get(position).getFirstName()+" "+data.get(position).getLastName());

       /* holder.user_comment_tv.setText(data.get(position).getComment()+"");
        holder.user_name_tv.setVisibility(View.GONE);
            String rate = (data.get(position).getRate() + "");
            Float rateF = Float.parseFloat(rate);
            holder.user_rating_bar.setRating(rateF);
*/

        }catch (Exception e){

        }


    }

    @Override
    public int getItemCount() {

        return 16;
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.user_name_tv)
        TextView user_name_tv;
        @BindView(R.id.user_comment_tv)
        TextView user_comment_tv;
        @BindView(R.id.review_date_tv)
        TextView review_date_tv;
        @BindView(R.id.user_rating_bar)
        RatingBar user_rating_bar;


        private ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
