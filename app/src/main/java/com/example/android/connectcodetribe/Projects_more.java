package com.example.android.connectcodetribe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.android.connectcodetribe.Adapters.ProjectsHorizontalAdapter;
import com.example.android.connectcodetribe.Model.Project;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Projects_more extends AppCompatActivity {

    Button bnAddProject;
    FirebaseDatabase database;
    RecyclerView mProjectsRecyclerView;
    ProjectsHorizontalAdapter mProjectsAdapter;
    List<Project> projects = new ArrayList<>();
    private boolean expandable = true;

    DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_more);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("testing").child("users").child("codetribe").child("Soweto").child("0");

        bnAddProject = (Button) findViewById(R.id.bnAddproject);



        bnAddProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Projects_more.this, ProjectsActivity.class);
                startActivity(intent);



                mProjectsRecyclerView = (RecyclerView) findViewById(R.id.moreprojectsRecyclerview);

                LinearLayoutManager horizontalLayoutManager
                        = new LinearLayoutManager(Projects_more.this, LinearLayoutManager.VERTICAL, false);
                mProjectsRecyclerView.setLayoutManager(horizontalLayoutManager);
                mProjectsAdapter = new ProjectsHorizontalAdapter(Projects_more.this, projects);
                mProjectsRecyclerView.setAdapter(mProjectsAdapter);
            }




        });

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                projects.clear();
                for (DataSnapshot snapshot : dataSnapshot.child("projects").getChildren()) {
                    Project project = new Project();
                    project.setSnapshot((String) snapshot.child("snapshot").getValue());
                    project.setName((String) snapshot.child("name").getValue());
                    project.setGithub_link((String) snapshot.child("github_link").getValue());
                    System.out.println(project.toMap());
                    projects.add(project);}
//                    mProjectsAdapter.notifyDataSetChanged();

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}

