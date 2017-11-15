package com.example.android.connectcodetribe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.connectcodetribe.profile.ProfileActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Skill_Editor_Activity extends AppCompatActivity {

    EditText editText;
    Button submit;
    DatabaseReference rootRef,demoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill__editor_);


        editText = (EditText) findViewById(R.id.etValue);
        submit = (Button) findViewById(R.id.btnSubmit);
        //database reference pointing to root of database
        rootRef = FirebaseDatabase.getInstance().getReference();
        //database reference pointing to demo node
        demoRef = rootRef.child("skills");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = editText.getText().toString();
                //push creates a unique id in database
                demoRef.push().setValue(value);
                Intent intent =new Intent(Skill_Editor_Activity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
