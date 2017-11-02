package com.example.android.connectcodetribe.profile;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.connectcodetribe.ActiveUserActivity;
import com.example.android.connectcodetribe.Adapters.ExperienceAdapter;
import com.example.android.connectcodetribe.Adapters.ProjectsHorizontalAdapter;
import com.example.android.connectcodetribe.Adapters.SkillAdapter;
import com.example.android.connectcodetribe.ChatActivityAlexandra;
import com.example.android.connectcodetribe.ChatActivityPretoria;
import com.example.android.connectcodetribe.ChatActivitySoweto;
import com.example.android.connectcodetribe.ChatActivityThembisa;
import com.example.android.connectcodetribe.EditExperienceActivity;
import com.example.android.connectcodetribe.LoginActivity;
import com.example.android.connectcodetribe.Model.Experience;
import com.example.android.connectcodetribe.Model.Project;
import com.example.android.connectcodetribe.Model.Skill;
import com.example.android.connectcodetribe.ProjectsActivity;
import com.example.android.connectcodetribe.R;
import com.example.android.connectcodetribe.UserProfileEditorActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    ImageButton btnStatus, btnGithubLink, btnCodeTribe, btnAddProject;
    RecyclerView mSkills, mProjects, mExperience;
    ImageView userImage;
    public String gihubLink;
    Button skillName;
    private  ImageButton viewMoreButton;

    RecyclerView mSkillsRecyclerView, mProjectsRecyclerView, mExperiencesRecyclerView;

    ProjectsHorizontalAdapter mProjectsAdapter;
    SkillAdapter mSkillsAdapter;
    ExperienceAdapter mExperienceAdapter;

    List<Project> projects = new ArrayList<>();
    List<Skill> skills = new ArrayList<>();
    List<Experience> mExperiences = new ArrayList<>();

    private LinearLayout dotsLayout;
    private TextView[] dots;
    FirebaseUser currentUser;
    private boolean expandable = true;

    Toolbar toolbar;
    Toolbar toolbar1;
    private FloatingActionButton editPen;

    Button add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar1 = (Toolbar) findViewById(R.id.toolbar1);

        toolbar.setTitle("");
        toolbar1.setTitle("");

        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar1);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);


        // adding bottom dots
        addBottomDots(0);

        database = FirebaseDatabase.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        myRef = database.getReference("testing").child("users").child("codetribe").child("Soweto").child("0");
        mBio = (TextView) findViewById(R.id.userBio);
        mStatus = (TextView) findViewById(R.id.userStatus);
        mCodeTribe = (TextView) findViewById(R.id.userCodeTribeName);
        btnCodeTribe = (ImageButton) findViewById(R.id.userCodeTribeImage);
        btnGithubLink = (ImageButton) findViewById(R.id.userGithubImage);
        btnStatus = (ImageButton) findViewById(R.id.userStatusImage);
        btnAddProject = (ImageButton) findViewById(R.id.btnAddProject);
        userName = (TextView) findViewById(R.id.userName);
        userImage = (ImageView) findViewById(R.id.userImage);
        skillName = (Button) findViewById(R.id.skill_display_picture);
        editPen=(FloatingActionButton)findViewById(R.id.floatingActionButton) ;
        viewMoreButton = (ImageButton) findViewById(R.id.moreOnUserBio);

        editPen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nonclair = new Intent(ProfileActivity.this,UserProfileEditorActivity.class);
                startActivity(nonclair);
            }
        });

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


        mExperienceAdapter = new ExperienceAdapter(mExperiences, ProfileActivity.this);
        mExperiencesRecyclerView = (RecyclerView) findViewById(R.id.experienceRecyclerView);
        mExperiencesRecyclerView.setAdapter(mExperienceAdapter);

        LinearLayoutManager expVertLayoutManager
                = new LinearLayoutManager(ProfileActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mExperiencesRecyclerView.setLayoutManager(expVertLayoutManager);



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                mBio.setText((String) dataSnapshot.child("bio").getValue());
                mStatus.setText((String) dataSnapshot.child("status").getValue());
                Glide.with(userImage.getContext())
                        .load((String) dataSnapshot.child("display_picture").getValue())
                        .into(userImage);
                toolbar.setTitle((String) dataSnapshot.child("name").getValue());
                toolbar1.setTitle((String)dataSnapshot.child("three_words").getValue());
                mBio.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if (expandable){
                            expandable = false;
                            if (mBio.getLineCount() > 4){
                                viewMoreButton.setVisibility(View.VISIBLE);
                                ObjectAnimator animation = ObjectAnimator.ofInt(mBio, "maxLines", 4);
                                animation.setDuration(0).start();


                            }
                        }
                        mBio.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
                viewMoreButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!expandable){
                            expandable = true;
                            ObjectAnimator animation = ObjectAnimator.ofInt(mBio, "maxLines", mBio.length());
                            animation.setDuration(100).start();
                            viewMoreButton.setImageDrawable(ContextCompat.getDrawable(ProfileActivity.this, R.drawable.ic_expand_less));

                        }else{
                            expandable = false;
                            ObjectAnimator animation = ObjectAnimator.ofInt(mBio, "maxLines", 4);
                            animation.setDuration(100).start();
                            viewMoreButton.setImageDrawable(ContextCompat.getDrawable(ProfileActivity.this, R.drawable.ic_expand_more));
                        }
                    }
                });

                gihubLink = (String) dataSnapshot.child("github_link").getValue();
                mCodeTribe.setText((String) dataSnapshot.child("0").child("tribe").getValue());
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
                btnCodeTribe.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String codeTribeName = (String) dataSnapshot.child("tribe").getValue();
                        if (codeTribeName.equals("Soweto")){
                            mCodeTribe.setText(codeTribeName);
                            Intent intent = new Intent(ProfileActivity.this, ChatActivitySoweto.class);
                            startActivity(intent);
                        }else if (codeTribeName.equals("Tembisa")){
                            mCodeTribe.setText(codeTribeName);
                            Intent intent = new Intent(ProfileActivity.this,ChatActivityThembisa.class);
                            startActivity(intent);
                        }else if (codeTribeName.equals("Pretoria")){
                            mCodeTribe.setText(codeTribeName);
                            Intent intent = new Intent(ProfileActivity.this,ChatActivityPretoria.class);
                            startActivity(intent);
                        }else{
                            mCodeTribe.setText(codeTribeName);
                            Intent intent = new Intent(ProfileActivity.this,ChatActivityAlexandra.class);
                            startActivity(intent);
                        }

                    }
                });
                btnStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(ProfileActivity.this, ActiveUserActivity.class));
                    }
                });

                btnAddProject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(ProfileActivity.this,ProjectsActivity.class);
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
                    //skill.setSkillLevel(Long.parseLong( _snapshot.child("level").getValue().toString()));
                    skill.setTitle((String) _snapshot.child("title").getValue());
                    System.out.println(skill.toMap());
                    skills.add(skill);

                }
                mSkillsAdapter.notifyDataSetChanged();

                add = (Button)findViewById(R.id.Add);

                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(ProfileActivity.this, EditExperienceActivity.class);
                        startActivity(intent);

                    }

                });


                mExperiences.clear();
                for (DataSnapshot snapshot : dataSnapshot.child("experience").getChildren()){
                    Experience experience  = new Experience();
                    experience.setCompanyName((String) snapshot.child("company_name").getValue());
                    experience.setPosition((String) snapshot.child("job_position").getValue());
                    experience.setStartYear( snapshot.child("startYear").getValue().toString());
                    experience.setEndYear( snapshot.child("endYear").getValue().toString());
                    mExperiences.add(experience);
                }
                mExperienceAdapter.notifyDataSetChanged();


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