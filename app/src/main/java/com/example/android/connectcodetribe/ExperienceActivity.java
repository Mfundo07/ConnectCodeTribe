package com.example.android.connectcodetribe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class ExperienceActivity extends AppCompatActivity {

    TextView period;
    TextView CompanyName;
    TextView position;
    Button add;
    DatabaseReference mDatabaseReference;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.model);

        period = (TextView)findViewById(R.id.periodTxt);
        CompanyName = (TextView)findViewById(R.id.companynameTxt);
        position = (TextView)findViewById(R.id.positionTxt);

    }
}



