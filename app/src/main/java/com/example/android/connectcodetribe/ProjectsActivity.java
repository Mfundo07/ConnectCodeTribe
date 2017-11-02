package com.example.android.connectcodetribe;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class ProjectsActivity extends AppCompatActivity {

    private ImageButton ProjectImage;
    private static final int GALLERY_REQUEST = 1;

    private static final int CAMERA_REQUEST_CODE  = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewing_project_feature_layout);


        ProjectImage = (ImageButton) findViewById(R.id.ProjectImage);


        ProjectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, CAMERA_REQUEST_CODE);
                }
                startActivityForResult(intent,CAMERA_REQUEST_CODE);


                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);



            }
        });
    }
}
