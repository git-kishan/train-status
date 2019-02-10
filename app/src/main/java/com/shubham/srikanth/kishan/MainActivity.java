package com.shubham.srikanth.kishan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.shubham.srikanth.kishan.Listener.ScreenSize;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView pnrStatusText,liveTrainText,trainFareText,seatAvailibilityText;
    private Toolbar toolbar;
    private TextView selectDate;
    private TextView dateTextView;
    private ScreenSize screenSize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenSize=new ScreenSize();
        double size= screenSize.getScreenSize(getWindowManager());
        if(size>6.5 && size<7.0){
            setContentView(R.layout.activity_main_7);
        }else if (size>4.8 && size<6.5){
            setContentView(R.layout.activity_main);
        }

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
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Train");
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
