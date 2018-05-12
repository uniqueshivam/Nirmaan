package com.bapps.saisathvik.nirmaan;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by shivam gupta on 19-03-2018.
 */

public class sharedpreferencemanager {
    //declaring key variables
    private static sharedpreferencemanager mInstance;
    private static final String shared_pref_name="mysharedpref";
    //keys for the contractor login
    private static final String KEY_CONTRACTOR_ID="contractor_id";
    private static final String KEY_CONTRACTOR_EMAIL="contractor_email";
    private static final String KEY_CONTRACTOR_PASSWORD="contractor_password";
    private static final String KEY_COMPANY_NAME="company_name";
    private static final String KEY_CONTRACTOR_NAME="contractor_name";
    private static final String KEY_CONTRACTOR_PHONE="contractor_phone";

    private ImageLoader mImageLoader;
    private static Context mCtx;

    private sharedpreferencemanager(Context context) {
        mCtx = context;


//        mImageLoader = new ImageLoader(mRequestQueue,
//                new ImageLoader.ImageCache() {
//                    private final LruCache<String, Bitmap>
//                            cache = new LruCache<String, Bitmap>(20);
//
//                    @Override
//                    public Bitmap getBitmap(String url) {
//                        return cache.get(url);
//                    }
//
//                    @Override
//                    public void putBitmap(String url, Bitmap bitmap) {
//                        cache.put(url, bitmap);
//                    }
//                });
    }

    public static synchronized sharedpreferencemanager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new sharedpreferencemanager(context);
        }
        return mInstance;
    }

    //storing the data of the contractor after login in the sharedpreferences

    public boolean contractorlogin(String contractor_id, String contractor_email, String contractor_password, String company_name, String contractor_name,
                                   String contractor_phone)
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(shared_pref_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(KEY_CONTRACTOR_ID,contractor_id);//storing the data fetched in our key values.
        editor.putString(KEY_CONTRACTOR_EMAIL,contractor_email);
        editor.putString(KEY_CONTRACTOR_PASSWORD,contractor_password);
        editor.putString(KEY_COMPANY_NAME,company_name);
        editor.putString(KEY_CONTRACTOR_NAME,contractor_name);
        editor.putString(KEY_CONTRACTOR_PHONE,contractor_phone);
        editor.apply();
        return true;

    }
    //checking is the user still login,by checking if there is any email present in the
    public boolean isUserlogedin()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(shared_pref_name, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_CONTRACTOR_EMAIL,null)!=null)
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


    public String get_contractor_id()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(shared_pref_name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_CONTRACTOR_ID,null);

    }
    public String get_contractor_email()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(shared_pref_name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_CONTRACTOR_EMAIL,null);

    }
    public String get_company_name()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(shared_pref_name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_COMPANY_NAME,null);
    }

    public String get_contractor_name()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(shared_pref_name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_CONTRACTOR_NAME,null);
    }

    public String get_contractor_phone()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(shared_pref_name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_CONTRACTOR_PHONE,null);
    }

}
