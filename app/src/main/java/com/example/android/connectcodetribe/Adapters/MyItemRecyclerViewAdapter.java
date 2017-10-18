package com.example.android.connectcodetribe.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.connectcodetribe.Model.ActiveUser;
import com.example.android.connectcodetribe.R;

import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private List<ActiveUser> mActiveUsers;
    private Activity activity;
    private Context context;


    public MyItemRecyclerViewAdapter(Activity activity, List<ActiveUser> mActiveUsers) {
        this.activity = activity;
        this.mActiveUsers = mActiveUsers;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mActiveUsers.get(position);
        holder.mIdView.setText(mActiveUsers.get(position).getActiveUserName());
        holder.mContentView.setText(mActiveUsers.get(position).getActiveUserSurname());
        holder.mStatus.setText(mActiveUsers.get(position).getActiveUserStatus());
        Glide.with(holder.profileImage.getContext())
                .load(mActiveUsers.get(position).getActiveUserImageUrl())
                .into(holder.profileImage);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
                LayoutInflater inflater = LayoutInflater.from(holder.itemView.getContext());
                View dialogView = inflater.inflate(R.layout.cardview_onclick_layout, null);
                boolean isPhoto = mActiveUsers.get(position).getActiveUserImageUrl() != null;
                if (isPhoto) {
                    Glide.with(activity)
                            .load(mActiveUsers.get(position).getActiveUserImageUrl())
                            .into((ImageView) dialogView.findViewById(R.id.profile_image));
                    ((TextView) dialogView.findViewById(R.id.name)).setText(mActiveUsers.get(position).getActiveUserName());
                    ((TextView) dialogView.findViewById(R.id.surname)).setText(mActiveUsers.get(position).getActiveUserSurname());
                    ((TextView) dialogView.findViewById(R.id.Status)).setText(mActiveUsers.get(position).getActiveUserStatus());
                    ((TextView) dialogView.findViewById(R.id.tribe)).setText(mActiveUsers.get(position).getActiveUserTribe());
                }

                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();


            }
        });

    }

    @Override
    public int getItemCount() {
        return mActiveUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mStatus;
        public ActiveUser mItem;
        public ImageView profileImage;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.User_Surname);
            mContentView = view.findViewById(R.id.User_Name);
            mStatus = view.findViewById(R.id.User_Status);
            profileImage = view.findViewById(R.id.profile_image);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
