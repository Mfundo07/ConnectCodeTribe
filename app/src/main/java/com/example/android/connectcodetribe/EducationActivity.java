package com.example.android.connectcodetribe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Admin on 10/13/2017.
 */

public class EducationActivity extends AppCompatActivity {

    private EditText degreeEditText;
    private EditText institutionEditText;
    private EditText startYearEditText;
    private EditText endYearEditText;
    DatabaseReference mDatabaseReference;
    private Button SaveInfoButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_education_and_qualification);

        degreeEditText = (EditText) findViewById(R.id.degree);
        institutionEditText = (EditText) findViewById(R.id.institute_edit);
        startYearEditText = (EditText) findViewById(R.id.Employed_edit);
        endYearEditText = (EditText) findViewById(R.id.end_year_edit);
        SaveInfoButton = (Button) findViewById(R.id.btnSave);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("verified_user_profile");
    }
}
