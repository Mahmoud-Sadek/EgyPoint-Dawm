package com.sadek.dawemapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sadek.dawemapp.R;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<String> {

    private List<String> objects;
    private Context context;

    public SpinnerAdapter(Context context, int resourceId,
                              List<String> objects) {
        super(context, resourceId, objects);
        this.objects = objects;
        this.context = context;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=(LayoutInflater) context.getSystemService(  Context.LAYOUT_INFLATER_SERVICE );
        View row=inflater.inflate(R.layout.item_login_language_spinner, parent, false);
        TextView label=(TextView)row.findViewById(R.id.spinner_txt);
        label.setText(objects.get(position));

        /*if (position == 0) {//Special style for dropdown header
            label.setTextColor(context.getResources().getColor(android.R.color.white));
        }*/

        return row;
    }

}
