package com.example.android.connectcodetribe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity_first extends AppCompatActivity {
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
    private TextView tribeMember;
    CircleImageView profileImage;
    FirebaseUser mAuth;

    CardView cardBio;
    Button profileEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        cardBio = (CardView) findViewById(R.id.cardView_bio);
        profileEditor = (Button) findViewById(R.id.profile_edit_button);
        profileEditor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity_first.this, UserProfileEditorActivity.class);
                startActivity(intent);
            }
        });

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseStorage = FirebaseStorage.getInstance();
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("/users/").child(mAuth.getUid());
        mStorageReference = mFirebaseStorage.getReference().child("verified_user_profile_photos");
        profileName = (TextView) findViewById(R.id.profile_name);
        profileSurname = (TextView) findViewById(R.id.profile_surname);
        profilePhoneNumber = (TextView) findViewById(R.id.profile_cellNumber);
        profileEmail = (TextView) findViewById(R.id.profile_email);
        profileStatus = (TextView) findViewById(R.id.profile_status);
        profileOccupation = (TextView) findViewById(R.id.profile_occupation);
        profileImage = (CircleImageView) findViewById(R.id.profile_image);
        tribeMember = (TextView) findViewById(R.id.profile_tribe);
       mDatabaseReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               profileName.setText((String) dataSnapshot.child("activeUserName").getValue());
               profileSurname.setText((String) dataSnapshot.child("activeUserSurname").getValue());
               profileEmail.setText((String) dataSnapshot.child("activeUserEmail").getValue());
               profileStatus.setText((String) dataSnapshot.child("activeUserStatus").getValue());
               profileOccupation.setText((String) dataSnapshot.child("activeUserOccupation").getValue());
               profilePhoneNumber.setText((String) dataSnapshot.child("activeUserNumber").getValue());
               tribeMember.setText((String) dataSnapshot.child("activeUserTribe").getValue());
               Glide.with(profileImage.getContext())
                       .load((String) dataSnapshot.child("activeUserImageUrl").getValue())
                       .into(profileImage);

           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });
    }
}
