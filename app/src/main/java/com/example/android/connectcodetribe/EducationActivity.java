package com.example.android.connectcodetribe;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.connectcodetribe.Model.Education;
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

public class EducationActivity extends AppCompatActivity {

    private EditText mHighestQualifi;
    private EditText mDescription;
    private EditText mInstitute;


    private Button ButUpload;
    private Uri filepath;
    private final int PICK_IMAGE_REQUEST = 71;
    DatabaseReference mRef;
    private EditText mHighestQualification, mQualificationDesc, mQualificationInst;

    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseUser currentUser;
    Object model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_slide);
        //Firebase Init
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        //Init view
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        ButUpload = (Button) findViewById(R.id.bnNext);
        mRef = FirebaseDatabase.getInstance().getReference("/education/").child(currentUser.getUid());

        mHighestQualification = (EditText) findViewById(R.id.qualificationshigh);
        mQualificationDesc = (EditText) findViewById(R.id.qualificationquali);
        mQualificationInst = (EditText) findViewById(R.id.qualificationinst);


        ButUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Education education = new Education();
                education.setQualifiction(mHighestQualification.getText().toString());
                education.setDesc(mQualificationDesc.getText().toString());
                education.setInstitute(mQualificationInst.getText().toString());
                mRef.setValue(education.toMap()).addOnCompleteListener(new OnCompleteListener<Void>() {
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


