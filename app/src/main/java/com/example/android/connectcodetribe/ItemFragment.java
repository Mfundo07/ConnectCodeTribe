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

import com.example.android.connectcodetribe.Adapters.MyItemRecyclerViewAdapter;
import com.example.android.connectcodetribe.Model.Profile;
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

public class ItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_PROFILE_ID = "profile_id";
    // TODO: Customize parameters
    private Profile mProfile;
    private int mColumnCount = 1;

    DatabaseReference mDatabaseReference;

    List<Profile> mProfiles = new ArrayList<>();
    FirebaseUser mAuth;

    MyItemRecyclerViewAdapter adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(UUID profileId) {
        ItemFragment fragment = new ItemFragment();
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
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        mAuth = FirebaseAuth.getInstance().getCurrentUser();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("/users/");


        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            adapter = new MyItemRecyclerViewAdapter(getActivity(), mProfiles);
            recyclerView.setAdapter(adapter);

            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChildren()) {
                        mProfiles.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Profile user = new Profile();
                            user.setProfileName((String) snapshot.child("activeUserName").getValue());
                            user.setProfileSurname((String) snapshot.child("activeUserSurname").getValue());
                            user.setStatus((String) snapshot.child("activeUserStatus").getValue());
                            user.setProfileImage((String) snapshot.child("activeUserImageUrl").getValue());
                            mProfiles.add(user);
                        }
                        if (mProfiles.size() > 0) {
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
