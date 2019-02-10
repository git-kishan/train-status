package com.shubham.srikanth.kishan.Interface;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.shubham.srikanth.kishan.R;


public class EditTextFocusChangeListener implements View.OnFocusChangeListener {
    private Context context;
    public EditTextFocusChangeListener(Context context){
        this.context=context;
    }
    @Override
    public void onFocusChange(View view, boolean b) {
        InputMethodManager inputMethodManager =  (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        if(view.getId()== R.id.data_taker&& !b){
            try {
                if(inputMethodManager!=null)
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }catch (NullPointerException nullPointerException){
                Log.i("TAG","NullPointerException :- "+nullPointerException.getMessage() );
            }
        }
        else if(view.getId()==R.id.train_no_edit_text && !b){

            try {
                if(inputMethodManager!=null)
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }catch (NullPointerException nullPointerException){
                Log.i("TAG","NullPointerException :- "+nullPointerException.getMessage() );
            }
        }
        else if((view.getId()==R.id.pnr_edit_text)||(view.getId()==R.id.train_no_edit_text) && !b){

            try {
                if(inputMethodManager!=null)
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }catch (NullPointerException nullPointerException){
                Log.i("TAG","NullPointerException :- "+nullPointerException.getMessage() );
            }
        }


    }
}
