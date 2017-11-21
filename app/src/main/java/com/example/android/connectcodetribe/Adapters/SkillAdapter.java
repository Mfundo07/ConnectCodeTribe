package com.example.android.connectcodetribe.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.connectcodetribe.Model.Project;
import com.example.android.connectcodetribe.Model.Skill;
import com.example.android.connectcodetribe.R;

import java.util.List;

/**
 * Created by Admin on 10/23/2017.
 */

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.ViewHolder> {
    private List<Project> mSkillImage;

    private List<Skill> mSkills;
    private Activity activity;


    public SkillAdapter(Activity activity, List<Skill> mSkills) {
        this.activity = activity;
        this.mSkills = mSkills;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.skills_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mSkills.get(position);
        holder.mTitle.setText(mSkills.get(position).getTitle());
        //holder.mEndorsements.setText(mSkills.get(position).getSkillLevel() + activity.getString(R.string.endorsed));



        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open project
            }
        });

    }

    @Override
    public int getItemCount() {
        return mSkills.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitle;
        public Skill mItem;
        public TextView mEndorsements;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitle = view.findViewById(R.id.skills_title_text_view);
            mEndorsements = view.findViewById(R.id.endorse_text_view);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitle.getText() + "'";
        }
    }
}
