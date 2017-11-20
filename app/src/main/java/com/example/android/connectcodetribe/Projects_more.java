package com.example.android.connectcodetribe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Projects_more extends AppCompatActivity {

    Button bnAddProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_more);

        bnAddProject = (Button) findViewById(R.id.bnAddproject);

        bnAddProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Projects_more.this,ProjectsActivity.class);
                startActivity(intent);
            }
        });
    }
}
