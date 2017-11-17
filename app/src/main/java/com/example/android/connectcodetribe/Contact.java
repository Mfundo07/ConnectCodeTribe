package com.example.android.connectcodetribe;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.connectcodetribe.Model.Contact_details;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by Admin on 11/17/2017.
 */

public class Contact extends AppCompatActivity {

    private EditText mHighestQualifi;
    private EditText mDescription;
    private EditText mInstitute;


    private Button ButUpload;
    private Uri filepath;
    private final int PICK_IMAGE_REQUEST = 71;
    DatabaseReference mRef;
    private EditText mMobile, mEmail, mTribe, mIntake, mStatus;

    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseUser currentUser;
    Object model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_slide);
        //Firebase Init
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        //Init view
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        ButUpload = (Button) findViewById(R.id.bnUploadcontact);
        mRef = FirebaseDatabase.getInstance().getReference("/Contact_details/").child(currentUser.getUid());

        mMobile = (EditText) findViewById(R.id.mobile);
        mEmail = (EditText) findViewById(R.id.email);
        mTribe = (EditText) findViewById(R.id.tribe);
        mIntake = (EditText) findViewById(R.id.intake);
        mStatus = (EditText) findViewById(R.id.statuss);


        ButUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact_details contact_details = new Contact_details();
                contact_details.setMobile(mMobile.getText().toString());
                contact_details.setEmail(mEmail.getText().toString());
                contact_details.setTribe(mTribe.getText().toString());
                contact_details.setIntake(mIntake.getText().toString());
                contact_details.setStatus(mStatus.getText().toString());
                mRef.setValue(contact_details.toMap()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Profile updated", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            task.getException().printStackTrace();
                        }
                    }
                });



            }
        });

    }
}

