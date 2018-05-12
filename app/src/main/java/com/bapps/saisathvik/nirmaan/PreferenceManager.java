package com.bapps.saisathvik.nirmaan;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Sai Sathvik on 3/17/2018.
 */

public class PreferenceManager {

    private Context context;
    private SharedPreferences sharedPreferences;

    public PreferenceManager(Context context)
    {
        this.context = context;
        getSharedPreferences();
    }


    private void getSharedPreferences()
    {

        sharedPreferences = context.getSharedPreferences(context.getString(R.string.my_preference), Context.MODE_PRIVATE);
    }

    public void writePreferences()
    {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.my_prefernce_key),"INIT_OK");
        editor.commit();

    }

    public boolean checkPreference()
    {

        boolean status = false;

        if (sharedPreferences.getString(context.getString(R.string.my_prefernce_key),"null").equals("null"))
        {
        status = false;
        }
        else {

            status = true;
        }

        return status;
    }

    public void clearPreferences(){

        sharedPreferences.edit().clear().commit();
    }
}
