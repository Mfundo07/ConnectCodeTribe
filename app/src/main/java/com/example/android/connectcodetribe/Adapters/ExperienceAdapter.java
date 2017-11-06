package com.example.android.connectcodetribe.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.connectcodetribe.Model.Experience;
import com.example.android.connectcodetribe.R;

import java.util.List;

public class ExperienceAdapter extends RecyclerView.Adapter<ExperienceAdapter.ViewHolder> {
    private List<Experience> mExperiences;
    private Activity mActivity;

    @Override
    public ExperienceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model, parent, false);
        return new ViewHolder(view);
    }

    public ExperienceAdapter(List<Experience> experiences, Activity activity) {
        mExperiences = experiences;
        mActivity = activity;
    }

    @Override
    public void onBindViewHolder(final ExperienceAdapter.ViewHolder holder, int position) {
        holder.mItem = mExperiences.get(position);
        holder.companyName.setText(mExperiences.get(position).getCompanyName());
        holder.positionName.setText(mExperiences.get(position).getPosition());
        holder.workingPeriod.setText(mExperiences.get(position).getStartYear() + "  - " +
                mExperiences.get(position).getEndYear());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            EditText startYear,companyName, jobPosition, endYear;
            @Override
            public void onClick(View view) {


            }

        });





    }

    @Override
    public int getItemCount() {
        return mExperiences.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public final View mView;
        public Experience mItem;
        public TextView companyName;
        public TextView positionName;
        public TextView workingPeriod;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            companyName = itemView.findViewById(R.id.companynameTxt);
            positionName = itemView.findViewById(R.id.positionTxt);
            workingPeriod = itemView.findViewById(R.id.periodTxt);


        }
    }
}