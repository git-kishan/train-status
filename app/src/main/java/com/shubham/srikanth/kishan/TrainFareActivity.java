package com.shubham.srikanth.kishan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.RotateAnimation;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.shubham.srikanth.kishan.Adapter.RecentSearchAdapter;
import com.shubham.srikanth.kishan.Adapter.SearchAdapter;
import com.shubham.srikanth.kishan.Database.DatabaseHelper;
import com.shubham.srikanth.kishan.Database.TrainCode;
import com.shubham.srikanth.kishan.Interface.EditTextFocusChangeListener;
import com.shubham.srikanth.kishan.Interface.SearchItemClickListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TrainFareActivity extends AppCompatActivity implements View.OnClickListener,TextWatcher, SearchItemClickListener {

    private CoordinatorLayout coordinatorLayout;
    private ArrayList<DataModel> newList,originalList;
    private SearchAdapter adapter;
    private RecyclerView trainFareRecyclerView;
    private LinearLayoutManager trainFareLinearLayoutManager,recentSearchLinearLayoutManager;
    private BottomSheetBehavior bottomSheetTrainFare;
    private LinearLayout rootLinearLayout;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private ImageView backImage;
    private EditText searchEditText;
    TrainCode trainCode=new TrainCode();
    private TextView from,to,fromCode,toCode,sourceStation,destinationStation;
    private TextView date,monthName,dayName;
    private ImageView flipImage,calenderImage;
    private MaterialButton button;
    private ConstraintLayout datePicker;
    private String top,bottom;//top and bottom specify which is clicked source station or destination station
    private String fromOnTop="true";// fromOnTop is used to show wheather from is on top side or on botoom side
    private FrameLayout recentSearchFrameLayout;
    private RecyclerView recentSearchRecyclerView;
    private TextView clear;
    private RecentSearchAdapter recentSearchAdapter;
    private ArrayList<DataModel> recentSearchList;
    private TextView recentSearchText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_fare);
        initilization();
        attachListener();
        originalList=trainCode.returnList();
        setBottomSheetCallbackForTrainFare();
        setTrainFareRecyclerView();
        setRecentSearchRecyclerView();


    }

    private void initilization(){
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        flipImage.setVisibility(View.GONE);
        calenderImage=findViewById(R.id.calender);
        button=findViewById(R.id.button);
        datePicker=findViewById(R.id.date_picker);
        date=findViewById(R.id.date);
        monthName=findViewById(R.id.month);
        dayName=findViewById(R.id.day_name);
        coordinatorLayout=findViewById(R.id.coordinator);
        recentSearchFrameLayout=findViewById(R.id.recent_search_framelayout);
        recentSearchRecyclerView=findViewById(R.id.recent_search_recycler_view);
        recentSearchLinearLayoutManager=new LinearLayoutManager(this);
        clear=findViewById(R.id.clear);
        appBarLayout=findViewById(R.id.app_bar_layout);
        recentSearchText=findViewById(R.id.recent_search_text);
        recentSearchText.requestFocus();
    }

    private void openSearchView() {
        bottomSheetTrainFare.setPeekHeight(3000);
        bottomSheetTrainFare.setState(BottomSheetBehavior.STATE_EXPANDED);
        toolbar.animate().translationY(-toolbar.getHeight()).start();
        appBarLayout.animate().translationY(-toolbar.getHeight()).start();
        recentSearchFrameLayout.setVisibility(View.VISIBLE);
        trainFareRecyclerView.setVisibility(View.GONE);
    }

    private void setTrainFareRecyclerView(){

        adapter=new SearchAdapter(this,originalList);
        trainFareRecyclerView.setLayoutManager(trainFareLinearLayoutManager);
        trainFareRecyclerView.setHasFixedSize(true);
        trainFareRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        trainFareRecyclerView.setAdapter(adapter);




    }
    private void setRecentSearchRecyclerView(){
        recentSearchRecyclerView.setLayoutManager(recentSearchLinearLayoutManager);
        recentSearchRecyclerView.setHasFixedSize(true);
        DatabaseHelper helper=new DatabaseHelper(this);
        recentSearchList=helper.returnDataFromRecentSearches();
        recentSearchFrameLayout.setVisibility(View.VISIBLE);
        trainFareRecyclerView.setVisibility(View.GONE);
        recentSearchAdapter=new RecentSearchAdapter(this,recentSearchList);
        recentSearchRecyclerView.setAdapter(recentSearchAdapter);

    }
    private void setBottomSheetCallbackForTrainFare(){
        bottomSheetTrainFare= BottomSheetBehavior.from(rootLinearLayout);
        bottomSheetTrainFare.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetTrainFare.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                if(newState == BottomSheetBehavior.STATE_HIDDEN){
                    toolbar.animate().translationY(0f).start();
                    appBarLayout.animate().translationY(0f).start();


                } if(newState != BottomSheetBehavior.STATE_HIDDEN){
                    recentSearchText.requestFocus();
                    setRecentSearchRecyclerView();

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
        EditTextFocusChangeListener focusChangeListener=new EditTextFocusChangeListener(this);
        searchEditText.setOnFocusChangeListener(focusChangeListener);

    }


    @Override
    public void onClick(View view) {
       if(view.getId()==R.id.back_button){
            bottomSheetTrainFare.setState(BottomSheetBehavior.STATE_HIDDEN);
           toolbar.animate().translationY(0f).start();
           appBarLayout.animate().translationY(0f).start();
        }

       else if(view.getId()==R.id.date_picker){
           showDatePicker();
       }
       else if(view.getId()==R.id.button){
           onButtonClicked();
           datePicker.setBackground(getResources().getDrawable(R.drawable.edit_no_dash));

       }else if(view.getId()==R.id.flip){
           flipToAndFrom();
       }
       else if(view.getId()==R.id.clear){

           deleteRecentSearch();
       }
       else if(view.getId()==R.id.source_station){
           top="true";bottom="false";
           openSearchView();

       }else if(view.getId()==R.id.destination_station){
           bottom="true";top="false";
           openSearchView();
       }
       else if(view.getId()==R.id.from|| view.getId()==R.id.from_code){
           top="true";bottom="false";
           openSearchView();
       }
       else if(view.getId()==R.id.to||view.getId()==R.id.to_code){
           bottom="true";top="false";
           openSearchView();
       }

    }

    @Override
    public void onBackPressed() {
        if(bottomSheetTrainFare.getState()==BottomSheetBehavior.STATE_EXPANDED){
            bottomSheetTrainFare.setState(BottomSheetBehavior.STATE_HIDDEN);
            toolbar.animate().translationY(0f).start();
            appBarLayout.animate().translationY(0f).start();
            return;
        }if(bottomSheetTrainFare.getState()!=BottomSheetBehavior.STATE_HIDDEN){
            bottomSheetTrainFare.setState(BottomSheetBehavior.STATE_HIDDEN);
            toolbar.animate().translationY(0f).start();
            appBarLayout.animate().translationY(0f).start();
        }
        super.onBackPressed();
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        recentSearchFrameLayout.setVisibility(View.GONE);
        trainFareRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void afterTextChanged(Editable editable) {

        newList=new ArrayList<>();
        String entry=searchEditText.getText().toString().toUpperCase().trim();
        for(int i=0;i<originalList.size();i++){
            if(originalList.get(i).getStationName().contains(entry)){
                newList.add(new DataModel(originalList.get(i).getStationName(),originalList.get(i).getStationCode()));
            }

        }
        if(newList.size()==0)return;
        adapter.updateList(newList);
        adapter.notifyDataSetChanged();
    }
    //click listener of search item clicked on list
    @Override
    public void onSearchItemClicked(int position) {

       try{
           if(clickedByTopOrBottom().equals("top")){
               bottomSheetTrainFare.setState(BottomSheetBehavior.STATE_HIDDEN);
               from.setVisibility(View.VISIBLE);
               fromCode.setVisibility(View.VISIBLE);
               sourceStation.setVisibility(View.INVISIBLE);

               if(isNewList()) {
                   from.setText(newList.get(position).getStationName());
                   fromCode.setText(newList.get(position).getStationCode());
                   searchEditText.setText("");
               }else {

                   from.setText(originalList.get(position).getStationName());
                   fromCode.setText(originalList.get(position).getStationCode());
                   searchEditText.setText("");
               }
               if(fromCode.getText().equals(toCode.getText())&&from.getText().equals(to.getText())){
                   Snackbar.make(coordinatorLayout,"source and destination station can't be same" ,Snackbar.LENGTH_SHORT ).show();
               }
           }else if(clickedByTopOrBottom().equals("bottom")){

               bottomSheetTrainFare.setState(BottomSheetBehavior.STATE_HIDDEN);
               to.setVisibility(View.VISIBLE);
               toCode.setVisibility(View.VISIBLE);
               destinationStation.setVisibility(View.INVISIBLE);

               if(isNewList()) {

                   to.setText(newList.get(position).getStationName());
                   toCode.setText(newList.get(position).getStationCode());
                   searchEditText.setText("");
               }else {
                   to.setText(originalList.get(position).getStationName());
                   toCode.setText(originalList.get(position).getStationCode());
                   searchEditText.setText("");
               }

               if(fromCode.getText().equals(toCode.getText())&&from.getText().equals(to.getText())){
                   Snackbar.make(coordinatorLayout,"source and destination station can't be same" ,Snackbar.LENGTH_INDEFINITE ).show();
               }

           }
           else if(clickedByTopOrBottom().equals("null")){
               Toast.makeText(this, "You haven't selected any station", Toast.LENGTH_SHORT).show();
           }

       }catch (IndexOutOfBoundsException e){
           Log.i("TAG", "outOfBoundException :- "+e.getMessage());
       }

        if(!fromCode.getText().equals("null")&&!toCode.getText().equals("null")){
            flipImage.setVisibility(View.VISIBLE);
        }


    }


    private boolean isNewList(){
        if(searchEditText.getText().toString().trim().length()==0) return false;
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
                                dayName.setTextColor(getResources().getColor( android.R.color.black));
                                monthName.setTextColor(getResources().getColor(android.R.color.black ));
                                date.setTextColor(getResources().getColor(android.R.color.black ));
                                datePicker.setBackground(getResources().getDrawable(R.drawable.edit_no_dash));
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
        if(fromOnTop.equals("true")) {
            helper.addDataToRecentSearch(new DataModel(
                    fromCode.getText().toString(),
                    from.getText().toString(),
                    toCode.getText().toString(),
                    to.getText().toString()
            ));
        }else if(fromOnTop.equals("false")){
            helper.addDataToRecentSearch(new DataModel(
                    toCode.getText().toString(),
                    to.getText().toString(),
                    fromCode.getText().toString(),
                    from.getText().toString()
            ));
        }


    }
    private void deleteRecentSearch(){

        DatabaseHelper helper = new DatabaseHelper(this);
        helper.deleteRecentSearches();
        recentSearchList.clear();
        recentSearchAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void showToast(String message){
        Toast.makeText(this,message ,Toast.LENGTH_SHORT ).show();
    }

}


