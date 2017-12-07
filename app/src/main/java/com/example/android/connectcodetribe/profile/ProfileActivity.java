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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.connectcodetribe.AboutActivity;
import com.example.android.connectcodetribe.Adapters.ProjectsHorizontalAdapter;
import com.example.android.connectcodetribe.Admin_Login_Activity;
import com.example.android.connectcodetribe.DifferentCodetribeTabs;
import com.example.android.connectcodetribe.EmailPasswordActivity;
import com.example.android.connectcodetribe.LoginActivity;
import com.example.android.connectcodetribe.Model.TribeMate;
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
    DatabaseReference myRef, mDatabaseReference;
    ImageButton btnStatus, btnGithubLink, btnAddBio, btnTribeChat ;
    ImageView userImage, btnAddProject;
    public String gihubLink;

    TextView mBio, mStatus, mCodeTribe,mAge,mEmail,mEthnicity,mGender,mMobile,mCompanyName,mCompanyNumber,mEmploymentStatus,mSalary,mStartDate;

    private  ImageButton viewMoreButton;
    private String codeTribeName;
    RecyclerView mProjectsRecyclerView;
    ProjectsHorizontalAdapter mProjectsAdapter;
    FloatingActionButton mProfileEditrFAButton;
    List<TribeMate> projects = new ArrayList<>();
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

        mMobile = (TextView) findViewById(R.id.profile_cell_number);
        mGender = (TextView) findViewById(R.id.profile_gender);
        mEthnicity = (TextView) findViewById(R.id.profile_ethnicity);
        mEmail = (TextView) findViewById(R.id.profile_email);
        mAge = (TextView) findViewById(R.id.profile_age);
        mCompanyName = (TextView) findViewById(R.id.profile_company_name_text_view);
        mCompanyNumber = (TextView) findViewById(R.id.profile_company_contact_text_view);
        mEmploymentStatus = (TextView) findViewById(R.id.profile_employment_status_text);
        mSalary = (TextView) findViewById(R.id.profile_salary_text);
        mStartDate = (TextView) findViewById(R.id.profile_intake_period_text);

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

        myRef = database.getReference("/users/");
        mBio = (TextView) findViewById(R.id.userBio);

        mStatus = (TextView) findViewById(R.id.userStatus);
        mCodeTribe = findViewById(R.id.userCodeTribeName);
        btnGithubLink = (ImageButton) findViewById(R.id.userGithubImage);
        btnStatus = findViewById(R.id.userStatusImage);

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
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {

                    mStatus.setText((String) dataSnapshot.child(currentUser.getUid()).child("status").getValue());
                    mCodeTribe.setText((String) dataSnapshot.child(currentUser.getUid()).child("codeTribeLocation").getValue());
                    toolbar.setTitle(((String)dataSnapshot.child(currentUser.getUid()).child("name").getValue() +" "+  dataSnapshot.child(currentUser.getUid()).child("surname").getValue()));
                    mMobile.setText((String) dataSnapshot.child(currentUser.getUid()).child("mobileNo").getValue());
                    mGender.setText((String) dataSnapshot.child(currentUser.getUid()).child("gender").getValue());
                    mEthnicity.setText((String) dataSnapshot.child(currentUser.getUid()).child("ethnicity").getValue());
                    mEmail.setText((String) dataSnapshot.child(currentUser.getUid()).child("emailAddress").getValue());
                    mAge.setText((String) dataSnapshot.child(currentUser.getUid()).child("age").getValue());
                    mCompanyName.setText((String) dataSnapshot.child(currentUser.getUid()).child("companyName").getValue());
                    mCompanyNumber.setText((String) dataSnapshot.child(currentUser.getUid()).child("companyContactNumber").getValue());
                    mEmploymentStatus.setText((String) dataSnapshot.child(currentUser.getUid()).child("employed").getValue());
                    mSalary.setText((String) dataSnapshot.child(currentUser.getUid()).child("salary").getValue());
                    mStartDate.setText((String) dataSnapshot.child(currentUser.getUid()).child("startDate").getValue());
                    Glide.with(userImage.getContext())
                           .load((String) dataSnapshot.child(currentUser.getUid()).child("profile_picture").getValue())
                            .into(userImage);
                    mBio.setText((String) dataSnapshot.child(currentUser.getUid()).child("bio").getValue());
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



                    projects.clear();
                    for (DataSnapshot snapshot : dataSnapshot.child(currentUser.getUid()).child("projects").getChildren()) {
                        TribeMate project = new TribeMate();
                        project.setProjectTitle((String) snapshot.child("name").getValue());
                        project.setProjectLink((String) snapshot.child("github_link").getValue());
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
                        myRef.child(currentUser.getUid()).child("bio").setValue(mBioEditText.getText().toString());
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




