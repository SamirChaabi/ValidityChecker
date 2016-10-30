package com.jeansandtshirt.validitychecker;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class ValidityCheckerFragment extends Fragment {

    Button checkButton;
    EditText pNumber;
    ValidityChecks validityChecks;

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
        validityChecks = new ValidityChecks();

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Integer> ints = validityChecks.toIntArray(pNumber.getText().toString());
                if (ints == null || !validityChecks.correctPNum(ints)){
                    wrongPNumberDialog();
                }
                else{
                    correntPnumberDialog();
                }
            }
        });
        return view;
    }

    public void wrongPNumberDialog(){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(R.string.not_valid_title);
        dialog.setMessage(getContext().getString(R.string.wrong_pnumber_msg));
        dialog.setCancelable(false);
        dialog.setPositiveButton("OK", null);
        dialog.create();
        dialog.show();
    }

    public void correntPnumberDialog(){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(R.string.valid_title);
        dialog.setMessage(getContext().getString(R.string.corrent_pnumber_msg));
        dialog.setCancelable(false);
        dialog.setPositiveButton("OK", null);
        dialog.create();
        dialog.show();
    }
}
