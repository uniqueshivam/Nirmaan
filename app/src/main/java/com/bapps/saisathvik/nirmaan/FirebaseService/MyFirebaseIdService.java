package com.bapps.saisathvik.nirmaan.FirebaseService;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Sai Sathvik on 3/30/2018.
 */

public class MyFirebaseIdService extends FirebaseInstanceIdService {


    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        sendNewTokenToServer(FirebaseInstanceId.getInstance().getToken());
    }

    private void sendNewTokenToServer(String token) {

        Log.d("SAI Token",token);

    }
}
