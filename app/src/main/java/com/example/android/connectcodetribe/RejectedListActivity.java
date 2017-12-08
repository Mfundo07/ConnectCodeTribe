package com.example.android.connectcodetribe;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.android.connectcodetribe.Adapters.AdminAcceptCategoryAdapter;

/**
 * Created by Admin on 12/8/2017.
 */

public class RejectedListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    FloatingActionButton mProfileBackFabButton;

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigatorrr_kb);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarkb);
        toolbar.setTitle ( "Rejected List" );
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);



        AdminAcceptCategoryAdapter adapter = new AdminAcceptCategoryAdapter(getSupportFragmentManager(), this);

        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        //mProfileBackFabButton.setOnClickListener(new View.OnClickListener() {
        //   @Override
        //  public void onClick(View v) {
        //   startActivity(new Intent(RequestActivity.this, Admin_profile.class));
        //  }
        // });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_add_members) {

            Intent logout = new Intent(RejectedListActivity.this, Admin_user_profile_editor.class);
            startActivity(logout);
            return true;

        } else if (id == R.id.nav_request) {

            Intent request = new Intent(RejectedListActivity.this, RequestActivity.class);
            startActivity(request);
            return true;

        }else if (id == R.id.nav_my_profile) {

            Intent profile = new Intent(RejectedListActivity.this, Admin_profile.class);
            startActivity(profile);
            return true;

        }

        else if (id == R.id.nav_rejected_request) {

            Intent nonclair = new Intent(RejectedListActivity.this, RejectedListActivity.class);
            startActivity(nonclair);
            return true;

        }

        else if (id == R.id.nav_accepted_request) {

            Intent accept = new Intent(RejectedListActivity.this, AcceptedActivity.class);
            startActivity(accept);
            return true;

        } else if (id == R.id.nav_logout) {

            Intent logout = new Intent(RejectedListActivity.this, Admin_Login_Activity.class);
            startActivity(logout);
            return true;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;




    }




}

