package com.jeansandtshirt.validitychecker;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainFragment extends ValidityCheckerFragment {

    Button checkButton;
    EditText pNumber;
    EditText name;
    ValidityChecks validityChecks;
    CandidateData candidateData;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_validity_checker, container, false);

        checkButton = (Button)view.findViewById(R.id.check_button);
        pNumber = (EditText)view.findViewById(R.id.p_number);
        name = (EditText)view.findViewById(R.id.name);
        name.requestFocus();

        validityChecks = new ValidityChecks();

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                candidateData = new CandidateData(name.getText().toString(),
                        pNumber != null ? validityChecks.toStdPNumberFormat(pNumber.getText().toString()) : null,
                        Calendar.getInstance().getTime().toString());

                if (validityChecks.isNull(candidateData) || !validityChecks.born(candidateData.pNumber) ||
                        !validityChecks.correctPNum(validityChecks.toIntArray(candidateData.pNumber))){
                    validityDialog(R.string.not_valid_title, R.string.wrong_pnumber_msg);

                    mRef.push().setValue(candidateData);
                }
                else{
                    validityDialog(R.string.valid_title, R.string.corrent_pnumber_msg);
                    clearTextFieldsAndSetFocus();
                }
            }
        });
        return view;
    }

    public void validityDialog(int title, int message){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(getContext().getString(title));
        dialog.setMessage(getContext().getString(message));
        dialog.setCancelable(false);
        dialog.setPositiveButton("OK", null);
        dialog.create();
        dialog.show();
    }

    public void clearTextFieldsAndSetFocus(){
        pNumber.setText("");
        name.setText("");
        name.requestFocus();
    }
}
