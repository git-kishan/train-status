package com.shubham.srikanth.kishan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.shubham.srikanth.kishan.Adapter.SearchAdapter;
import com.shubham.srikanth.kishan.Database.TrainCode;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TrainFareActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private SearchAdapter adapter;
    private RecyclerView trainFareRecyclerView;
    private LinearLayoutManager trainFareLinearLayoutManager;
    private BottomSheetBehavior bottomSheetTrainFare;
    private LinearLayout rootLinearLayout;
//    private MaterialButton  button;
    private Toolbar toolbar;
    private ImageView backImage;
    private EditText searchEditText;
    TrainCode dataList=new TrainCode();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_fare);
        initilization();
        attachListener();
        setBottomSheetCallbackForTrainFare();
        setTrainFareRecyclerView();


    }

    private void initilization(){
        toolbar=findViewById(R.id.toolbar);
        backImage=findViewById(R.id.back_button);
        searchEditText=findViewById(R.id.data_taker);
        rootLinearLayout=findViewById(R.id.search_view_root_layout);
        bottomSheetTrainFare=BottomSheetBehavior.from(rootLinearLayout);
        trainFareRecyclerView=findViewById(R.id.train_fare_recycler_view);
        trainFareLinearLayoutManager=new LinearLayoutManager(this);
    }

    private void openSearchView() {

        bottomSheetTrainFare.setPeekHeight(3000);
        bottomSheetTrainFare.setState(BottomSheetBehavior.STATE_EXPANDED);
        trainFareRecyclerView.setAdapter(adapter);
    }

    private void setTrainFareRecyclerView(){

         adapter=new SearchAdapter(this,dataList.returnList());
        trainFareRecyclerView.setLayoutManager(trainFareLinearLayoutManager);
        trainFareRecyclerView.setItemAnimator(new DefaultItemAnimator());
        trainFareRecyclerView.setHasFixedSize(true);
        trainFareRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }
    private void setBottomSheetCallbackForTrainFare(){
        bottomSheetTrainFare= BottomSheetBehavior.from(rootLinearLayout);
        bottomSheetTrainFare.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetTrainFare.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                if(newState == BottomSheetBehavior.STATE_HIDDEN){
                }

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

    }
    private void attachListener(){
        backImage.setOnClickListener(this);
        searchEditText.addTextChangedListener(this);

    }


    @Override
    public void onClick(View view) {


       if(view.getId()==R.id.back_button){
            bottomSheetTrainFare.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
    }

    @Override
    public void onBackPressed() {
        if(bottomSheetTrainFare.getState()==BottomSheetBehavior.STATE_EXPANDED){
            bottomSheetTrainFare.setState(BottomSheetBehavior.STATE_HIDDEN);
            return;
        }
        super.onBackPressed();
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

        ArrayList<DataModel> tempList=new ArrayList<>();
        ArrayList<DataModel> newList=new ArrayList<>();
        tempList=dataList.returnList();
        String entry=searchEditText.getText().toString().toUpperCase();
        ArrayList<DataModel> list = new ArrayList<>();
        for(int i=0;i<tempList.size();i++){
            if(tempList.get(i).getStationName().contains(entry)){
                newList.add(new DataModel(tempList.get(i).getStationName(),tempList.get(i).getStationCode()));
            }

        }
        adapter.updateList(newList);

    }
}
