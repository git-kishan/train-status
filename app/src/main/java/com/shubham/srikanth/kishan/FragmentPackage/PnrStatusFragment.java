package com.shubham.srikanth.kishan.FragmentPackage;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.shubham.srikanth.kishan.R;

import java.util.ArrayList;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class PnrStatusFragment extends Fragment implements View.OnClickListener {

    private View rootLayoutOfActivity,constraintLayoutOfActivity;
    private TextInputLayout textInputLayout;
    private TextInputEditText pnrEditText;
    private MaterialButton pnrButton;
    private ImageView googleMic;

    public PnrStatusFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_pnr_status, container, false);
        rootLayoutOfActivity=getActivity().findViewById(R.id.root_layout);
        constraintLayoutOfActivity=getActivity().findViewById(R.id.root_layout);
        rootLayoutOfActivity.setVisibility(View.GONE);
        textInputLayout=view.findViewById(R.id.textInputLayout);
        pnrEditText=view.findViewById(R.id.pnr_edit_text);
        pnrButton=view.findViewById(R.id.pnr_button);
        googleMic=view.findViewById(R.id.google_mic);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pnrButton.setOnClickListener(this);
        googleMic.setOnClickListener(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        rootLayoutOfActivity.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.google_mic){
            onMicPressed();

        }
        else if(view.getId()==R.id.pnr_button){
        }
    }
    private void onMicPressed(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);



        try {
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivityForResult(intent, 10);
            } else {
                Toast.makeText(getActivity(), "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
            }
        }catch (NullPointerException e){
            Snackbar.make(constraintLayoutOfActivity,"Cannot be started now" , Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    pnrEditText.setText(result.get(0));
                }
                break;
        }
    }
}
