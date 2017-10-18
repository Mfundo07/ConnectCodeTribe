package com.example.android.connectcodetribe.Adapters;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.connectcodetribe.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<String> mItemNames, mItemSuurname, mItemStatus;
    List<String> mItemOccupation, mProfileImage;
    private  Context context;
    private CircleImageView profileImage;
    private TextView nameTextView;
    private TextView surnameTextView;
    private TextView tribeTextView;
    private TextView statusTextView;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;
    private FirebaseDatabase mFirebaseDatabase;
    FirebaseStorage mFirebaseStorage;
    FirebaseUser mAuth;



    public MyItemRecyclerViewAdapter(List<String> mItemNames, List<String> mItemSuurname, List<String> mItemStatus,List<String>mItemOccupation, List<String> mProfileImage) {
        this.mItemNames = mItemNames;
        this.mItemSuurname = mItemSuurname;
        this.mItemStatus = mItemStatus;
        this.mItemOccupation = mItemOccupation;
        this.mProfileImage = mProfileImage;
    }

    public MyItemRecyclerViewAdapter(List<String> itemName, List<String> itemSurname, List<String> itemStatus) {
        mItemNames = itemName;
        mItemSuurname = itemSurname;
        mItemStatus = itemStatus;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseStorage = FirebaseStorage.getInstance();
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child(mAuth.getUid());
       mDatabaseReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               if (dataSnapshot.hasChildren()){
               mItemNames.clear();
               mItemSuurname.clear();
               mItemOccupation.clear();
               mItemStatus.clear();
               mProfileImage.clear();
               for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                   mItemNames.add((String) snapshot.child("activeUserName").getValue());
                   mItemSuurname.add((String) snapshot.child("activeUserSurname").getValue());
                   mItemStatus.add((String) snapshot.child("activeUserStatus").getValue());
                   mProfileImage.add((String) snapshot.child("activeUserPhotoUrl").getValue());
                   mItemOccupation.add((String) snapshot.child("activeUserOccupation").getValue());


               }}
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mItemNames.get(position);
        holder.mIdView.setText(mItemNames.get(position));
        holder.mContentView.setText(mItemSuurname.get(position));
        holder.mStatus.setText(mItemStatus.get(position));
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
                LayoutInflater inflater  = LayoutInflater.from(holder.itemView.getContext());
                View dialogView = inflater.inflate(R.layout.cardview_onclick_layout,null);
                profileImage = dialogView.findViewById(R.id.profile_image);
                nameTextView = dialogView.findViewById(R.id.name);
                surnameTextView = dialogView.findViewById(R.id.surname);
                tribeTextView = dialogView.findViewById(R.id.tribe);
                statusTextView = dialogView.findViewById(R.id.Status);
                boolean isPhoto = profileImage != null;
                if (isPhoto){
                    Glide.with(profileImage.getContext())
                            .load(mAuth.getPhotoUrl())
                            .into(profileImage);
                    nameTextView.setText(holder.mItem);
                    surnameTextView.setText(mItemSuurname.get(position));
                    statusTextView.setText(mItemStatus.get(position));
                    tribeTextView.setText(mItemOccupation.get(position));
                }

                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();





            }
        });

    }

    @Override
    public int getItemCount() {
        return mItemNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mStatus;
        public String mItem;
        public ImageView mProfileImage;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView =  view.findViewById(R.id.User_Surname);
            mContentView =  view.findViewById(R.id.User_Name);
            mStatus =  view.findViewById(R.id.User_Status);
            mProfileImage = view.findViewById(R.id.profile_image);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
