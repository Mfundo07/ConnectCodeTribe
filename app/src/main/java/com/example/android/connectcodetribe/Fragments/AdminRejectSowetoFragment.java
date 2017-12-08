package com.example.android.connectcodetribe.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.connectcodetribe.Adapters.AcceptedUsersAdapter;
import com.example.android.connectcodetribe.Model.Project;
import com.example.android.connectcodetribe.Model.TribeMate;
import com.example.android.connectcodetribe.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Admin on 12/8/2017.
 */

public class AdminRejectSowetoFragment extends Fragment {
    // TODO: Customize parameter argument names
    private static final String ARG_PROFILE_ID = "profile_id";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    DatabaseReference mDatabaseReference;

    List<TribeMate> mTribeMates = new ArrayList<>();
    List<Project> mProjects = new ArrayList<>();
    FirebaseUser mAuth;

    AcceptedUsersAdapter adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AdminRejectSowetoFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static AdminRequestSowetoFragment newInstance(UUID profileId) {
        AdminRequestSowetoFragment fragment = new AdminRequestSowetoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PROFILE_ID, profileId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            UUID profileId = (UUID) getArguments().getSerializable(ARG_PROFILE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_recycler_view, container, false);

        mAuth = FirebaseAuth.getInstance().getCurrentUser();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("/rejected/").child("Soweto");


        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            adapter = new AcceptedUsersAdapter(getActivity(), mTribeMates);
            recyclerView.setAdapter(adapter);

            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChildren()) {
                        mTribeMates.clear();
                        mProjects.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            TribeMate user = new TribeMate();
                            Project project = new Project();
                            user.setName((String) snapshot.child("name").getValue());
                            user.setSurname((String) snapshot.child("surname").getValue());
                            user.setAge(Long.valueOf( snapshot.child("age").getValue().toString()));
                            user.setEMC((String) snapshot.child("employeeCode").getValue());
                            user.setEthnicity((String) snapshot.child("ethnicity").getValue());
                            user.setGender((String) snapshot.child("gender").getValue());
                            user.setStatus((String) snapshot.child("status").getValue());
                            user.setCodeTribe((String) snapshot.child("codeTribeLocation").getValue());
                            user.setEmail((String) snapshot.child("emailAddress").getValue());
                            user.setMobile((String) snapshot.child("mobileNo").getValue());
                            user.setInstitute((String) snapshot.child("qualificationInstitution").getValue());
                            user.setDesc((String) snapshot.child("qualificationDescription").getValue());
                            user.setQualification((String) snapshot.child("highestQualification").getValue());
                            if ((String) snapshot.child("profile_picture").getValue() != null){
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
                            mProjects.add(project);
                            mTribeMates.add(user);
                        }
                        if (mTribeMates.size() > 0) {
                            adapter.notifyDataSetChanged();
                        } else {
                            System.out.println("No active users found");
                        }
                        if (mProjects.size() > 0) {
                            adapter.notifyDataSetChanged();
                        } else {
                            System.out.println("No active users found");
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        return view;
    }

}
