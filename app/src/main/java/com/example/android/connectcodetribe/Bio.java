package com.example.android.connectcodetribe;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.connectcodetribe.Model.TribeMate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by Admin on 11/16/2017.
 */

public class Bio extends AppCompatActivity {

    private EditText mYear;
    private EditText mMonth;
    private EditText mDay;


    private Button add_bio_button;
    private Uri filepath;
    private final int PICK_IMAGE_REQUEST = 71;
    DatabaseReference mRef;
    private EditText mName, mSurname, mGender, mDateOfBirth, mEthnicity, mMobile, mEmail, mTribe, mIntake, mStatus;

    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseUser currentUser;
    Object model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_slide);
        //Firebase Init
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        //Init view
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        add_bio_button = (Button) findViewById(R.id.add_bio_button);
        mRef = FirebaseDatabase.getInstance().getReference("/tribeMates/").child(currentUser.getUid());
        mName = (EditText) findViewById(R.id.namedit);
        mSurname = (EditText) findViewById(R.id.surnamedit);
        mDateOfBirth = (EditText) findViewById(R.id.dateOfBirth);
        mEthnicity = (EditText) findViewById(R.id.ethnicity);
        mGender = (EditText) findViewById(R.id.genderedit);
        mMobile = (EditText) findViewById(R.id.mobile);
        mEmail = (EditText) findViewById(R.id.email);
        mTribe = (EditText) findViewById(R.id.tribe);
        mIntake = (EditText) findViewById(R.id.intake);
        mStatus = (EditText) findViewById(R.id.statuss);


        add_bio_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TribeMate tribeMate = new TribeMate();
                tribeMate.setName(mName.getText().toString());
                tribeMate.setSurname(mSurname.getText().toString());
                tribeMate.setAge(Long.valueOf(mDateOfBirth.getText().toString()));
                tribeMate.setEthnicity(mEthnicity.getText().toString());
                tribeMate.setGender(mGender.getText().toString());
                tribeMate.setMobile(mMobile.getText().toString());
                tribeMate.setEmail(mEmail.getText().toString());
                tribeMate.setCodeTribe(mTribe.getText().toString());
                tribeMate.setIntakeYear(mIntake.getText().toString());
                tribeMate.setStatus(mStatus.getText().toString());
                mRef.setValue(tribeMate).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Profile updated", Toast.LENGTH_SHORT).show();
                        } else {
                            task.getException().printStackTrace();
                        }
                    }
                });


            }
        });

    }
}

