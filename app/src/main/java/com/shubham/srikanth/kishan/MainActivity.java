package com.shubham.srikanth.kishan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.shubham.srikanth.kishan.FragmentPackage.PnrStatusBottomFragment;
import com.shubham.srikanth.kishan.Interface.EditTextFocusChangeListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText trainNoEditText;
    private TextInputLayout textInputLayout;
    private FrameLayout fragmentContainer;
    private TextView pnrStatusText,liveTrainText,trainFareText,seatAvailibilityText;
    private ConstraintLayout rootLayout;
    private CardView bottomSheetTitle;
    private Toolbar toolbar;
    private TextView toolbarTitle,selectDate;
    private TextView dateTextView;
    private NestedScrollView nestedScrollView;
    private BottomSheetBehavior  bottomSheet;
    private MaterialButton todayButton,tomorrowButton,twoDayAgoButton,threeDayAgoButton,liveStatusButton;
    private RecyclerView liveTrainRecyclerView;
    private LinearLayoutManager liveTrainLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initilization();
        attachListener();
        setBottomSheetCallbackForLiveTrain();



    }


    private void attachListener(){
        pnrStatusText.setOnClickListener(this);
        liveTrainText.setOnClickListener(this);
        trainFareText.setOnClickListener(this);
        seatAvailibilityText.setOnClickListener(this);
        todayButton.setOnClickListener(this);
        tomorrowButton.setOnClickListener(this);
        twoDayAgoButton.setOnClickListener(this);
        threeDayAgoButton.setOnClickListener(this);
        liveStatusButton.setOnClickListener(this);
        EditTextFocusChangeListener focusChangeListener=new EditTextFocusChangeListener(this);
        trainNoEditText.setOnFocusChangeListener(focusChangeListener);
    }
    private void initilization(){
        pnrStatusText=findViewById(R.id.pnr_status);
        liveTrainText=findViewById(R.id.live_train);
        trainFareText=findViewById(R.id.train_fare);
        seatAvailibilityText=findViewById(R.id.seat_avaibility);
        fragmentContainer=findViewById(R.id.frame_layout);
        rootLayout=findViewById(R.id.root_layout);
        rootLayout.setVisibility(View.VISIBLE);
        toolbar=findViewById(R.id.toolbar);
        toolbarTitle=toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Train Status");
        nestedScrollView=findViewById(R.id.bottom_sheet);
        todayButton=findViewById(R.id.today_button);
        tomorrowButton=findViewById(R.id.tomorrow_button);
        twoDayAgoButton=findViewById(R.id.twoday_ago_button);
        threeDayAgoButton=findViewById(R.id.threeday_ago_button);
        dateTextView=findViewById(R.id.date_text);
        bottomSheetTitle=findViewById(R.id.bottom_sheet_topview);
        liveStatusButton=findViewById(R.id.train_button);
        trainNoEditText=findViewById(R.id.train_no_edit_text);
        textInputLayout=findViewById(R.id.train_no_layout);
        selectDate=findViewById(R.id.textView);
        EditTextFocusChangeListener focusChangeListener=new EditTextFocusChangeListener(this);
        trainNoEditText.setOnFocusChangeListener(focusChangeListener);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pnr_status:
                PnrStatusBottomFragment pnrStatusBottomFragment=new PnrStatusBottomFragment();
                pnrStatusBottomFragment.show(getSupportFragmentManager(),pnrStatusBottomFragment.getTag());
                break;
            case R.id.live_train:
               bottomSheet.setPeekHeight(950);
               bottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
               rootLayout.setAlpha(0.5f);
               rootLayout.setClickable(false);
                break;
            case R.id.train_fare:
                Intent intent=new Intent(this,TrainFareActivity.class);
                startActivity(intent);

                break;
            case R.id.seat_avaibility:
                Toast.makeText(this,"seat availibility is clicked" ,Toast.LENGTH_SHORT ).show();
            case R.id.today_button:
                showSnackbar("Today ");
                break;
            case R.id.tomorrow_button:
                showSnackbar("tommorow ");
                break;
            case R.id.twoday_ago_button:
                showSnackbar("two day ago ");
                break;
            case R.id.threeday_ago_button:
                showSnackbar("three day ago ");
                break;
            case R.id.train_button:
                showSnackbar("Live Train status ");
                break;


        }
    }

    @Override
    public void onBackPressed() {
        syncToolbarTitle();
        rootLayout.setVisibility(View.VISIBLE);

        if(bottomSheet.getState()==BottomSheetBehavior.STATE_EXPANDED||
        bottomSheet.getState()==BottomSheetBehavior.STATE_SETTLING||
        bottomSheet.getState()==BottomSheetBehavior.STATE_COLLAPSED){

            bottomSheet.setState(BottomSheetBehavior.STATE_HIDDEN);
            rootLayout.setAlpha(1f);
            rootLayout.setClickable(true);
            return;
        }
        super.onBackPressed();
    }

    private void syncToolbarTitle(){
        if(getSupportFragmentManager().findFragmentById(R.id.frame_layout)!=null){
           flipToolbarTitle("Train Status");
        }
    }

    private void flipToolbarTitle(final String title){
        ObjectAnimator flip = ObjectAnimator.ofFloat(toolbarTitle, "rotationX", 0f, 360f);
        flip.setDuration(700);
        flip.start();
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toolbarTitle.setText(title);

            }
        }, 300);
    }


    private void setBottomSheetCallbackForLiveTrain(){
        bottomSheet= BottomSheetBehavior.from(nestedScrollView);
        bottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheet.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(newState!= BottomSheetBehavior.STATE_HIDDEN){
                    liveTrainText.setEnabled(false);
                    seatAvailibilityText.setEnabled(false);
                    trainFareText.setEnabled(false);
                    pnrStatusText.setEnabled(false);
                }
                if(newState==BottomSheetBehavior.STATE_HIDDEN){
                    liveTrainText.setEnabled(true);
                    seatAvailibilityText.setEnabled(true);
                    trainFareText.setEnabled(true);
                    pnrStatusText.setEnabled(true);
                    selectDate.requestFocus();
                }
                if(newState==BottomSheetBehavior.STATE_EXPANDED){
                    flipToolbarTitle("Live Train Status");
                }
                if(newState==BottomSheetBehavior.STATE_HALF_EXPANDED||
                newState==BottomSheetBehavior.STATE_SETTLING){
                    flipToolbarTitle("Train Status");
                }
                if(newState==BottomSheetBehavior.STATE_HIDDEN){
                    rootLayout.setAlpha(1f);
                    selectDate.requestFocus();
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    private void showSnackbar(String message){
        Snackbar.make(rootLayout,message+" is clicked" ,Snackbar.LENGTH_SHORT ).setAction("undo", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();
    }


}
