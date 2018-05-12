package com.bapps.saisathvik.nirmaan.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bapps.saisathvik.nirmaan.Fragment.AgencyListFragment;
import com.bapps.saisathvik.nirmaan.Fragment.LaboursFragment;


/**
 * Created by Sai Sathvik on 3/23/2018.
 */

public class AgencyFragmentAdapter extends FragmentPagerAdapter {

    private Context context;

    public AgencyFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position)
    {
        if (position == 0)
            return AgencyListFragment.getINSTANCE();

        else if (position == 1)
            return LaboursFragment.getINSTANCE();

        else
            return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return " AGENCIES PROFILE";
            case 1:
                return "INDEPENDENT LABOURS";

        }

        return "";

    }
}
