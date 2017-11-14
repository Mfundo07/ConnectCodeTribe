package com.example.android.connectcodetribe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.connectcodetribe.Adapters.ExperienceAdapter;
import com.example.android.connectcodetribe.Adapters.ProjectsHorizontalAdapter;
import com.example.android.connectcodetribe.Adapters.SkillAdapter;
import com.example.android.connectcodetribe.Model.Experience;
import com.example.android.connectcodetribe.Model.Project;
import com.example.android.connectcodetribe.Model.Skill;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ScrollingFragment extends AppCompatActivity {

    TextView mEmail,mCurrentOccupation;
    FirebaseDatabase database;
    DatabaseReference myRef;
    TextView mBio, txtStatus, mCodeTribe, userName;
    ImageButton imageButtonStatus, btnGithubLink, btnCodeTribe;
    RecyclerView mSkills, mProjects, mExperience;
    ImageView userImage;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mEmail = (TextView) findViewById(R.id.User_Email);
        mCurrentOccupation = (TextView) findViewById(R.id.User_occupation);

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
        mBio = (TextView) findViewById(R.id.userBio);
        txtStatus = (TextView) findViewById(R.id.userStatus);
        mCodeTribe = (TextView) findViewById(R.id.userCodeTribeName);
        btnCodeTribe = (ImageButton) findViewById(R.id.userCodeTribeImage);
        btnGithubLink = (ImageButton) findViewById(R.id.userGithubImage);
        imageButtonStatus = (ImageButton) findViewById(R.id.userStatusImage);
        userName = (TextView) findViewById(R.id.userName);
        skillName = (ImageButton) findViewById(R.id.skill_display_picture);
        viewMoreButton = (ImageButton) findViewById(R.id.moreOnUserBio);
    }

    private void addBottomDots(int i) {

    }
}
