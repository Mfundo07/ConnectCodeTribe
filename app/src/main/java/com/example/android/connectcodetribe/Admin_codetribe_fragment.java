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

public class Admin_codetribe_fragment extends AppCompatActivity {

    FloatingActionButton mProfileBackFabButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_codetribe_fragment);

        mProfileBackFabButton =(FloatingActionButton) findViewById(R.id.admin_profile_back_fab_button);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);



        CategoryAdapter adapter = new CategoryAdapter(getSupportFragmentManager(), this);

        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        mProfileBackFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin_codetribe_fragment.this, Admin_profile.class));
            }
        });




    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.sign_out_menu:
                startActivity(new Intent(this, LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

}