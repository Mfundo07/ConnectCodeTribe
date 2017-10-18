package com.example.android.connectcodetribe;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.connectcodetribe.Model.Qualification;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Admin on 10/13/2017.
 */

public class EducationActivity extends AppCompatActivity {

    private EditText degreeEditText;
    private EditText institutionEditText;
    private EditText startYearEditText;
    private EditText endYearEditText;
    private EditText majorEditText;
    private EditText facultyEditText;
    DatabaseReference mDatabaseReference;
    private Button saveInfoButton;
    FirebaseUser currentUser;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_education_and_qualification);

        degreeEditText = (EditText) findViewById(R.id.degree);
        institutionEditText = (EditText) findViewById(R.id.institute_edit);
        startYearEditText = (EditText) findViewById(R.id.startYear_edit);
        endYearEditText = (EditText) findViewById(R.id.end_year_edit);
        saveInfoButton = (Button) findViewById(R.id.btnSave);
        majorEditText = (EditText) findViewById(R.id.major_editText);
        facultyEditText = (EditText) findViewById(R.id.Employed_edit);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("/qualifications/").child(currentUser.getUid());
        saveInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Qualification items = new Qualification();
                items.setUserDegree(degreeEditText.getText().toString());
                items.setUserInstitution(institutionEditText.getText().toString());
                items.setStartYear(startYearEditText.getText().toString());
                items.setEndYear(endYearEditText.getText().toString());
                items.setFaculty(facultyEditText.getText().toString());
                items.setMajor(majorEditText.getText().toString());
                mDatabaseReference.setValue(items.toMap()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Qualification updated", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            task.getException().printStackTrace();
                        }
                    }
                });;

            }
        });

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                degreeEditText.setText((String) dataSnapshot.child("userDegree").getValue());
                institutionEditText.setText((String) dataSnapshot.child("userInstitution").getValue());
                facultyEditText.setText((String) dataSnapshot.child("userDegreeFaculty").getValue());
                majorEditText.setText((String) dataSnapshot.child("userFacultyMajor").getValue());
                startYearEditText.setText((String) dataSnapshot.child("userStartYear").getValue());
                endYearEditText.setText((String) dataSnapshot.child("userEndYear").getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
