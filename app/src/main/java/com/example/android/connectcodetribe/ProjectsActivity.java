package com.example.android.connectcodetribe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.android.connectcodetribe.Model.Project;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class ProjectsActivity extends AppCompatActivity {

    private ImageButton ProjectImage;
    private Button ButUpload;
    private Uri filepath;
    private final int PICK_IMAGE_REQUEST = 71;
    DatabaseReference mRef;
    private EditText mProjectTitle, mProjectLink;

    FirebaseStorage storage;
    StorageReference storageReference;

    private static final int GALLERY_REQUEST = 1;

    private static final int CAMERA_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewing_project_feature_layout);


        //Firebase Init
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();






        //Init view
        ProjectImage = (ImageButton) findViewById(R.id.ProjectImage);
        ButUpload = (Button) findViewById(R.id.ButUpload);
        mRef = FirebaseDatabase.getInstance().getReference().child("/projects/");
        mProjectTitle = (EditText) findViewById(R.id.post_Title);
        mProjectLink = (EditText) findViewById(R.id.post_Desc);

        ProjectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();


            }

        });
        ButUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();

            }
        });

    }

    private void uploadImage() {
        if (filepath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading Project.....");
            progressDialog.show();

            StorageReference ref = storageReference.child("projects/"+ UUID.randomUUID().toString());
            ref.putFile(filepath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadUri = taskSnapshot.getDownloadUrl();
                            Project item = new Project();
                            item.setProjectDisplayPicture(downloadUri.toString());
                            item.setProjectTitle(mProjectTitle.getText().toString());
                            item.setProjectUrl(mProjectLink.getText().toString());
                            mRef.push().setValue(item);

                            progressDialog.dismiss();
                            Toast.makeText(ProjectsActivity.this, "Project Upload Successful", Toast.LENGTH_SHORT).show();
                            ProjectImage.setImageResource(R.drawable.ic_photocamera);
                            mProjectTitle.setText("");
                            mProjectLink.setText("");
                            finish();



                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(ProjectsActivity.this, "Project Upload Unsuccessful"+e.getMessage() , Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded"+(int)progress+"%");
                        }
                    });
        }

    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null)
        {
            filepath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                ProjectImage.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
               e.printStackTrace();
            }
        }
    }
}





