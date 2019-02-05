package com.shubham.srikanth.kishan.FragmentPackage;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.shubham.srikanth.kishan.R;


public class TrainFareFragment extends BottomSheetDialogFragment {

    private CardView cardView;
    private LinearLayout linearLayout;
    private BottomSheetBehavior bottomSheetBehavior;

    public TrainFareFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_train_fare, container, false);
        cardView=view.findViewById(R.id.root_layout_cardview);
        linearLayout=getActivity().findViewById(R.id.search_view_root_layout);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"clicked" ,Toast.LENGTH_SHORT ).show();
                bottomSheetBehavior=BottomSheetBehavior.from(linearLayout);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
    }
}
