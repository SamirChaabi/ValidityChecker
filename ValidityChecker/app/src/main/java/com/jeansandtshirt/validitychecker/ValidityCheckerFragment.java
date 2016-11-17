package com.jeansandtshirt.validitychecker;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.firebase.client.Firebase;

/**
 * Created by samir on 2016-11-03.
 */
public class ValidityCheckerFragment extends Fragment {
    final String FIREBASE_KEY = "https://validitychecker-c5ec7.firebaseio.com/";

    public Firebase mRef = new Firebase(FIREBASE_KEY);
}
