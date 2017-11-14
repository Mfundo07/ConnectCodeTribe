package com.example.android.connectcodetribe.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.connectcodetribe.Model.Profile;
import com.example.android.connectcodetribe.R;
import com.example.android.connectcodetribe.ScrollingFragment;

import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private List<Profile> mProfiles;
    private Activity activity;
    private Context context;
    private Profile mProfile;


    public MyItemRecyclerViewAdapter(Activity activity, List<Profile> mProfiles) {
        this.activity = activity;
        this.mProfiles = mProfiles;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mProfiles.get(position);
        holder.mIdView.setText(mProfiles.get(position).getProfileName());
        holder.mContentView.setText(mProfiles.get(position).getProfileSurname());
        holder.mStatus.setText(mProfiles.get(position).getStatus());
        holder.mIntakeYear.setText(mProfiles.get(position).getIntakeYear());
        Glide.with(holder.profileImage.getContext())
                .load(mProfiles.get(position).getProfileImage())
                .into(holder.profileImage);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ScrollingFragment.class);
                context = view.getContext();
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return mProfiles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mStatus;
        public Profile mItem;
        public ImageView profileImage;
        public TextView mIntakeYear;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.User_Surname);
            mContentView = view.findViewById(R.id.User_Name);
            mStatus = view.findViewById(R.id.User_Status);
            profileImage = view.findViewById(R.id.profile_image);
            mIntakeYear = view.findViewById(R.id.intake_year_text);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
