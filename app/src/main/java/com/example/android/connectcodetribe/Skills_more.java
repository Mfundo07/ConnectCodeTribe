package com.example.android.connectcodetribe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Skills_more extends AppCompatActivity {

    Button bnAddSkill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills_more);

        bnAddSkill = (Button) findViewById(R.id.bnAddSkill);

        bnAddSkill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Skills_more.this,ProjectsActivity.class);
                startActivity(intent);
            }
        });
    }
}
