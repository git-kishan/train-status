package com.shubham.srikanth.kishan.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.shubham.srikanth.kishan.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecentSearchViewHolder extends RecyclerView.ViewHolder {

    public TextView fromStationName,fromStationCode,toStationName,toStationCode;
    public RecentSearchViewHolder(@NonNull View itemView) {
        super(itemView);
        fromStationCode=itemView.findViewById(R.id.from_station_code);
        fromStationName=itemView.findViewById(R.id.from_station_name);
        toStationCode=itemView.findViewById(R.id.to_station_code);
        toStationName=itemView.findViewById(R.id.to_station_name);
    }
}
