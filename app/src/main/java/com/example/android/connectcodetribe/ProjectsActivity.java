package com.example.android.connectcodetribe;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ProjectsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewing_project_feature_layout);
    }

    public void Browser (View view){

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/"));
        startActivity(browserIntent);
    }
}
