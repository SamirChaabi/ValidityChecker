package com.jeansandtshirt.validitychecker;

import android.support.v4.app.Fragment;

import com.firebase.client.Firebase;

/**
 * Created by samir on 2016-11-03.
 */
public class ValidityCheckerFragment extends Fragment {

    public Firebase mRef;

    public void setMRef(Firebase mRef){
        this.mRef = mRef;
    }
}
