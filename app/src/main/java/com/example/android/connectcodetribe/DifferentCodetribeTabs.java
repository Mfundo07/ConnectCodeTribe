package com.example.android.connectcodetribe;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.android.connectcodetribe.Adapters.CategoryAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DifferentCodetribeTabs extends AppCompatActivity {

    FloatingActionButton mProfileBackFabButton;
    FirebaseUser currentUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_activity);

        mProfileBackFabButton=(FloatingActionButton)findViewById(R.id.profile_back_fab_button);
        mProfileBackFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profile = new Intent(DifferentCodetribeTabs.this, UserProfileEditorActivity.class);
                startActivity(profile);
            }
        });

       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarkb);
        setSupportActionBar(toolbar);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null){
            startActivity(new Intent(this, LoginActivity.class));
        finish();}

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        CategoryAdapter adapter = new CategoryAdapter(getSupportFragmentManager(), this);

        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getGroupId();

        //noinspection SimplifiableIfStatement

        switch (item.getItemId()) {
            case R.id.btnLogout:
                Intent logout = new Intent(DifferentCodetribeTabs.this, LoginActivity.class);
                startActivity(logout);
                return true;
            case R.id.btnAdmin:
                Intent admin = new Intent(DifferentCodetribeTabs.this, Admin_Login_Activity.class);
                startActivity(admin);
                return true;
            case R.id.btnAbout:
                Intent about = new Intent(DifferentCodetribeTabs.this, AboutActivity.class);
                startActivity(about);
                return true;
            default:
                return super.onOptionsItemSelected(item);




    }
    }
}
