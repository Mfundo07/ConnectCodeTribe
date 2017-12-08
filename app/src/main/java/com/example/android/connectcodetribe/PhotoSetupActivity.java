package com.example.android.connectcodetribe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.connectcodetribe.Model.TribeMate;
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

/**
 * Created by Admin on 12/7/2017.
 */

public class PhotoSetupActivity extends AppCompatActivity{
    private ImageView mImageView;
    private Button mImageEditButton;
    private Button mImageSaveButton;
    private Uri filepath;
    String mCodeTribe;
    String mEMC;
    DatabaseReference mRef;
    StorageReference mStorageReference;
    private final int PICK_IMAGE_REQUEST = 71;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_setup);
        mImageView = findViewById(R.id.profile_setup_image);
        mImageEditButton = findViewById(R.id.profile_image_edit_setup_button);
        mImageSaveButton = findViewById(R.id.profile_image_save_setup_button);
        mCodeTribe = getIntent().getExtras().getString("CodeTribe");
        mEMC = getIntent().getExtras().getString("Employee_Code");
        mStorageReference = FirebaseStorage.getInstance().getReference("/requested/").child("images");
        mRef = FirebaseDatabase.getInstance().getReference("/requested/").child("images");
        mImageEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseProjectImage();
            }
        });

        mImageSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadProjectImage();
            }
        });


    }
    private void uploadProjectImage() {
        if (filepath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading Image.....");
            progressDialog.show();

            StorageReference ref = mStorageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filepath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadUri = taskSnapshot.getDownloadUrl();
                            TribeMate mate = new TribeMate();
                            mate.setProfileImage(downloadUri.toString());
                            mRef.child(mCodeTribe).child(mEMC).setValue(mate.toMap());

                            progressDialog.dismiss();
                            Toast.makeText(PhotoSetupActivity.this, "Image Upload Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(PhotoSetupActivity.this, DifferentCodetribeTabs.class));



                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(PhotoSetupActivity.this, "Project Upload Unsuccessful"+e.getMessage() , Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }

    }

    private void chooseProjectImage() {
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
                mImageView.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


}
