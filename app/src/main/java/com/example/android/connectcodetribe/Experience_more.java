package com.example.android.connectcodetribe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Experience_more extends AppCompatActivity {

    Button bNAddexperience;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_more);

        bNAddexperience = (Button) findViewById(R.id.bnAddexperience);

        bNAddexperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Experience_more.this,ProjectsActivity.class);
                startActivity(intent);
            }
        });
    }
}

