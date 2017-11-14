package com.example.android.connectcodetribe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

public class ScrollingFragment extends AppCompatActivity {

    TextView mEmail,mCurrentOccupation;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        mEmail = (TextView) findViewById(R.id.User_Email);
        mCurrentOccupation = (TextView) findViewById(R.id.User_occupation);
    }
}
