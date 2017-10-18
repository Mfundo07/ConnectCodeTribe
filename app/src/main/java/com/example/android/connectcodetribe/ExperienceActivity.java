package com.example.android.connectcodetribe;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class ExperienceActivity extends AppCompatActivity {

    Button addExperience;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.model);

        addExperience = (Button) findViewById(R.id.fab_add);

        addExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(ExperienceActivity.this);
                LayoutInflater inflater = ExperienceActivity.this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.input_dialog,null);
                Button updateInfo = dialogView.findViewById(R.id.update);


                updateInfo.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                    }

                });
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }


        });
    }
}
