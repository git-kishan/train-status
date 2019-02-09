package com.shubham.srikanth.kishan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView pnrStatusText,liveTrainText,trainFareText,seatAvailibilityText;
    private ConstraintLayout rootLayout;
    private Toolbar toolbar;
    private TextView toolbarTitle,selectDate;
    private TextView dateTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initilization();
        attachListener();



    }


    private void attachListener(){
        pnrStatusText.setOnClickListener(this);
        liveTrainText.setOnClickListener(this);
        trainFareText.setOnClickListener(this);
        seatAvailibilityText.setOnClickListener(this);

    }
    private void initilization(){
        pnrStatusText=findViewById(R.id.pnr_status);
        liveTrainText=findViewById(R.id.live_train);
        trainFareText=findViewById(R.id.train_fare);
        rootLayout=findViewById(R.id.root_layout);
        rootLayout.setVisibility(View.VISIBLE);
        toolbar=findViewById(R.id.toolbar);
        toolbarTitle=toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Train Status");
        seatAvailibilityText=findViewById(R.id.seat_avaibility);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pnr_status:

                startActivity(new Intent(this,PnrStatusActivity.class));
                break;
            case R.id.live_train:

                startActivity(new Intent(this,LiveTrainStatusActivity.class));
                break;
            case R.id.train_fare:
                Intent intent=new Intent(this,TrainFareActivity.class);
                startActivity(intent);
                break;
            case R.id.seat_avaibility:
                Toast.makeText(this,"seat availibility is clicked" ,Toast.LENGTH_SHORT ).show();
                break;


        }
    }
}
