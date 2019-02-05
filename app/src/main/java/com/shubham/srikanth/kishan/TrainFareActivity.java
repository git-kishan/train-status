package com.shubham.srikanth.kishan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ObjectAnimator;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.shubham.srikanth.kishan.Adapter.SearchAdapter;
import com.shubham.srikanth.kishan.Database.DatabaseHelper;
import com.shubham.srikanth.kishan.Database.TrainCode;
import com.shubham.srikanth.kishan.Interface.SearchItemClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TrainFareActivity extends AppCompatActivity implements View.OnClickListener,TextWatcher, SearchItemClickListener {

    private CoordinatorLayout coordinatorLayout;
    ArrayList<DataModel> newList;
    private SearchAdapter adapter;
    private RecyclerView trainFareRecyclerView;
    private LinearLayoutManager trainFareLinearLayoutManager;
    private BottomSheetBehavior bottomSheetTrainFare;
    private LinearLayout rootLinearLayout;
    private Toolbar toolbar;
    private ImageView backImage;
    private EditText searchEditText;
    TrainCode trainCode=new TrainCode();
    private ArrayList<DataModel> originalList;
    private TextView from,to,fromCode,toCode,sourceStation,destinationStation;
    private TextView date,monthName,dayName;
    private ImageView flipImage,calenderImage;
    private MaterialButton button;
    private ConstraintLayout datePicker;
    private static String top,bottom;
    private  String fromOnTop="true";
    private String sourceStationOnTop="true";
    private FrameLayout recentSearchFrameLayout;
    private RecyclerView recentSearchRecyclerView;
    private TextView clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_fare);
        initilization();
        attachListener();
        originalList=trainCode.returnList();
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
        from=findViewById(R.id.from);
        to=findViewById(R.id.to);
        fromCode=findViewById(R.id.from_code);
        toCode=findViewById(R.id.to_code);
        sourceStation=findViewById(R.id.source_station);
        destinationStation=findViewById(R.id.destination_station);
        flipImage=findViewById(R.id.flip);
        calenderImage=findViewById(R.id.calender);
        button=findViewById(R.id.button);
        datePicker=findViewById(R.id.date_picker);
        date=findViewById(R.id.date);
        monthName=findViewById(R.id.month);
        dayName=findViewById(R.id.day_name);
        coordinatorLayout=findViewById(R.id.coordinator);
        recentSearchFrameLayout=findViewById(R.id.recent_search_framelayout);
        recentSearchRecyclerView=findViewById(R.id.recent_search_recycler_view);
        clear=findViewById(R.id.clear);
    }

    private void openSearchView() {

        bottomSheetTrainFare.setPeekHeight(3000);
        bottomSheetTrainFare.setState(BottomSheetBehavior.STATE_EXPANDED);
        trainFareRecyclerView.setAdapter(adapter);
        toolbar.animate().translationY(-toolbar.getHeight()).start();
    }

    private void setTrainFareRecyclerView(){

        adapter=new SearchAdapter(this,originalList);
        trainFareRecyclerView.setLayoutManager(trainFareLinearLayoutManager);
        trainFareRecyclerView.setItemAnimator(new DefaultItemAnimator());
        trainFareRecyclerView.setHasFixedSize(true);
        trainFareRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        trainFareRecyclerView.setAdapter(adapter);
    }
    private void setBottomSheetCallbackForTrainFare(){
        bottomSheetTrainFare= BottomSheetBehavior.from(rootLinearLayout);
        bottomSheetTrainFare.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetTrainFare.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                if(newState == BottomSheetBehavior.STATE_HIDDEN){
                    toolbar.animate().translationY(0f).start();
                    searchEditText.setText("");

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
        sourceStation.setOnClickListener(this);
        destinationStation.setOnClickListener(this);
        datePicker.setOnClickListener(this);
        button.setOnClickListener(this);
        from.setOnClickListener(this);
        fromCode.setOnClickListener(this);
        to.setOnClickListener(this);
        toCode.setOnClickListener(this);
        flipImage.setOnClickListener(this);
        clear.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
       if(view.getId()==R.id.back_button){
            bottomSheetTrainFare.setState(BottomSheetBehavior.STATE_HIDDEN);
           toolbar.animate().translationY(0f).start();
        }
        else if(view.getId()==R.id.source_station){
           top="true";bottom="false";
           openSearchView();

        }else if(view.getId()==R.id.destination_station){
           bottom="true";top="false";
           openSearchView();
       }
       else if(view.getId()==R.id.date_picker){
           showDatePicker();
       }
       else if(view.getId()==R.id.button){
           onButtonClicked();

       }
       else if(view.getId()==R.id.from|| view.getId()==R.id.from_code){
           top="true";bottom="false";
           openSearchView();
       }
       else if(view.getId()==R.id.to||view.getId()==R.id.to_code){
           bottom="true";top="false";
           openSearchView();
       }else if(view.getId()==R.id.flip){
           flipToAndFrom();
       }
       else if(view.getId()==R.id.clear){

       }


    }

    @Override
    public void onBackPressed() {
        if(bottomSheetTrainFare.getState()==BottomSheetBehavior.STATE_EXPANDED){
            bottomSheetTrainFare.setState(BottomSheetBehavior.STATE_HIDDEN);
            toolbar.animate().translationY(0f).start();
            return;
        }if(bottomSheetTrainFare.getState()!=BottomSheetBehavior.STATE_HIDDEN){
            bottomSheetTrainFare.setState(BottomSheetBehavior.STATE_HIDDEN);
            toolbar.animate().translationY(0f).start();
        }
        super.onBackPressed();
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        recentSearchFrameLayout.setVisibility(View.GONE);
        recentSearchRecyclerView.setVisibility(View.GONE);
        trainFareRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void afterTextChanged(Editable editable) {

        ArrayList<DataModel> tempList;
        newList=new ArrayList<>();
        tempList=originalList;
        String entry=searchEditText.getText().toString().toUpperCase().trim();
        ArrayList<DataModel> list = new ArrayList<>();
        for(int i=0;i<tempList.size();i++){
            if(tempList.get(i).getStationName().contains(entry)){
                newList.add(new DataModel(tempList.get(i).getStationName(),tempList.get(i).getStationCode()));
            }

        }
        adapter.updateList(newList);

    }

    private void showToast(String message){
        Toast.makeText(this,message ,Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void onSearchItemClicked(int position) {

      if(clickedByTopOrBottom().equals("top")){
          bottomSheetTrainFare.setState(BottomSheetBehavior.STATE_HIDDEN);
          from.setVisibility(View.VISIBLE);
          fromCode.setVisibility(View.VISIBLE);
          sourceStation.setVisibility(View.INVISIBLE);
          if(isNewList()) {
              from.setText(newList.get(position).getStationName());
              fromCode.setText(newList.get(position).getStationCode());
          }
          else {
              from.setText(originalList.get(position).getStationName());
              fromCode.setText(originalList.get(position).getStationCode());
          }

      }else if(clickedByTopOrBottom().equals("bottom")){
          bottomSheetTrainFare.setState(BottomSheetBehavior.STATE_HIDDEN);
          to.setVisibility(View.VISIBLE);
          toCode.setVisibility(View.VISIBLE);
          destinationStation.setVisibility(View.INVISIBLE);
          if(isNewList()) {
              to.setText(newList.get(position).getStationName());
              toCode.setText(newList.get(position).getStationCode());
          }
          else {
              to.setText(originalList.get(position).getStationName());
              toCode.setText(originalList.get(position).getStationCode());
          }

      }
      else if(clickedByTopOrBottom().equals("null")){
          Toast.makeText(this, "You haven't selected any station", Toast.LENGTH_SHORT).show();
      }


    }
    private boolean isNewList(){
        if(searchEditText.getText().toString().trim().length()==0)
        return false;
        else return true;
    }

    private String clickedByTopOrBottom(){
        if(top.equals("true"))
            return "top";
       else if(bottom.equals("true"))
            return "bottom";
       else
        return "null";
    }
    private void showDatePicker(){
       final int pyear,month,day;
            final Calendar calendar = Calendar.getInstance();
            pyear = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            if(validateYear(pyear, year)) {
                                SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
                                Date mdate = new Date(year, monthOfYear, dayOfMonth-1);
                                String dayOfWeek = simpledateformat.format(mdate);

                                dayName.setText(dayOfWeek);
                                monthName.setText(returnMonthName(monthOfYear));
                                date.setText(dayOfMonth + "");
                            }else {
                                Snackbar.make(coordinatorLayout, "please select from current year", Snackbar.LENGTH_INDEFINITE).setAction(
                                        "date", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                showDatePicker();
                                            }
                                        }).show();
                            }
                        }
                    }, pyear, month, day);
            datePickerDialog.show();

        }

        private String returnMonthName(int position){

        String [] monthName=new String[]{
                "January","February","March","April","May","June","July","August",
                "September","October","November","December"
        };
        return monthName[position];

    }

    private boolean validateYear(int presentYear,int selectedYear){

        return true;
    }
    private void flipToAndFrom(){

        RotateAnimation rotate = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        rotate.setDuration(400);
        rotate.setRepeatCount(Animation.ABSOLUTE);
        flipImage.startAnimation(rotate);
//
//        if(from.getText().equals("null")&&fromCode.getText().equals("null")&&to.getText().equals("null")&&toCode.getText().equals("null")){
//            if(sourceStationOnTop.equals("true")) {
//                sourceStation.animate().translationY(180f).start();
//                destinationStation.animate().translationY(-180f).start();
//                sourceStationOnTop = "false";
//                return;
//            }else if(sourceStationOnTop.equals("false")){
//                sourceStation.animate().translationY(0f).start();
//                destinationStation.animate().translationY(0f).start();
//                sourceStationOnTop = "true";
//                return;
//            }
//        }
        if((from.getText().equals("null")&&fromCode.getText().equals("null"))||(to.getText().equals("null")&&toCode.getText().equals("null"))){
            return;
        }
            if(fromOnTop.equals("true")) {
            fromCode.animate().translationY(180f).start();
            toCode.animate().translationY(-180f).start();
            from.animate().translationY(180f).start();
            to.animate().translationY(-180f).start();
            fromOnTop = "false";
        }else if(fromOnTop.equals("false"))  {
            fromCode.animate().translationY(0f).start();
            toCode.animate().translationY(0f).start();
            from.animate().translationY(0f).start();
            to.animate().translationY(0f).start();
            fromOnTop = "true";
        }
    }

    private void onButtonClicked(){
       if(checkValidation())
           putSearchInDatabase();
    }
    private boolean checkValidation(){
        if(from.getText().toString().equals("null")||fromCode.getText().toString().equals("null")
        ||to.getText().toString().equals("null")||toCode.getText().toString().equals("null")){
            return false;
        }
        return true;
    }
    private void putSearchInDatabase(){
        DatabaseHelper helper=new DatabaseHelper(this);
        helper.addDataToRecentSearch(new DataModel(
                fromCode.getText().toString(),
                from.getText().toString(),
                toCode.getText().toString(),
                to.getText().toString()
        ));

        showToast("data saved in database");
        ArrayList<DataModel> m=helper.returnDataFromRecentSearches();
        for(int i=0;i<m.size();i++){
            Log.i("TAG","station code :- "+m.get(i).getFromStationCode());
            Log.i("TAG","station name :- "+m.get(i).getFromStationName());
            Log.i("TAG","station name :- "+m.get(i).getToStationName());
            Log.i("TAG","station code :- "+m.get(i).getToStationCode());

        }

    }
    private void deleteRecentSearch(){
        DatabaseHelper helper = new DatabaseHelper(this);
        helper.deleteRecentSearches();
    }



}

