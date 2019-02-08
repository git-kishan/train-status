package com.shubham.srikanth.kishan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shubham.srikanth.kishan.DataModel;
import com.shubham.srikanth.kishan.R;
import com.shubham.srikanth.kishan.ViewHolder.RecentSearchViewHolder;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecentSearchAdapter extends RecyclerView.Adapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<DataModel> list;
    public RecentSearchAdapter(Context context, ArrayList<DataModel> list){
        this.context=context;
        this.list=list;
        inflater=LayoutInflater.from(context);

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recentSearchView=inflater.inflate(R.layout.recent_search_item_view,parent,false );
        return new RecentSearchViewHolder(recentSearchView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof RecentSearchViewHolder){
            ((RecentSearchViewHolder) holder).fromStationCode.setText(list.get(position).getFromStationCode());
            ((RecentSearchViewHolder) holder).fromStationName.setText(list.get(position).getFromStationName());
            ((RecentSearchViewHolder) holder).toStationCode.setText(list.get(position).getToStationCode());
            ((RecentSearchViewHolder) holder).toStationName.setText(list.get(position).getToStationName());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
