package com.example.android.connectcodetribe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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

public class Skills_more extends AppCompatActivity {

    Button bnAddSkill;
    FirebaseDatabase database;
    RecyclerView mSkillsRecyclerView;
    ProjectsHorizontalAdapter mSkillAdapter;
    List<Project> skills = new ArrayList<>();
    private boolean expandable = true;

    DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills_more);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("testing").child("users").child("codetribe").child("Soweto").child("0");

        bnAddSkill = (Button) findViewById(R.id.bnAddSkill);



        bnAddSkill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Skills_more.this, ProjectsActivity.class);
                startActivity(intent);



                mSkillsRecyclerView = (RecyclerView) findViewById(R.id.moreskillRecyclerview);

                LinearLayoutManager verticalLayoutmanager
                        = new LinearLayoutManager(Skills_more.this, StaggeredGridLayoutManager.VERTICAL, false);
                mSkillsRecyclerView.setLayoutManager(verticalLayoutmanager);
                mSkillAdapter = new ProjectsHorizontalAdapter(Skills_more.this, skills);
                mSkillsRecyclerView.setAdapter(mSkillAdapter);
            }




        });

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                skills.clear();
                for (DataSnapshot snapshot : dataSnapshot.child("skills").getChildren()) {
                    Project project = new Project();
                    project.setSnapshot((String) snapshot.child("snapshot").getValue());
                    project.setName((String) snapshot.child("name").getValue());
                    project.setGithub_link((String) snapshot.child("github_link").getValue());
                    System.out.println(project.toMap());
                    skills.add(project);}
//                    mProjectsAdapter.notifyDataSetChanged();

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
