package com.shubham.srikanth.kishan;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.shubham.srikanth.kishan.FragmentPackage.LiveTrainFragment;
import com.shubham.srikanth.kishan.FragmentPackage.PnrStatusFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout fragmentContainer;
    private TextView pnrStatusText,liveTrainText,trainFareText,seatAvailibilityText;
    private ConstraintLayout rootLayout;
    private CardView toolbarCardView;
    private TextView toolbarTitle;
    private NestedScrollView nestedScrollView;
    private BottomSheetBehavior  bottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initilization();
        attachListener();
        bottomSheet= BottomSheetBehavior.from(nestedScrollView);
        bottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);

    }

    private void loadFragment(Fragment fragment,String title){
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.slide_up,
                        R.animator.slide_down,
                        R.animator.slide_up,R.animator.slide_down)
                .add(R.id.frame_layout, fragment).addToBackStack(null).commit();
        toolbarTitle.setText(title);
        syncToolbarTitle();
        flipToolbarTitle(title);

    }
    private void attachListener(){
        pnrStatusText.setOnClickListener(this);
        liveTrainText.setOnClickListener(this);
        trainFareText.setOnClickListener(this);
        seatAvailibilityText.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Fragment fragment;
        switch (view.getId()){
            case R.id.pnr_status:
                fragment=new PnrStatusFragment();
                loadFragment(fragment,"PNR Status" );
                break;
            case R.id.live_train:
               bottomSheet.setPeekHeight(700);
               bottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
        }
    }
    private void initilization(){
        pnrStatusText=findViewById(R.id.pnr_status);
        liveTrainText=findViewById(R.id.live_train);
        trainFareText=findViewById(R.id.train_fare);
        seatAvailibilityText=findViewById(R.id.seat_avaibility);
        fragmentContainer=findViewById(R.id.frame_layout);
        rootLayout=findViewById(R.id.root_layout);
        rootLayout.setVisibility(View.VISIBLE);
        toolbarCardView=findViewById(R.id.toolbar_cardview);
        toolbarTitle=toolbarCardView.findViewById(R.id.title);
        toolbarTitle.setText("Train Status");
        nestedScrollView=findViewById(R.id.bottom_sheet);
    }

    @Override
    public void onBackPressed() {
        syncToolbarTitle();
        rootLayout.setVisibility(View.VISIBLE);
        super.onBackPressed();
    }

    private void syncToolbarTitle(){
        if(getSupportFragmentManager().findFragmentById(R.id.frame_layout)!=null){
           flipToolbarTitle("Train Status");
        }
    }

    private void flipToolbarTitle(String title){
        ObjectAnimator flip = ObjectAnimator.ofFloat(toolbarTitle, "rotationY", 0f, 360f);
        flip.setDuration(700);
        flip.start();
        toolbarTitle.setText(title);
    }
}
