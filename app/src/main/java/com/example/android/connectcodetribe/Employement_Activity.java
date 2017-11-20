package com.example.android.connectcodetribe;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.connectcodetribe.Model.Employment;
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

public class Employement_Activity extends AppCompatActivity {




    private Button bnUpload;
    private Uri filepath;
    private final int PICK_IMAGE_REQUEST = 71;
    DatabaseReference mRef;
    private EditText mEmploy, mCurrent, mCompany, mJobtitle, mStart;

    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseUser currentUser;
    Object model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourth_slide);
        //Firebase Init
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        //Init view
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        bnUpload = (Button) findViewById(R.id.bnUpload);
        mRef = FirebaseDatabase.getInstance().getReference("/Employment_details/").child(currentUser.getUid());
        mEmploy = (EditText) findViewById(R.id.previousemployment);
        mCurrent = (EditText) findViewById(R.id.employedd);
        mCompany = (EditText) findViewById(R.id.companyname);
        mJobtitle = (EditText) findViewById(R.id.jobtitle);
        mStart = (EditText) findViewById(R.id.startd);


        bnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Employment employment = new Employment();
                employment.setEmploymenyStatus(mEmploy.getText().toString());
                employment.setCompanyContactNumber(mCurrent.getText().toString());
                employment.setCompanyName(mCompany.getText().toString());
                employment.setSalary(mJobtitle.getText().toString());
                employment.setStartDate(mStart.getText().toString());
                Toast.makeText(Employement_Activity.this, "info added", Toast.LENGTH_SHORT).show();

                mRef.setValue(employment.toMap()).addOnCompleteListener(new OnCompleteListener<Void>() {
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








