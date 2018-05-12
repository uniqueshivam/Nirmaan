package com.bapps.saisathvik.nirmaan;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bapps.saisathvik.nirmaan.Adapter.MyFragmentAdapter;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager)findViewById(R.id.viewPager);
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(),getApplicationContext());
        viewPager.setAdapter(adapter);


        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }
}
