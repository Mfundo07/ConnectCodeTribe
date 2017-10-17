package com.example.android.connectcodetribe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.connectcodetribe.Model.ActiveUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    FirebaseDatabase mFirebaseDatabase;
    FirebaseStorage mFirebaseStorage;
    DatabaseReference mDatabaseReference;
    StorageReference mStorageReference;
    private TextView profileName;
    private TextView profileSurname;
    private TextView profilePhoneNumber;
    private TextView profileEmail;
    private TextView profileStatus;
    private TextView profileOccupation;
    ChildEventListener mChildEventListener;
    CircleImageView profileImage;
    FirebaseUser mAuth;

    CardView cardBio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        cardBio = (CardView) findViewById(R.id.cardView_bio);
        cardBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, UserProfileEditorActivity.class);
                startActivity(intent);
            }
        });

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseStorage = FirebaseStorage.getInstance();
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child(mAuth.getDisplayName().toString());
        mStorageReference = mFirebaseStorage.getReference().child("verified_user_profile_photos");
        profileName = (TextView) findViewById(R.id.profile_name);
        profileSurname = (TextView) findViewById(R.id.profile_surname);
        profilePhoneNumber = (TextView) findViewById(R.id.profile_cellNumber);
        profileEmail = (TextView) findViewById(R.id.profile_email);
        profileStatus = (TextView) findViewById(R.id.profile_status);
        profileOccupation = (TextView) findViewById(R.id.profile_occupation);
        profileImage = (CircleImageView) findViewById(R.id.profile_image);
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ActiveUser activeUser = dataSnapshot.getValue(ActiveUser.class);
                profileName.setText(activeUser.getActiveUserName());
                profileSurname.setText(activeUser.getActiveUserSurname());
                profilePhoneNumber.setText(activeUser.getActiveUserNumber());
                profileEmail.setText(activeUser.getActiveUserEmail());
                profileStatus.setText(activeUser.getActiveUserStatus());
                profileOccupation.setText(activeUser.getActiveUserOccupation());
                boolean isPhoto = activeUser.getActiveUserImageUrl() !=null;
                if (isPhoto){
                    Glide.with(profileImage.getContext())
                            .load(mAuth.getPhotoUrl())
                            .into(profileImage);
                }


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {



            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabaseReference.addChildEventListener(mChildEventListener);
    }
}
