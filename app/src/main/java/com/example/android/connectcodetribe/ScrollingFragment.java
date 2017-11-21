package com.example.android.connectcodetribe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ScrollingFragment extends AppCompatActivity {

    private CircleImageView userProfileImage;
    private TextView userProfileName;
    private TextView userProfileStatus;
    private TextView userProfileCodeTribe;
    private TextView userProfileSurname;

    private TextView userEMC;
    private TextView userEthnicity;
    private TextView userGender;
    private TextView userAge;
    private TextView userEmail;
    private TextView userMobileNo;
    private TextView userProgramStatus;
    private TextView userCodeTribeLocation;

    String mName;
    String mImage;
    String mStatus;
    String mCodeTribe;
    String mSurname;

    String mGender;
    String mEMC;
    String mEthnicity;
    String mAge;
    String mEmail;
    String mMobile;
    Toolbar userProfileToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);



        userEthnicity = findViewById(R.id.user_ethnicity);
        userGender = findViewById(R.id.user_gender);
        userAge = findViewById(R.id.user_age);
        userEmail = findViewById(R.id.user_email);
        userMobileNo = findViewById(R.id.user_cell_number);
        userProfileStatus = findViewById(R.id.user_status);
        userCodeTribeLocation = findViewById(R.id.user_code_tribe);

        mName = getIntent().getExtras().getString("Name");
        mImage = getIntent().getExtras().getString("Image");
        mStatus = getIntent().getExtras().getString("Status");
        mCodeTribe = getIntent().getExtras().getString("CodeTribe");
        mSurname = getIntent().getExtras().getString("Surname");


        mGender = getIntent().getExtras().getString("Gender");
        mEMC = getIntent().getExtras().getString("Employee_code");
        mEthnicity = getIntent().getExtras().getString("Ethnicity");
        mAge = getIntent().getExtras().getString("Age");
        mEmail = getIntent().getExtras().getString("Email");
        mMobile = getIntent().getExtras().getString("Mobile");

        userProfileToolbar =  findViewById(R.id.toolbar);
        userProfileToolbar.setTitle(mName + " " + mSurname);
        setSupportActionBar(userProfileToolbar);


        userProfileStatus.setText(mStatus);
        userCodeTribeLocation.setText(mCodeTribe);


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

        if (getIntent().getExtras().getString("Mobile No") != null){
            userMobileNo.setText(mMobile);}
        else{
            userMobileNo.setText("");
        }




    }


}
