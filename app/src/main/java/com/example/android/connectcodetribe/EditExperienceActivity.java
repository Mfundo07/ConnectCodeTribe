package com.example.android.connectcodetribe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.connectcodetribe.Model.Experience;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Admin on 11/2/2017.
 */

public class EditExperienceActivity extends AppCompatActivity {

    EditText startYear;
    EditText endYear;
    EditText companyName;
    EditText position;
    Button addBtn;

    DatabaseReference myRef;
    FirebaseDatabase database;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_input_experience);

        startYear = (EditText)findViewById(R.id.startYearEditText);
        endYear =(EditText)findViewById(R.id.endYearEditText);
        companyName =(EditText)findViewById(R.id.companyNameEditText);
        position = (EditText)findViewById(R.id.positionEditText);
        addBtn = (Button)findViewById(R.id.Addbtn);

        database = FirebaseDatabase.getInstance();
        //myRef = database.getReference("testing").child("users").child("codetribe").child("Soweto").child("0").child("experience").child("0");

        Experience item = new Experience();
        item.setStartYear(startYear.getText().toString());
        item.setEndYear(endYear.getText().toString());
        item.setPosition(position.getText().toString());
        item.setCompanyName(companyName.getText().toString());
        myRef.setValue(item.toMap());

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditExperienceActivity.this, ExperienceActivity.class);
                startActivity(intent);
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               // EditExperienceActivity values = dataSnapshot.getValue(EditExperienceActivity.class);

                startYear.setText((String)dataSnapshot.child("startYear").getValue());
                endYear.setText((String)dataSnapshot.child("endYear").getValue());
                companyName.setText((String)dataSnapshot.child("company_name").getValue());
                position.setText((String)dataSnapshot.child("job_position").getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       // Toast.makeText(EditExperienceActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();







    }
}
