package com.bapps.saisathvik.nirmaan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.bapps.saisathvik.nirmaan.Adapter.AgencyFragmentAdapter;
import com.bapps.saisathvik.nirmaan.Adapter.MyFragmentAdapter;
import com.bapps.saisathvik.nirmaan.Fragment.AgencyListFragment;
import com.github.clans.fab.FloatingActionMenu;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager viewPager;
    TabLayout tabLayout;
    FloatingActionMenu menu;
    com.github.clans.fab.FloatingActionButton id_search_agency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        menu = (FloatingActionMenu)findViewById(R.id.menu);
        id_search_agency = (com.github.clans.fab.FloatingActionButton )findViewById(R.id.id_search_agency);



        toolbar.setTitle("Nirmaan");
        setSupportActionBar(toolbar);

        viewPager = (ViewPager)findViewById(R.id.viewPager);
        AgencyFragmentAdapter adapter = new AgencyFragmentAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager) ;
        id_search_agency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchIntent  = new Intent(Home.this,SearchActivity.class);
                startActivity(searchIntent);
            }
        });




            //hideItem();




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();




        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }

    //private void hideItem() {
      //  NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //Menu nav_menu = navigationView.getMenu();
        //Intent intent = getIntent();
        //String s = intent.getStringExtra("guest");

    //nav_menu.findItem(R.id.nav_signOut).setVisible(false);





    //}


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);




        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();









        if (id == R.id.nav_skilldevelopment) {
            // Handle the camera action

            Intent intenthello = new Intent(Home.this,Replace.class);
            intenthello.putExtra("TabName","Adv");
            startActivity(intenthello);

        } else if (id == R.id.nav_safety) {

        } else if (id == R.id.nav_EditProfile) {

        } else if (id == R.id.nav_addYourLabours){

            Intent stlabour = new Intent(Home.this,LabourFormActivity.class);
            stlabour.putExtra("agency",sharedpreference_agencylogin.getInstance(this).get_agency_id());
            startActivity(stlabour);

        }
        else if (id == R.id.nav_viewYourLabours){
            Intent viewLabours = new Intent(Home.this,ViewYourAgencylaboursActivity.class);
            startActivity(viewLabours);


        }
        else if (id == R.id.nav_wagesList){
            Intent wageIntent = new Intent(Home.this,WageListActivity.class);
            startActivity(wageIntent);


        }
        else if(id == R.id.nav_requirementsList){

            Intent requireIntent = new Intent(Home.this,PostRequirementActivity.class);
            startActivity(requireIntent);


        }

        else if (id == R.id.nav_signOut) {

            sharedpreference_agencylogin.getInstance(this).logout();

            startActivity(new Intent(Home.this,MainActivity.class));
            finish();

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
