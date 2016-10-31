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

public class ValidityCheckerFragment extends Fragment {

    Button checkButton;
    EditText pNumber;
    EditText name;
    ValidityChecks validityChecks;
    CandidateData candidateData;

    public Firebase mRef;

    public ValidityCheckerFragment() {
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

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                name.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        pNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                pNumber.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        validityChecks = new ValidityChecks();

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validityChecks.isNameEmpty(name.getText().toString()))
                    name.setError("Please enter a valid name");

                String formattedPNumber = validityChecks.toStdPNumberFormat(pNumber.getText().toString());

                if (formattedPNumber == null || !validityChecks.born(formattedPNumber) ||
                        !validityChecks.correctPNum(validityChecks.toIntArray(formattedPNumber))){
                    validityDialog(R.string.not_valid_title, R.string.wrong_pnumber_msg);
                    pNumber.setError(getContext().getString(R.string.p_number_error));

                    mRef.push().setValue(
                            new CandidateData(name.getText().toString(),
                                                pNumber.getText().toString(),
                                                Calendar.getInstance().getTime().toString()));
                }
                else{
                    validityDialog(R.string.valid_title, R.string.corrent_pnumber_msg);
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

    public void setmRef(Firebase mRef){
        this.mRef = mRef;
    }
}
