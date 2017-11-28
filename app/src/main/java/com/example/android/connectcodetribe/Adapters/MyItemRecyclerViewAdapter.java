package com.example.android.connectcodetribe.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.connectcodetribe.Model.TribeMate;
import com.example.android.connectcodetribe.R;
import com.example.android.connectcodetribe.ScrollingFragment;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private List<TribeMate> mTribeMates;
    private Activity activity;
    private Context context;


    public MyItemRecyclerViewAdapter(Activity activity, List<TribeMate> mTribeMates) {
        this.activity = activity;
        this.mTribeMates = mTribeMates;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mTribeMates.get(position);
        holder.mIdView.setText(mTribeMates.get(position).getName());
        holder.mContentView.setText(mTribeMates.get(position).getSurname());
        holder.mStatus.setText(mTribeMates.get(position).getStatus());
        holder.mIntakeYear.setText(mTribeMates.get(position).getIntakeYear());
        holder.mCodeTribe.setText(mTribeMates.get(position).getCodeTribe());
        Glide.with(holder.mCircleImageView.getContext())
                .load(mTribeMates.get(position).getProfileImage())
                .into(holder.mCircleImageView);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(activity, ScrollingFragment.class);
                intent.putExtra("Name", mTribeMates.get(position).getName());
                intent.putExtra("Surname", mTribeMates.get(position).getSurname());
                intent.putExtra("CodeTribe", mTribeMates.get(position).getCodeTribe());
                intent.putExtra("Status", mTribeMates.get(position).getStatus());
                intent.putExtra("Email", mTribeMates.get(position).getEmail());

                intent.putExtra("Employee_code", mTribeMates.get(position).getEMC());
                intent.putExtra("Gender", mTribeMates.get(position).getGender());
                intent.putExtra("Ethnicity", mTribeMates.get(position).getEthnicity());
                intent.putExtra("Age", String.valueOf(mTribeMates.get(position).getAge()));
                intent.putExtra("Email", mTribeMates.get(position).getEmail());
                intent.putExtra("Mobile", mTribeMates.get(position).getMobile());
                intent.putExtra("image", mTribeMates.get(position).getProfileImage());
                intent.putExtra("bio", mTribeMates.get(position).getBio());
                intent.putExtra("company_name",mTribeMates.get(position).getCompanyName());
                intent.putExtra("employed_year", mTribeMates.get(position).getIntakeYear());
                intent.putExtra("company_contact", mTribeMates.get(position).getCompanyContactNumber());
                intent.putExtra("employment_status", mTribeMates.get(position).getEmploymentStatus());
                intent.putExtra("salary", mTribeMates.get(position).getSalary());
                context = view.getContext();
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return mTribeMates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mStatus;
        public TribeMate mItem;
        public TextView mIntakeYear;
        public TextView mCodeTribe;
        public CircleImageView mCircleImageView;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.User_Surname);
            mContentView = view.findViewById(R.id.User_Name);
            mStatus = view.findViewById(R.id.User_Status);
            mIntakeYear = view.findViewById(R.id.intake_year_text);
            mCodeTribe = view.findViewById(R.id.soweto_orange);
            mCircleImageView = view.findViewById(R.id.profile_image);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + " '";
        }
    }
}
