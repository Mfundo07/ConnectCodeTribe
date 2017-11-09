package com.example.android.connectcodetribe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.android.connectcodetribe.Fragments.FragmentSingle;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = getSupportFragmentManager().findFragmentById(android.R.id.content);
        if(fragment == null){
            fragment = FragmentSingle.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(android.R.id.content, fragment, "")
                    .commit();

        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .attach(fragment)
                    .commit();
        }

    }
}
