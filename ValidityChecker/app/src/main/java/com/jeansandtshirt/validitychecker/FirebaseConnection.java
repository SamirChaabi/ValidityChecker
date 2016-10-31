package com.jeansandtshirt.validitychecker;

import com.firebase.client.Firebase;

/**
 * Created by samir on 2016-10-31.
 */
public class FirebaseConnection extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
