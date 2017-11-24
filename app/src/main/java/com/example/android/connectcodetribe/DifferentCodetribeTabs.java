package com.example.android.connectcodetribe;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.android.connectcodetribe.Adapters.CategoryAdapter;
import com.example.android.connectcodetribe.profile.ProfileActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DifferentCodetribeTabs extends AppCompatActivity {

    FloatingActionButton mProfileBackFabButton;
    FirebaseUser currentUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_activity);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }else {
            startActivity(new Intent(this, UserProfileEditorActivity.class));
        }

        mProfileBackFabButton = findViewById(R.id.profile_back_fab_button);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);



        CategoryAdapter adapter = new CategoryAdapter(getSupportFragmentManager(), this);

        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        mProfileBackFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DifferentCodetribeTabs.this, ProfileActivity.class));
            }
        });




    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getGroupId();

        if(id==R.id.btnLogout);

        Intent intentLogout = new Intent(DifferentCodetribeTabs.this,LoginActivity.class);
        startActivity(intentLogout);
        finish();

        if(id==R.id.btnAdmin);

        Intent intentAdmin = new Intent(DifferentCodetribeTabs.this,Admin_Login_Activity.class);
        startActivity(intentAdmin);
        finish();

        if(id==R.id.btnAbout);

        Intent intentAbout = new Intent(DifferentCodetribeTabs.this,AboutActivity.class);
        startActivity(intentAbout);
        finish();

        return true;




    }
}
