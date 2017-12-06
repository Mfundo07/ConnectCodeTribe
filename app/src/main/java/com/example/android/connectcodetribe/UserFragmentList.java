package com.example.android.connectcodetribe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.connectcodetribe.Adapters.AcceptedUsersAdapter;
import com.example.android.connectcodetribe.Model.Project;
import com.example.android.connectcodetribe.Model.TribeMate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 12/1/2017.
 */

public class UserFragmentList extends Fragment {
    private RecyclerView mRecyclerView;
    private AcceptedUsersAdapter mAdapter;
    List<TribeMate> mTribeMates;
    DatabaseReference mDatabaseReference;

    public UserFragmentList() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_admin_request_list, container, false);
        mRecyclerView = view.findViewById(R.id.request_list);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("/users/");
        mTribeMates = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new AcceptedUsersAdapter(getActivity(), mTribeMates);
        mRecyclerView.setAdapter(mAdapter);
       mDatabaseReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               if (dataSnapshot.hasChildren()) {
                   mTribeMates.clear();
                   for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                       TribeMate user = new TribeMate();
                       Project project = new Project();
                       user.setName((String) snapshot.child("name").getValue());
                       user.setSurname((String) snapshot.child("surname").getValue());
                       user.setAge(Long.valueOf(snapshot.child("age").getValue().toString()));
                       user.setEMC((String) snapshot.child("employeeCode").getValue());
                       user.setEthnicity((String) snapshot.child("ethnicity").getValue());
                       user.setGender((String) snapshot.child("gender").getValue());
                       user.setStatus((String) snapshot.child("status").getValue());
                       user.setCodeTribe((String) snapshot.child("codeTribeLocation").getValue());
                       user.setEmail((String) snapshot.child("emailAddress").getValue());
                       user.setMobile((String) snapshot.child("mobileNo").getValue());
                       if (snapshot.child("profile_picture").getValue() != null){
                           user.setProfileImage((String) snapshot.child("profile_picture").getValue());}
                       user.setBio((String) snapshot.child("bio").getValue());
                       user.setEmploymentStatus((String) snapshot.child("employed").getValue());
                       user.setSalary((String) snapshot.child("monthlySalary(ZAR)").getValue());
                       user.setCompanyContactNumber((String) snapshot.child("companyContactDetails").getValue());
                       user.setStartDate((String) snapshot.child("startDate").getValue());
                       user.setTribeEmploymentCodeUnderline((String) snapshot.child("employeeCode").getValue());
                       user.setTribeUnderline((String) snapshot.child("tribe_underline").getValue());
                       user.setCompanyName((String) snapshot.child("companyName").getValue());
                       for (DataSnapshot projectSnapshot: snapshot.child("projects").getChildren()){
                           project.setGithub_link((String) projectSnapshot.child("github_link").getValue());
                           project.setName((String) projectSnapshot.child("name").getValue());
                       }

                       mTribeMates.add(user);
                   }
                   if (mTribeMates.size() > 0) {
                       mAdapter.notifyDataSetChanged();
                   } else {
                       System.out.println("No active users found");
                   }

               }
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });

        return view;
    }

}
