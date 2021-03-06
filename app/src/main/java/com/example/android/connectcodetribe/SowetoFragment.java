package com.example.android.connectcodetribe;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.connectcodetribe.Adapters.AcceptedUsersAdapter;
import com.example.android.connectcodetribe.Model.Profile;
import com.example.android.connectcodetribe.Model.TribeMate;
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

public class SowetoFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_PROFILE_ID = "profile_id";
    // TODO: Customize parameters
    private Profile mProfile;
    private int mColumnCount = 1;

    DatabaseReference mDatabaseReference;

    List<TribeMate> mTribeMates = new ArrayList<>();
    FirebaseUser mAuth;

    AcceptedUsersAdapter adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SowetoFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static SowetoFragment newInstance(UUID profileId) {
        SowetoFragment fragment = new SowetoFragment();
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
        View view = inflater.inflate(R.layout.welcome_slide2, container, false);

        mAuth = FirebaseAuth.getInstance().getCurrentUser();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("/convert/");


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
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            TribeMate user = new TribeMate();
                            user.setName((String) snapshot.child("name").getValue());
                            user.setSurname((String) snapshot.child("surname").getValue());
                            user.setAge(Long.valueOf(snapshot.child("age").getValue().toString()));
                            user.setEMC((String) snapshot.child("employeeCode").getValue());
                            user.setEthnicity((String) snapshot.child("ethnicity").getValue());
                            user.setGender((String) snapshot.child("gender").getValue());
                            user.setIntakeYear((String) snapshot.child("inTakePeriod").getValue());
                            user.setStatus((String) snapshot.child("status").getValue());
                            user.setCodeTribe((String) snapshot.child("codeTribeLocation").getValue());

                            user.setEmail((String) snapshot.child("emailAddress").getValue());
                            user.setMobile((String) snapshot.child("mobileNumber").getValue());

                            mTribeMates.add(user);
                        }
                        if (mTribeMates.size() > 0) {
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
