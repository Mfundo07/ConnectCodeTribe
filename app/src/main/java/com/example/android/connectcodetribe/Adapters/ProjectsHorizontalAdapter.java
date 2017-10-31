package com.example.android.connectcodetribe.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.connectcodetribe.Model.Project;
import com.example.android.connectcodetribe.R;
import com.example.android.connectcodetribe.profile.ProfileActivity;

import java.util.List;

/**
 * Created by Admin on 10/23/2017.
 */

public class ProjectsHorizontalAdapter extends RecyclerView.Adapter<ProjectsHorizontalAdapter.ViewHolder> {

    private List<Project> mProjects;
    private Activity activity;


    public ProjectsHorizontalAdapter(Activity activity, List<Project> mProjects) {
        this.activity = activity;
        this.mProjects = mProjects;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.project_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mProjects.get(position);
        holder.mTitle.setText(mProjects.get(position).getProjectTitle());
        Glide.with(activity)
                .load(mProjects.get(position).getProjectDisplayPicture())
                .into(holder.projectDisplayPicture);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open project
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(mProjects.get(position).getProjectUrl()));
                activity.startActivity(intent);
            }
        });
        ((ProfileActivity) activity).addBottomDots(position);
    }

    @Override
    public int getItemCount() {
        return mProjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitle;
        public Project mItem;
        public ImageView projectDisplayPicture;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitle = view.findViewById(R.id.project_title);
            projectDisplayPicture = view.findViewById(R.id.project_display_picture);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitle.getText() + "'";
        }
    }
}
