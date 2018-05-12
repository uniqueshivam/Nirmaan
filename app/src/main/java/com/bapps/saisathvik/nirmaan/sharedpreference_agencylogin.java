package com.bapps.saisathvik.nirmaan;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by shivam gupta on 20-03-2018.
 */

public class sharedpreference_agencylogin {
    private static sharedpreference_agencylogin mInstance;
    private static final String shared_pref_name="mysharedpref";
    //keys for the agency login

    private static final String KEY_AGENCY_ID="agency_id";
    private static final String KEY_AGENCY_EMAIL="agency_email";
    private static final String KEY_AGENCY_PASSWORD="agency_password";
    private static final String KEY_AGENCY_NAME="agency_name";
    private static final String KEY_AGENCY_OWNER="agency_owner";
    private static final String KEY_AGENCY_PHONE="agecny_phone";
    private static final String KEY_AGENCY_SERVICE_AREA="agency_service_area";
    private static final String KEY_AGENCY_RATING="agency_rating";

    private ImageLoader mImageLoader;
    private static Context mCtx;

    private sharedpreference_agencylogin(Context context) {
        mCtx = context;
    }

    public static synchronized sharedpreference_agencylogin getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new sharedpreference_agencylogin(context);
        }
        return mInstance;
    }

    public boolean agencylogin(String agency_id, String agency_email, String agency_password, String agency_name, String agency_owner,
                               String agency_phone, String agency_service_area)
    {
        SharedPreferences sharedPreferences =mCtx.getSharedPreferences(shared_pref_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString(KEY_AGENCY_ID,agency_id);
        editor.putString(KEY_AGENCY_EMAIL,agency_email);
        editor.putString(KEY_AGENCY_PASSWORD,agency_password);
        editor.putString(KEY_AGENCY_NAME,agency_name);
        editor.putString(KEY_AGENCY_OWNER,agency_owner);
        editor.putString(KEY_AGENCY_PHONE,agency_phone);
        editor.putString(KEY_AGENCY_SERVICE_AREA,agency_service_area);
       // editor.putString(KEY_AGENCY_RATING,agency_rating);
        editor.apply();
        return true;
    }
    public boolean isAgencylogedin()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(shared_pref_name, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_AGENCY_EMAIL,null)!=null)
        {
            return true;

        }
        else
        {
            return false;
        }
    }

    public boolean logout()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(shared_pref_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;

    }


    public String get_agency_id()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(shared_pref_name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_AGENCY_ID,null);

    }
    public String get_agency_email()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(shared_pref_name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_AGENCY_EMAIL,null);

    }
    public String get_agency_name()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(shared_pref_name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_AGENCY_NAME,null);
    }

    public String get_agency_owner()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(shared_pref_name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_AGENCY_OWNER,null);
    }

    public String get_agency_phone()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(shared_pref_name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_AGENCY_PHONE,null);
    }

    public String get_agency_service_area()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(shared_pref_name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_AGENCY_SERVICE_AREA,null);
    }

//    public String get_agency_rating()
//    {
//        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(shared_pref_name,Context.MODE_PRIVATE);
//        return sharedPreferences.getString(KEY_AGENCY_RATING,null);
//    }
}
