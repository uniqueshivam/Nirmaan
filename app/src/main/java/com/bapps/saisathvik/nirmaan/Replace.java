package com.bapps.saisathvik.nirmaan;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bapps.saisathvik.nirmaan.Fragment.SkillDevelopmentFragment;

public class Replace extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replace);


        String s = getIntent().getExtras().getString("TabName");

        if (s.equals("Adv")){

            SkillDevelopmentFragment advertisingFragment = new SkillDevelopmentFragment();
            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.replace_layout,advertisingFragment,"myFragmentTag");
            ft.commit();

        }
    }
}
