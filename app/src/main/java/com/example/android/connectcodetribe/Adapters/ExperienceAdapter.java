package com.example.android.connectcodetribe.Adapters;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.connectcodetribe.Model.Experience;
import com.example.android.connectcodetribe.R;
import com.example.android.connectcodetribe.profile.ProfileActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

                final AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
                LayoutInflater inflater = LayoutInflater.from(holder.itemView.getContext());
                View dialogView = inflater.inflate(R.layout.input_dialog, null);
                startYear = dialogView.findViewById(R.id.startYearEditText);
                companyName = dialogView.findViewById(R.id.companyNameEditText);
                jobPosition = dialogView.findViewById(R.id.positionEditText);
                endYear = dialogView.findViewById(R.id.endYearEditText);
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    FirebaseDatabase database;
                    DatabaseReference myRef;
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        database = FirebaseDatabase.getInstance();
                        myRef = database.getReference("testing").child("users").child("codetribe").child("Soweto").child("0").child("experience").child("0");
                        Experience item = new Experience();
                        item.setStartYear(startYear.getText().toString());
                        item.setEndYear(endYear.getText().toString());
                        item.setPosition(jobPosition.getText().toString());
                        item.setCompanyName(companyName.getText().toString());
                        myRef.setValue(item.toMap());


                        myRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                startYear.setText((String) dataSnapshot.child("startYear").getValue());
                                endYear.setText((String)dataSnapshot.child("endYear").getValue());
                                jobPosition.setText((String) dataSnapshot.child("job_position").getValue());
                                companyName.setText((String) dataSnapshot.child("company_name").getValue());
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        Toast.makeText(mActivity, "Data Updated", Toast.LENGTH_SHORT).show();
                        dialogInterface.cancel();

                    }
                }).setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                    }
                });




                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();


            }

        });
        ((ProfileActivity)mActivity).addBottomDots(position);


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