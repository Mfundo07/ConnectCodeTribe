package com.example.android.connectcodetribe.profile;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.connectcodetribe.Adapters.ExperienceAdapter;
import com.example.android.connectcodetribe.Adapters.ProjectsHorizontalAdapter;
import com.example.android.connectcodetribe.Adapters.SkillAdapter;
import com.example.android.connectcodetribe.ChatActivityAlexandra;
import com.example.android.connectcodetribe.ChatActivityPretoria;
import com.example.android.connectcodetribe.ChatActivitySoweto;
import com.example.android.connectcodetribe.ChatActivityThembisa;
import com.example.android.connectcodetribe.DifferentCodetribeTabs;
import com.example.android.connectcodetribe.Experience_more;
import com.example.android.connectcodetribe.LoginActivity;
import com.example.android.connectcodetribe.Model.Experience;
import com.example.android.connectcodetribe.Model.Profile;
import com.example.android.connectcodetribe.Model.Project;
import com.example.android.connectcodetribe.Model.Skill;
import com.example.android.connectcodetribe.Projects_more;
import com.example.android.connectcodetribe.R;
import com.example.android.connectcodetribe.Skills_more;
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
    TextView mBio, mStatus, mCodeTribe;
    ImageButton btnStatus, btnGithubLink, btnCodeTribe ;
    ImageView userImage, btnAddProject, skiills_editor, AddExperience;
    public String gihubLink;
    ImageButton skillName;
    private  ImageButton viewMoreButton;
    private String codeTribeName;
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
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_layout);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kabza= new Intent(ProfileActivity.this,UserProfileEditorActivity.class);
                startActivity(kabza);
            }
        });

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
        userImage = (ImageView) findViewById(R.id.userImage);
        if (currentUser == null){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }else{
            Glide.with(userImage.getContext())
                    .load(currentUser.getPhotoUrl())
                    .into(userImage);
        }
        myRef = database.getReference("testing").child("users").child("codetribe").child("Soweto").child("0");
        mBio = (TextView) findViewById(R.id.userBio);
        mStatus = (TextView) findViewById(R.id.userStatus);
     //   mCodeTribe = (TextView) findViewById(R.id.userCodeTribeName);
        btnGithubLink = (ImageButton) findViewById(R.id.userGithubImage);

        btnAddProject = (ImageButton) findViewById(R.id.btnAddProject);
        skiills_editor = (ImageButton) findViewById(R.id.skiills_editor);
        AddExperience = (ImageButton) findViewById(R.id.AddExperience);

        skillName = (ImageButton) findViewById(R.id.skill_display_picture);
        viewMoreButton = (ImageButton) findViewById(R.id.moreOnUserBio);


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
                = new LinearLayoutManager(ProfileActivity.this, StaggeredGridLayoutManager.HORIZONTAL, false);
        mSkillsRecyclerView.setLayoutManager(verticalLayoutmanager);
        mSkillsAdapter = new SkillAdapter(ProfileActivity.this, skills);
        mSkillsRecyclerView.setAdapter(mSkillsAdapter);
        mExperienceAdapter = new ExperienceAdapter(mExperiences, ProfileActivity.this);
        mExperiencesRecyclerView = (RecyclerView) findViewById(R.id.experienceRecyclerView);
        mExperiencesRecyclerView.setAdapter(mExperienceAdapter);
        LinearLayoutManager expVertLayoutManager
                = new LinearLayoutManager(ProfileActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mExperiencesRecyclerView.setLayoutManager(expVertLayoutManager);
        btnAddProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, Projects_more.class));
            }


        });

        skiills_editor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, Skills_more.class));
            }


        });
        AddExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, Experience_more.class));
            }


        });
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                Profile profile = dataSnapshot.getValue(Profile.class);
                mStatus.setText(dataSnapshot.child("status").getValue().toString());
                toolbar.setTitle(dataSnapshot.child("name").getValue().toString());
                toolbar1.setTitle((String)dataSnapshot.child("three_words").getValue());
                mBio.setText((String) dataSnapshot.child("bio").getValue());
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
                        mBio.getViewTreeObserver().removeOnGlobalLayoutListener(this);}
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
                codeTribeName = (String) dataSnapshot.child("tribe").getValue();
                mCodeTribe.setText(codeTribeName);

                btnAddProject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(ProfileActivity.this,Projects_more.class);
                        startActivity(intent);
                    }
                });

                skiills_editor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(ProfileActivity.this,Skills_more.class);
                        startActivity(intent);
                    }
                });
                AddExperience.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(ProfileActivity.this,Experience_more.class);
                        startActivity(intent);
                    }
                });

                projects.clear();
                for (DataSnapshot snapshot : dataSnapshot.child("projects").getChildren()) {
                    Project project = new Project();
                    project.setSnapshot((String) snapshot.child("snapshot").getValue());
                    project.setName((String) snapshot.child("name").getValue());
                    project.setGithub_link((String) snapshot.child("github_link").getValue());
                    System.out.println(project.toMap());
                    projects.add(project);
                }
                mProjectsAdapter.notifyDataSetChanged();
                skills.clear();
                for (DataSnapshot _snapshot : dataSnapshot.child("skills").getChildren()) {
                    Skill skill = new Skill();
                    //skill.setSkillLevel(Long.parseLong( _snapshot.child("level").getValue().toString()));
                    skill.setTitle((String) _snapshot.child("skill").getValue());
                    System.out.println(skill.toMap());
                    skills.add(skill);
                }
                mSkillsAdapter.notifyDataSetChanged();
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
        dots = new TextView[projects.size()];
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
        
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getGroupId();

        if(id==R.id.btnEdit);

        Intent intentAdmin = new Intent(ProfileActivity.this,UserProfileEditorActivity.class);
        startActivity(intentAdmin);

        return true;
    }
}
