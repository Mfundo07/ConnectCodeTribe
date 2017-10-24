package com.example.android.connectcodetribe.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.connectcodetribe.Adapters.ProjectsHorizontalAdapter;
import com.example.android.connectcodetribe.Adapters.SkillAdapter;
import com.example.android.connectcodetribe.Model.Project;
import com.example.android.connectcodetribe.Model.Skill;
import com.example.android.connectcodetribe.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;

    TextView mBio, mStatus, mCodeTribe, userName, userDescription;
    ImageButton btnStatus, btnGithubLink, btnCodeTribe;
    RecyclerView mSkills, mProjects, mExperience;
    ImageView userImage;
    public String gihubLink;

    RecyclerView mSkillsRecyclerView, mProjectsRecyclerView;

    ProjectsHorizontalAdapter mProjectsAdapter;
    SkillAdapter mSkillsAdapter;

    List<Project> projects = new ArrayList<>();
    List<Skill> skills = new ArrayList<>();

    private LinearLayout dotsLayout;
    private TextView[] dots;

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("");

        setSupportActionBar(toolbar);

        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);


        // adding bottom dots
        addBottomDots(0);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("testing").child("users").child("codetribe").child("Soweto").child("0");
        mBio = (TextView) findViewById(R.id.userBio);
        mStatus = (TextView) findViewById(R.id.userStatus);
        mCodeTribe = (TextView) findViewById(R.id.userCodeTribeName);
        btnCodeTribe = (ImageButton) findViewById(R.id.userCodeTribeImage);
        btnGithubLink = (ImageButton) findViewById(R.id.userGithubImage);
        btnStatus = (ImageButton) findViewById(R.id.userStatusImage);
        userName = (TextView) findViewById(R.id.userName);
        userDescription = (TextView) findViewById(R.id.userDescribe);
        userImage = (ImageView) findViewById(R.id.userImage);

        mProjectsRecyclerView = (RecyclerView) findViewById(R.id.projectsRecyclerview);
        //Setup layout manager to a horizontal scrolling recyclerView
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(ProfileActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mProjectsRecyclerView.setLayoutManager(horizontalLayoutManagaer);

        mProjectsAdapter = new ProjectsHorizontalAdapter(ProfileActivity.this, projects);
        mProjectsRecyclerView.setAdapter(mProjectsAdapter);

        mSkillsRecyclerView = (RecyclerView) findViewById(R.id.skillsRecyclerview);
        //Setup layout manager to a staggered scrolling recyclerView
        LinearLayoutManager verticalLayoutmanager
                = new LinearLayoutManager(ProfileActivity.this, StaggeredGridLayoutManager.VERTICAL, false);
        mSkillsRecyclerView.setLayoutManager(verticalLayoutmanager);

        mSkillsAdapter = new SkillAdapter(ProfileActivity.this, skills);
        mSkillsRecyclerView.setAdapter(mSkillsAdapter);


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                mBio.setText((String) dataSnapshot.child("bio").getValue());
                mStatus.setText((String) dataSnapshot.child("status").getValue());
                Glide.with(userImage.getContext())
                        .load((String) dataSnapshot.child("display_picture").getValue())
                        .into(userImage);
                userDescription.setText((String) dataSnapshot.child("three_words").getValue());
                userName.setText((String) dataSnapshot.child("name").getValue());
                toolbar.setTitle((String) dataSnapshot.child("name").getValue());
                gihubLink = (String) dataSnapshot.child("github_link").getValue();
                mCodeTribe.setText((String) dataSnapshot.child("codeTribe").getValue());
                btnGithubLink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setData(Uri.parse(gihubLink));
                        startActivity(intent);
                    }
                });

                projects.clear();
                for (DataSnapshot snapshot : dataSnapshot.child("projects").getChildren()) {
                    Project project = new Project();
                    project.setProjectDisplayPicture((String) snapshot.child("snapshot").getValue());
                    project.setProjectTitle((String) snapshot.child("name").getValue());
                    project.setProjectUrl((String) snapshot.child("github_link").getValue());
                    System.out.println(project.toMap());
                    projects.add(project);
                }

                mProjectsAdapter.notifyDataSetChanged();

                skills.clear();
                for (DataSnapshot _snapshot : dataSnapshot.child("skills").getChildren()) {
                    Skill skill = new Skill();
                    skill.setSkillLevel(Long.parseLong(_snapshot.child("level").getValue().toString()));
                    skill.setTitle((String) _snapshot.child("title").getValue());
                    System.out.println(skill.toMap());
                    skills.add(skill);
                }
                mSkillsAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void addBottomDots(int currentPage) {
        dots = new TextView[skills.size()];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

}
