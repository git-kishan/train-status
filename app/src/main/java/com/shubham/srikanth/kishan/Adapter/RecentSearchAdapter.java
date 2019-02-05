package com.shubham.srikanth.kishan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.shubham.srikanth.kishan.DataModel;

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
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
