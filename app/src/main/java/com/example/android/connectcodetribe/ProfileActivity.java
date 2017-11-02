package com.example.android.connectcodetribe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {



    Button addExperience;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    FirebaseUser mAuth;
    EditText TribeEditText, projectLinkEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.model);




        mDatabaseReference = FirebaseDatabase.getInstance().getReference("/ExperienceAdapter/").child(mAuth.getUid());
        mAuth = FirebaseAuth.getInstance().getCurrentUser();


    }
}
