package com.shubham.srikanth.kishan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shubham.srikanth.kishan.DataModel;
import com.shubham.srikanth.kishan.R;
import com.shubham.srikanth.kishan.ViewHolder.SearchViewHolder;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchAdapter extends RecyclerView.Adapter {

    private ArrayList<DataModel> dataList;
    private Context context;
    private LayoutInflater inflater;
    public SearchAdapter(Context context, ArrayList<DataModel> dataList){
        this.context=context;
        this.dataList=dataList;
        inflater=LayoutInflater.from(context);

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.search_item_view,parent,false );
        return new SearchViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof SearchViewHolder){
            ((SearchViewHolder) holder).stationName.setText(dataList.get(position).getStationName());
            ((SearchViewHolder) holder).stationCode.setText( dataList.get(position).getStationCode());

            String stationName=dataList.get(position).getStationName();
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void updateList(ArrayList<DataModel> list){
        dataList=new ArrayList<>();
        dataList.addAll(list);
        notifyDataSetChanged();
    }
}
