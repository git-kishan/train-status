package com.shubham.srikanth.kishan.ViewHolder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.shubham.srikanth.kishan.Interface.SearchItemClickListener;
import com.shubham.srikanth.kishan.R;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class SearchViewHolder extends RecyclerView.ViewHolder {

    public  TextView stationName,stationCode;
    private SearchItemClickListener listener;
    private CardView cardView;
    public SearchViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        listener= (SearchItemClickListener) context;
        stationCode=itemView.findViewById(R.id.station_code);
        stationName=itemView.findViewById(R.id.station_name);
        cardView=itemView.findViewById(R.id.card_View_root);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onSearchItemClicked(getAdapterPosition());
            }
        });


    }


}

