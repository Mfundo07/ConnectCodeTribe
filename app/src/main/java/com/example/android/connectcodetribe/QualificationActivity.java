package com.example.android.connectcodetribe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.connectcodetribe.Model.Qualification;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QualificationActivity extends AppCompatActivity {
    Button addQualification;
    DatabaseReference mDatabaseReference;
    FirebaseUser currentUser;
    TextView certificateTextView;
    TextView instituteTextView;
    TextView startYearTextView;
    TextView endYearTextView;
    TextView facultyTextView;
    TextView majorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_education_and_qualification);
        certificateTextView = (TextView) findViewById(R.id.txtcert);
        instituteTextView = (TextView) findViewById(R.id.txtinst);
        startYearTextView = (TextView) findViewById(R.id.startYear_text);
        endYearTextView = (TextView) findViewById(R.id.end_year_text);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        facultyTextView = (TextView) findViewById(R.id.txtFaculty);
        majorTextView = (TextView) findViewById(R.id.txtMajor);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("/qualifications/").child(currentUser.getUid());

        addQualification = (Button) findViewById(R.id.btnSave);

        addQualification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        QualificationActivity.this,
                        EducationActivity.class
                );
                startActivity(intent);
            }
        });


        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Qualification value = dataSnapshot.getValue(Qualification.class);
                certificateTextView.setText(value.getUserDegree());
                instituteTextView.setText(value.getUserInstitution());
                startYearTextView.setText((String) dataSnapshot.child("userStartYear").getValue());
                endYearTextView.setText((String) dataSnapshot.child("userEndYear").getValue());
                facultyTextView.setText((String) dataSnapshot.child("userDegreeFaculty").getValue());
                majorTextView.setText((String) dataSnapshot.child("userFacultyMajor").getValue());



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}
