package com.example.android.connectcodetribe;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ScrollingFragment extends AppCompatActivity {




    private TextView userProgramStatus;

    String mName;
    String mStatus;
    String mCodeTribe;
    String mSurname;
    String mBio;
    String mGender;
    String mEMC;
    String mEthnicity;
    String mAge;
    String mEmail;
    String mMobile;
    String mImage;
    String mEmployed;
    String mSalary;
    String mCompanyName;
    String mCompanyContact;
    Toolbar userProfileToolbar;

    Boolean expandable = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);


        TextView userEthnicity = findViewById(R.id.user_ethnicity);
        TextView userGender = findViewById(R.id.user_gender);
        TextView userAge = findViewById(R.id.user_age);
        TextView userEmail = findViewById(R.id.user_email);
        TextView userMobileNo = findViewById(R.id.user_cell_number);
        TextView userProfileStatus = findViewById(R.id.userStatus);
        TextView userCodeTribeLocation = findViewById(R.id.user_code_tribe);
        TextView userEMC = findViewById(R.id.user_code);
        ImageView userImage = findViewById(R.id.userImage);
        TextView userSalary = findViewById(R.id.profile_salary_text);
        TextView userCompanyName = findViewById(R.id.profile_company_name_text_view);
        TextView userCompanyContact = findViewById(R.id.profile_company_contact_text_view);
        final TextView userBio = findViewById(R.id.userBio);
        final ImageButton viewMoreButton = findViewById(R.id.moreOnUserBio);


        mName = getIntent().getExtras().getString("Name");
        mImage = getIntent().getExtras().getString("Image");
        mStatus = getIntent().getExtras().getString("Status");
        mCodeTribe = getIntent().getExtras().getString("CodeTribe");
        mSurname = getIntent().getExtras().getString("Surname");
        mImage = getIntent().getExtras().getString("image");
        mBio = getIntent().getExtras().getString("bio");


        mGender = getIntent().getExtras().getString("Gender");
        mEMC = getIntent().getExtras().getString("Employee_code");
        mEthnicity = getIntent().getExtras().getString("Ethnicity");
        mAge = getIntent().getExtras().getString("Age");
        mEmail = getIntent().getExtras().getString("Email");
        mMobile = getIntent().getExtras().getString("Mobile");
        mSalary = getIntent().getExtras().getString("salary");
        mCompanyName = getIntent().getExtras().getString("company_name");
        mCompanyContact = getIntent().getExtras().getString("company_contact");

        userProfileToolbar =  findViewById(R.id.toolbar);
        userProfileToolbar.setTitle(mName + " " + mSurname);
        setSupportActionBar(userProfileToolbar);


        userProfileStatus.setText(mStatus);
        userCodeTribeLocation.setText(mCodeTribe);
        userEMC.setText(mEMC);
        userSalary.setText(mSalary);
        userCompanyName.setText(mCompanyName);
        userCompanyContact.setText(mCompanyContact);
        Glide.with(userImage.getContext())
                .load(mImage)
                .into(userImage);

        userBio.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (expandable) {
                    expandable = false;
                    if (userBio.getLineCount() > 4) {
                        viewMoreButton.setVisibility(View.VISIBLE);
                        ObjectAnimator animation = ObjectAnimator.ofInt(userBio, "maxLines", 4);
                        animation.setDuration(0).start();
                    }
                }
                userBio.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        viewMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!expandable) {
                    expandable = true;
                    ObjectAnimator animation = ObjectAnimator.ofInt(userBio, "maxLines", userBio.length());
                    animation.setDuration(100).start();
                    viewMoreButton.setImageDrawable(ContextCompat.getDrawable(ScrollingFragment.this, R.drawable.ic_expand_less));
                } else {
                    expandable = false;
                    ObjectAnimator animation = ObjectAnimator.ofInt(userBio, "maxLines", 4);
                    animation.setDuration(100).start();
                    viewMoreButton.setImageDrawable(ContextCompat.getDrawable(ScrollingFragment.this, R.drawable.ic_expand_more));
                }
            }
        });


        if (getIntent().getExtras().getString("Gender") != null){
            userGender.setText(mGender);}
        else{
            userGender.setText("");
        }

        if (getIntent().getExtras().getString("Ethnicity") != null){
            userEthnicity.setText(mEthnicity);}
        else{
            userEthnicity.setText("");
        }

        if (getIntent().getExtras().getString("Age") != null){
            userAge.setText(mAge);}
        else{
            userAge.setText("");
        }

        if (getIntent().getExtras().getString("Email") != null){
            userEmail.setText(mEmail);}
        else{
            userEmail.setText("");
        }

        if (getIntent().getExtras().getString("Mobile") != null){
            userMobileNo.setText(mMobile);}
        else{
            userMobileNo.setText("");
        }




    }


}
