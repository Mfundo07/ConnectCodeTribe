package com.example.android.connectcodetribe;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.connectcodetribe.Model.Experience;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    FirebaseUser currentUser;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_input_experience);

        startYear =  (EditText)findViewById(R.id.startYearEditText);
        endYear =(EditText)findViewById(R.id.endYearEditText);
        companyName =(EditText)findViewById(R.id.companyNameEditText);
        position = (EditText)findViewById(R.id.positionEditText);
        addBtn = (Button)findViewById(R.id.Addbtn);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("testing").child("users").child("codetribe").child("Soweto").child(currentUser.getUid()).child("experience");



        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Experience item = new Experience();
                item.setStartYear(startYear.getText().toString());
                item.setEndYear(endYear.getText().toString());
                item.setPosition(position.getText().toString());
                item.setCompanyName(companyName.getText().toString());
                myRef.setValue(item.toMap()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(EditExperienceActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                });

            }
        });


       // Toast.makeText(EditExperienceActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();







    }
}
