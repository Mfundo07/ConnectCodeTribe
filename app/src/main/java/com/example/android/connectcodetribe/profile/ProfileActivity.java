package com.example.android.connectcodetribe.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.connectcodetribe.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;

    TextView mBio, mStatus, mCodeTribe, userName, userDescription;
    ImageButton btnStatus, btnGithubLink,btnCodeTribe;
    RecyclerView mSkills, mProjects, mExperience;
    ImageView userImage;
    public String gihubLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("testing").child("users").child("codetribe").child("Soweto").child("0");
        mBio = (TextView) findViewById(R.id.userBio);
        mStatus = (TextView) findViewById(R.id.userStatus);
        mCodeTribe = (TextView) findViewById(R.id.userCodeTribeName);
        btnCodeTribe = (ImageButton) findViewById(R.id.userCodeTribeImage);
        btnGithubLink = (ImageButton) findViewById(R.id.userGithubImage);
        btnStatus = (ImageButton) findViewById(R.id.userStatusImage);
        userName = (TextView) findViewById(R.id.userName);
        userDescription = (TextView) findViewById(R.id.userDescribe);
        userImage = (ImageView) findViewById(R.id.userImage);


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                mBio.setText((String) dataSnapshot.child("bio").getValue());
                mStatus.setText((String) dataSnapshot.child("status").getValue());
                Glide.with(userImage.getContext())
                        .load((String) dataSnapshot.child("display_picture").getValue())
                        .into(userImage);
                userDescription.setText((String) dataSnapshot.child("three_words").getValue());
                userName.setText((String) dataSnapshot.child("name").getValue());
                gihubLink = (String) dataSnapshot.child("github_link").getValue();
                btnGithubLink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setData(Uri.parse(gihubLink));
                        startActivity(intent);
                    }
                });







            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

}
