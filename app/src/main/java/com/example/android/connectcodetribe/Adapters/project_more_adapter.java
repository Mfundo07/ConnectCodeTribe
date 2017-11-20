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

import java.util.List;

/**
 * Created by Admin on 11/20/2017.
 */

public class project_more_adapter extends RecyclerView.Adapter<project_more_adapter.ViewHolder> {

    private List<Project> mProjects;
    private Activity activity;


    public project_more_adapter(Activity activity, List<Project> mProjects) {
        this.activity = activity;
        this.mProjects = mProjects;
    }


    @Override
    public project_more_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.project_item_layout, parent, false);
        return new project_more_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final project_more_adapter.ViewHolder holder, final int position) {
        holder.mItem = mProjects.get(position);
        holder.mTitle.setText(mProjects.get(position).getName());
        Glide.with(activity)
                .load(mProjects.get(position).getSnapshot())
                .into(holder.projectDisplayPicture);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open project
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(mProjects.get(position).getGithub_link()));
                activity.startActivity(intent);
            }
        });
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