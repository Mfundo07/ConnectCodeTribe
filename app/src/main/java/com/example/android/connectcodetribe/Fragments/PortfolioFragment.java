package com.example.android.connectcodetribe.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.connectcodetribe.CompletedProfileActivity;
import com.example.android.connectcodetribe.ExperienceActivity;
import com.example.android.connectcodetribe.Model.ActiveUser;
import com.example.android.connectcodetribe.ProfileActivity;
import com.example.android.connectcodetribe.ProjectsActivity;
import com.example.android.connectcodetribe.QualificationActivity;
import com.example.android.connectcodetribe.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Admin on 10/4/2017.
 */

public class PortfolioFragment extends Fragment {
    private CardView codeTribeCardView, codeTribeCardView2,codeTribeCardView3, codeTribeCardView4,codeTribeCardView5;
    private CircleImageView profileImage;
    private TextView userName;
    private TextView userOccupation;
    DatabaseReference mReference;
    StorageReference mStorageReference;
    ChildEventListener mChildEventlistener;
    String activeUserTitle;
    String activeUserOccupation;
    String activeUserName;
    TextView userSurname;
    FirebaseUser mAuth;


    public PortfolioFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.user_portfolio,container, false);
        codeTribeCardView = rootView.findViewById(R.id.card1);
        profileImage = rootView.findViewById(R.id.profile_image);
        userName = rootView.findViewById(R.id.userName);
        userSurname = rootView.findViewById(R.id.userSurname);
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
        final CollapsingToolbarLayout collapsingToolbarLayout =  rootView.findViewById(R.id.collapse_toolBar);
        mReference = FirebaseDatabase.getInstance().getReference().child(mAuth.getUid());
        userOccupation = rootView.findViewById(R.id.userOccupation);
        codeTribeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);


            }
        });
        codeTribeCardView2 = rootView.findViewById(R.id.card2);
        codeTribeCardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), QualificationActivity.class);
                startActivity(intent);


            }
        });
        codeTribeCardView3 = rootView.findViewById(R.id.card3);
        codeTribeCardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ProjectsActivity.class);
                startActivity(intent);


            }
        });
        codeTribeCardView4 = rootView.findViewById(R.id.card4);

        codeTribeCardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ExperienceActivity.class);
                startActivity(intent);




            }
        });
        codeTribeCardView5 = rootView.findViewById(R.id.card5);
        codeTribeCardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CompletedProfileActivity.class);
                startActivity(intent);
            }
        });


       mChildEventlistener = new ChildEventListener() {
           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {

               ActiveUser activeUser = dataSnapshot.getValue(ActiveUser.class);
               activeUserTitle = activeUser.getActiveUserName();
               activeUserOccupation = activeUser.getActiveUserOccupation();
               userOccupation.setText(activeUserOccupation);
               collapsingToolbarLayout.setTitle(activeUserTitle);
               userName.setText(activeUserTitle);
               userSurname.setText(activeUser.getActiveUserSurname());




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
        mReference.addChildEventListener(mChildEventlistener);













        return rootView;
    }
}
