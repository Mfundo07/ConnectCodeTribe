package com.example.android.connectcodetribe.profile;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.connectcodetribe.Adapters.ProjectsHorizontalAdapter;
import com.example.android.connectcodetribe.DifferentCodetribeTabs;
import com.example.android.connectcodetribe.LoginActivity;
import com.example.android.connectcodetribe.Model.Project;
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
    String mEmployee;
    String mCodeTribeOption;

    FirebaseDatabase database;
    DatabaseReference myRef;
    TextView mBio, mStatus, mCodeTribe;
    ImageButton btnStatus, btnGithubLink, btnAddBio ;
    ImageView userImage, btnAddProject, AddEx;
    public String gihubLink;






    private  ImageButton viewMoreButton;
    private String codeTribeName;
    RecyclerView mProjectsRecyclerView;
    ProjectsHorizontalAdapter mProjectsAdapter;
    FloatingActionButton mProfileEditrFAButton;
    List<Project> projects = new ArrayList<>();
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


        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar1 = (Toolbar) findViewById(R.id.toolbar1);
        toolbar.setTitle("");
        toolbar1.setTitle("");
        setSupportActionBar(toolbar);
        btnAddBio = findViewById(R.id.add_bio_button);
        btnAddProject = findViewById(R.id.btn_add_project);
        setSupportActionBar(toolbar1);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        // adding bottom dots
        addBottomDots(0);
        database = FirebaseDatabase.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        userImage = (ImageView) findViewById(R.id.userImage);
        if (currentUser == null){
            userImage.setImageResource(R.drawable.man_user_user);
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }else{
            Glide.with(userImage.getContext())
                    .load(currentUser.getPhotoUrl())
                    .into(userImage);
        }
        myRef = database.getReference("/users/");
        mBio = (TextView) findViewById(R.id.userBio);
        mStatus = (TextView) findViewById(R.id.userStatus);
        mCodeTribe = findViewById(R.id.userCodeTribeName);
        btnGithubLink = (ImageButton) findViewById(R.id.userGithubImage);
        btnStatus = findViewById(R.id.userStatusImage);
        btnAddBio = findViewById(R.id.btn_add_bio);

        viewMoreButton = (ImageButton) findViewById(R.id.moreOnUserBio);
        mProfileEditrFAButton = findViewById(R.id.fab);


        mProjectsRecyclerView = (RecyclerView) findViewById(R.id.projectsRecyclerview);
        //Setup layout manager to a horizontal scrolling recyclerView
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(ProfileActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mProjectsRecyclerView.setLayoutManager(horizontalLayoutManagaer);
        mProjectsAdapter = new ProjectsHorizontalAdapter(ProfileActivity.this, projects);
        mProjectsRecyclerView.setAdapter(mProjectsAdapter);
        //Setup layout manager to a staggered scrolling recyclerView
       btnStatus.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(ProfileActivity.this, DifferentCodetribeTabs.class));
           }
       });





        mProfileEditrFAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, UserProfileEditorActivity.class));
            }
        });
        myRef.child(currentUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {

                    mStatus.setText((String) dataSnapshot.child("codeTribe_details").child("codeTribeProgramStatus").getValue());
                    mCodeTribe.setText((String) dataSnapshot.child("codeTribe_details").child("codeTribeLocation").getValue());
                    toolbar.setTitle(((String)dataSnapshot.child("personal_details").child("name").getValue() +" "+  dataSnapshot.child("personal_details").child("surname").getValue()));
                    Glide.with(userImage.getContext())
                            .load((String) dataSnapshot.child("profile_images").child("profileImage").getValue())
                            .into(userImage);
                    mBio.setText((String) dataSnapshot.child("personal_details").child("bio").getValue());
                    mBio.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            if (expandable) {
                                expandable = false;
                                if (mBio.getLineCount() > 4) {
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
                            if (!expandable) {
                                expandable = true;
                                ObjectAnimator animation = ObjectAnimator.ofInt(mBio, "maxLines", mBio.length());
                                animation.setDuration(100).start();
                                viewMoreButton.setImageDrawable(ContextCompat.getDrawable(ProfileActivity.this, R.drawable.ic_expand_less));
                            } else {
                                expandable = false;
                                ObjectAnimator animation = ObjectAnimator.ofInt(mBio, "maxLines", 4);
                                animation.setDuration(100).start();
                                viewMoreButton.setImageDrawable(ContextCompat.getDrawable(ProfileActivity.this, R.drawable.ic_expand_more));
                            }
                        }
                    });
                    gihubLink = (String) dataSnapshot.child("github_link").getValue();
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

                    btnAddProject.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(ProfileActivity.this, ProjectsActivity.class);
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
                }
                @Override
                public void onCancelled (DatabaseError databaseError){
                }
            });

        btnAddBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                LayoutInflater inflater = ProfileActivity.this.getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.bio_editor, null);
                final EditText mBioEditText = dialogView.findViewById(R.id.bio_edit_text);
                Button addBioButton = dialogView.findViewById(R.id.bio_text_submit_button);
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();

                addBioButton.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View v) {
                        myRef.child(currentUser.getUid()).child("personal_details").child("bio").setValue(mBioEditText.getText().toString());
                        mBioEditText.setText("");
                        alertDialog.cancel();


                    }


                });


                alertDialog.show();

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

}
