package com.shubham.srikanth.kishan.FragmentPackage;


import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shubham.srikanth.kishan.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrainFareFragment extends Fragment {


    public TrainFareFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_train_fare, container, false);
    }

}
