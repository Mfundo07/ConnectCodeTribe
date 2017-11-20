package com.example.android.connectcodetribe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.connectcodetribe.Adapters.ProjectsHorizontalAdapter;
import com.example.android.connectcodetribe.Model.Project;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

public class Projects_more extends AppCompatActivity {

    List<Project> project_list = new ArrayList<>();
    ProjectsHorizontalAdapter mProjectsAdapter;
    private DataSnapshot dataSnapshot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_more);

        mProjectsAdapter = new ProjectsHorizontalAdapter(Projects_more.this, project_list);


        project_list.clear();
        for (DataSnapshot snapshot : dataSnapshot.child("projects").getChildren()) {
            Project project = new Project();
            project.setSnapshot((String) snapshot.child("snapshot").getValue());
            project.setName((String) snapshot.child("name").getValue());
            project.setGithub_link((String) snapshot.child("github_link").getValue());
            System.out.println(project.toMap());
            project_list.add(project);
        }
        mProjectsAdapter.notifyDataSetChanged();

        }
    }
