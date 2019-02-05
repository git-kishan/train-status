package com.shubham.srikanth.kishan.FragmentPackage;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.shubham.srikanth.kishan.R;
import java.util.ArrayList;
import java.util.Locale;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import static android.app.Activity.RESULT_OK;


public class PnrStatusBottomFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    private View rootLayoutOfActivity;
    private TextInputLayout textInputLayout;
    private TextInputEditText pnrEditText;
    private MaterialButton pnrButton;
    private ImageView googleMic;
    private View rootView;

    public PnrStatusBottomFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView=getLayoutInflater().inflate(R.layout.fragment_pnr_status_bottom,container,false );
        textInputLayout = rootView.findViewById(R.id.textInputLayout);
        pnrEditText = rootView.findViewById(R.id.pnr_edit_text);
        pnrButton = rootView.findViewById(R.id.pnr_button);
        googleMic = rootView.findViewById(R.id.google_mic);
        rootLayoutOfActivity = getActivity().findViewById(R.id.root_layout);
        return rootView;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pnrButton.setOnClickListener(this);
        googleMic.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.google_mic) {
            onMicPressed();

        } else if (view.getId() == R.id.pnr_button) {
            Toast.makeText(getActivity(),"clicked" ,Toast.LENGTH_LONG ).show();
        }
    }

    private void onMicPressed() {
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
        } catch (NullPointerException e) {
            Snackbar.make(rootLayoutOfActivity, "Cannot be started now", Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String mdata=result.get(0);
                    StringBuilder builder = new StringBuilder();
                    char [] mmdata=mdata.toCharArray();
                    boolean valid=true;
                    int count=0;
                    for (int i=0;i<mdata.length();i++){
                        if(!Character.isDigit(mmdata[i])&&mmdata[i]!=' '){
                            valid=false;
                            break;
                        }
                        if(mmdata[i]==' '){
                            continue;
                        }builder.append(mmdata[i]);
                        count++;
                    }
                    if(!valid){
                        Toast.makeText(getActivity(),"Please speak pnr no." , Toast.LENGTH_SHORT).show();
                        return;
                    } else if(count!=4) {
                        Toast.makeText(getActivity(),"Check your pnr no." , Toast.LENGTH_SHORT).show();
                        return;
                    }
                    pnrEditText.setText(builder.toString());
                }
                break;
        }
    }
}
