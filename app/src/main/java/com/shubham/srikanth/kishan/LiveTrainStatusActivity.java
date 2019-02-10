package com.shubham.srikanth.kishan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.shubham.srikanth.kishan.Interface.EditTextFocusChangeListener;
import com.shubham.srikanth.kishan.Listener.CustomEditText;

import java.util.Calendar;

public class LiveTrainStatusActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private TextInputLayout textInputLayout;
    private CustomEditText trainNoEditText;
    private TextView selectDate;
    private ConstraintLayout threeDaysAgoConstraint,twoDaysAgoConstraint,yesterdayConstraint,todayConstraint,tommorowConstraint;
    private TextView threeDaysAgoText,threeDayAgoMonthText,threeDaysAgoDateText;
    private TextView twoDaysAgoText,twoDaysAgoMonthText,twoDaysAgoDateText;
    private TextView yesterdayText,yesterdayMonthText,yesterdayDateText;
    private TextView todayText,todayMonthText,todayDateText;
    private TextView tommorowText,tommorowMonthText,tommorowDateText;
    private MaterialButton searchLiveButton;
    private ConstraintLayout rootLayout;
    private HorizontalScrollView horizontalScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_train_status);
        initialization();
        attachListener();
        setDateButtonvalue();
    }
    private void initialization(){
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textInputLayout=findViewById(R.id.train_no_input_layout);
        trainNoEditText=findViewById(R.id.train_no_edit_text);
        selectDate=findViewById(R.id.select_date_text);
        //constraint initilization
        threeDaysAgoConstraint=findViewById(R.id.three_day_ago);
        twoDaysAgoConstraint=findViewById(R.id.two_day_ago);
        yesterdayConstraint=findViewById(R.id.yesterday);
        todayConstraint=findViewById(R.id.today);
        tommorowConstraint = findViewById(R.id.tommorow);
        //button initilization
        threeDaysAgoText=findViewById(R.id.threedays_ago_text);
        threeDaysAgoDateText=findViewById(R.id.threedays_ago_date_text);
        threeDayAgoMonthText=findViewById(R.id.threedays_ago_month_text);
        twoDaysAgoText=findViewById(R.id.twodays_ago__text);
        twoDaysAgoDateText=findViewById(R.id.twodays_ago_date);
        twoDaysAgoMonthText=findViewById(R.id.twodays_ago_month_text);
        yesterdayText=findViewById(R.id.yesterday_text);
        yesterdayDateText=findViewById(R.id.yesterday_date_text);
        yesterdayMonthText=findViewById(R.id.yesterday_month_text);
        todayText=findViewById(R.id.today_day_text);
        todayDateText=findViewById(R.id.today_date_text);
        todayMonthText=findViewById(R.id.today_month_text);
        tommorowText=findViewById(R.id.tommorow_text);
        tommorowDateText=findViewById(R.id.tommorow_date_text);
        tommorowMonthText=findViewById(R.id.tommorow_month_text);
        searchLiveButton=findViewById(R.id.train_button);
        rootLayout=findViewById(R.id.root_layout);
        horizontalScrollView=findViewById(R.id.nestedScrollView);


    }

    private void attachListener(){
        threeDaysAgoConstraint.setOnClickListener(this);
        twoDaysAgoConstraint.setOnClickListener(this);
        yesterdayConstraint.setOnClickListener(this);
        todayConstraint.setOnClickListener(this);
        tommorowConstraint.setOnClickListener(this);
        rootLayout.setOnClickListener(this);
        EditTextFocusChangeListener  focusChangeListener=new EditTextFocusChangeListener(this);
        trainNoEditText.setOnFocusChangeListener(focusChangeListener);
        searchLiveButton.setOnClickListener(this);
        horizontalScrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                horizontalScrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
            }
        }, 100L);

    }

    @Override
    public void onClick(View view) {
     switch (view.getId()){
         case R.id.root_layout:
             selectDate.requestFocus();
             break;
         case R.id.three_day_ago:
             setDateButtonBackground(threeDaysAgoConstraint,threeDaysAgoText,threeDaysAgoDateText,threeDayAgoMonthText);
             break;
         case R.id.two_day_ago:
             setDateButtonBackground(twoDaysAgoConstraint,twoDaysAgoText,twoDaysAgoDateText,twoDaysAgoMonthText);
             break;
         case R.id.yesterday:
             setDateButtonBackground(yesterdayConstraint,yesterdayText,yesterdayDateText,yesterdayMonthText);
             break;
         case R.id.today:
             setDateButtonBackground(todayConstraint,todayText,todayDateText,todayMonthText);
             break;
         case R.id.tommorow:
             setDateButtonBackground(tommorowConstraint,tommorowText,tommorowDateText,tommorowMonthText);
             break;
         case R.id.train_button:
             selectDate.requestFocus();

     }

    }
    private void setDateButtonBackground(ConstraintLayout constraintLayout,TextView day,TextView date,TextView month){
        selectDate.requestFocus();
        //set default background to textView
        threeDaysAgoConstraint.setBackground(getResources().getDrawable(R.drawable.edit_corner ));
        twoDaysAgoConstraint.setBackground(getResources().getDrawable(R.drawable.edit_corner ));
        yesterdayConstraint.setBackground(getResources().getDrawable(R.drawable.edit_corner ));
        todayConstraint.setBackground(getResources().getDrawable(R.drawable.edit_corner ));
        tommorowConstraint.setBackground(getResources().getDrawable(R.drawable.edit_corner ));
        //set default text color
        threeDaysAgoText.setTextColor(getResources().getColor(android.R.color.tab_indicator_text ));
        threeDaysAgoDateText.setTextColor(getResources().getColor(android.R.color.tab_indicator_text ));
        threeDayAgoMonthText.setTextColor(getResources().getColor(android.R.color.tab_indicator_text ));
        twoDaysAgoText.setTextColor(getResources().getColor(android.R.color.tab_indicator_text ));
        twoDaysAgoDateText.setTextColor(getResources().getColor(android.R.color.tab_indicator_text ));
        twoDaysAgoMonthText.setTextColor(getResources().getColor(android.R.color.tab_indicator_text ));
        yesterdayText.setTextColor(getResources().getColor(android.R.color.tab_indicator_text ));
        yesterdayDateText.setTextColor(getResources().getColor(android.R.color.tab_indicator_text ));
        yesterdayMonthText.setTextColor(getResources().getColor(android.R.color.tab_indicator_text ));
        todayText.setTextColor(getResources().getColor(android.R.color.tab_indicator_text ));
        todayDateText.setTextColor(getResources().getColor(android.R.color.tab_indicator_text ));
        todayMonthText.setTextColor(getResources().getColor(android.R.color.tab_indicator_text ));
        tommorowText.setTextColor(getResources().getColor(android.R.color.tab_indicator_text ));
        tommorowDateText.setTextColor(getResources().getColor(android.R.color.tab_indicator_text ));
        tommorowMonthText.setTextColor(getResources().getColor(android.R.color.tab_indicator_text ));
        //set selected value to view
        constraintLayout.setBackground(getResources().getDrawable(R.drawable.edit_no_dash ));
        day.setTextColor(getResources().getColor(android.R.color.black));
        date.setTextColor(getResources().getColor(android.R.color.black));
        month.setTextColor(getResources().getColor(android.R.color.black));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    private void setDateButtonvalue(){
        Calendar calendar=Calendar.getInstance();
        int date=calendar.get(Calendar.DATE);
        int month=calendar.get(Calendar.MONTH);
        todayDateText.setText(String.valueOf(date));
        todayMonthText.setText(returnMonthName(month));
        calendar.add(Calendar.DATE,1 );
        date=calendar.get(Calendar.DATE);
        month=calendar.get(Calendar.MONTH);
        tommorowDateText.setText(String.valueOf(date));
        tommorowMonthText.setText(returnMonthName(month));
        calendar.add(Calendar.DATE,-2 );
        date=calendar.get(Calendar.DATE);
        month=calendar.get(Calendar.MONTH);
        yesterdayDateText.setText(String.valueOf(date));
        yesterdayMonthText.setText(returnMonthName(month));
        calendar.add(Calendar.DATE,-1 );
        date=calendar.get(Calendar.DATE);
        month=calendar.get(Calendar.MONTH);
        twoDaysAgoDateText.setText(String.valueOf(date));
        twoDaysAgoMonthText.setText(returnMonthName(month));
        calendar.add(Calendar.DATE, -1);
        date=calendar.get(Calendar.DATE);
        month=calendar.get(Calendar.MONTH);
        threeDaysAgoDateText.setText(String.valueOf(date));
        threeDayAgoMonthText.setText(returnMonthName(month));



    }
    private String returnMonthName(int position){

        String [] monthName=new String[]{
                "Jan","Feb","Mar","Apr","May","Jun","Jul","Aug",
                "Sep","Oct","Nov","Dec"
        };
        return monthName[position];

    }

}
