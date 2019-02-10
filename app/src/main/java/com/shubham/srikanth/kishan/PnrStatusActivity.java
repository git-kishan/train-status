package com.shubham.srikanth.kishan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;


import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.shubham.srikanth.kishan.Interface.DrawableClickListener;
import com.shubham.srikanth.kishan.Interface.EditTextFocusChangeListener;
import com.shubham.srikanth.kishan.Listener.CustomEditText;

import java.util.ArrayList;
import java.util.Locale;

public class PnrStatusActivity extends AppCompatActivity implements  View.OnClickListener {

    private Toolbar toolbar;
    private TextInputLayout textInputLayout;
    private TextInputEditText pnrEditText;
    private MaterialButton pnrButton;
    private ConstraintLayout rootLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pnr_status);
        initilization();
        attachListener();



    }

    private void initilization(){
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textInputLayout=findViewById(R.id.textInputLayout);
        pnrEditText=findViewById(R.id.pnr_edit_text);
        pnrButton=findViewById(R.id.pnr_button);
        rootLayout=findViewById(R.id.root_layout);
    }
    private void attachListener(){
        pnrButton.setOnClickListener(this);
        rootLayout.setOnClickListener(this);
        EditTextFocusChangeListener focusChangeListener=new EditTextFocusChangeListener(this);
        pnrEditText.setOnFocusChangeListener(focusChangeListener);


    }



    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.pnr_button:
                break;
            case R.id.root_layout:
                rootLayout.requestFocus();
                break;
        }
    }

    private String extractUserEntry(String micText){
        char [] speechText=micText.toCharArray();
        char [] resultText=new char[1000];
        int i=0;
        for(char c:speechText){
            if(Character.isDigit(c)){
                resultText[i]=c;
                i++;
            }
            else if(c==' '){
                continue;
            }
        }

        return String.valueOf(resultText);
    }
    private boolean validateUserEntry(String micText){
        char [] speechText=micText.toCharArray();
        for(char c :speechText){
             if(Character.isLetter(c)){
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
