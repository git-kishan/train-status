package com.shubham.srikanth.kishan.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.shubham.srikanth.kishan.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchViewHolder extends RecyclerView.ViewHolder {

    public  TextView stationName,stationCode;
    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);
        stationCode=itemView.findViewById(R.id.station_code);
        stationName=itemView.findViewById(R.id.station_name);

    }
}
