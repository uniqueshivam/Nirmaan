package com.bapps.saisathvik.nirmaan.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bapps.saisathvik.nirmaan.Fragment.IndependentLabours;
import com.bapps.saisathvik.nirmaan.Fragment.SignInFragment;
import com.bapps.saisathvik.nirmaan.Fragment.SignUpFragment;
/**
 * Created by Sai Sathvik on 3/18/2018.
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {

    private Context context;

    public MyFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position)
    {
        if (position == 0)
            return SignInFragment.getINSTANCE();

        else if (position == 1)
            return SignUpFragment.getINSTANCE();

        else if (position == 2)
            return IndependentLabours.getINSTANCE();

        else
            return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return " AGENCY SIGN IN";
            case 1:
                return "AGENCY SIGN UP";

            case 2:
                return "LABOUR FORM";

        }

        return "";

    }
}
