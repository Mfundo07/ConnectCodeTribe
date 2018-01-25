package com.example.android.connectcodetribe.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.connectcodetribe.DifferentCodetribeTabs;
import com.example.android.connectcodetribe.Model.TribeMate;
import com.example.android.connectcodetribe.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by Admin on 1/19/2018.
 */

public class PhotoSetupFragment extends Fragment {
    private ImageView mImageView;
    private Button mImageEditButton;
    private Button mImageSaveButton;
    private Uri filepath;
    FirebaseUser mAuth;
    String mCodeTribe;
    String mEMC;
    DatabaseReference mRef;
    StorageReference mStorageReference;
    private final int PICK_IMAGE_REQUEST = 71;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.welcome_slide4, container, false);

        mImageView = rootView.findViewById(R.id.profile_setup_image);
        mImageEditButton = rootView.findViewById(R.id.profile_image_edit_setup_button);
        mImageSaveButton = rootView.findViewById(R.id.profile_image_save_setup_button);
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
        //mCodeTribe = getActivity().getIntent().getExtras().getString("CodeTribe");
        //mEMC = getActivity().getIntent().getExtras().getString("Employee_Code");
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
        return rootView;

    }
    private void uploadProjectImage() {
        if (filepath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
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
                            mRef.child("Soweto").child(mAuth.getEmail()).setValue(mate.toMap());

                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Image Upload Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getActivity(), DifferentCodetribeTabs.class));



                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Project Upload Unsuccessful"+e.getMessage() , Toast.LENGTH_SHORT).show();

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
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);

        if (requestCode == PICK_IMAGE_REQUEST
                && intent != null && intent.getData() != null)
        {
            filepath = intent.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filepath);
                mImageView.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
