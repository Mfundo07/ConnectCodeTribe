package com.example.android.connectcodetribe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ScrollingFragment extends AppCompatActivity {

    private CircleImageView userProfileImage;
    private TextView userProfileName;
    private TextView userProfileStatus;
    private TextView userProfileCodeTribe;
    private TextView userProfileSurname;

    private TextView userEMC;
    private TextView userEthncity;
    private TextView userGender;
    private TextView userAge;
    private TextView userEmail;
    private TextView userMobileNo;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_screen);


        userProfileName = findViewById(R.id.user_profile_name);
        userProfileImage = findViewById(R.id.user_profile_image_view);
        userProfileStatus = findViewById(R.id.user_profile_status);
        userProfileCodeTribe = findViewById(R.id.user_profile_codeTribe);
        userProfileSurname = findViewById(R.id.user_profile_surname);

        userEMC = findViewById(R.id.Userprofile_employeeCode);
        userEthncity = findViewById(R.id.Userprofile_ethenicity);
        userGender = findViewById(R.id.Userprofile_gender);
        userAge = findViewById(R.id.Userprofile_age);
        userEmail = findViewById(R.id.Userprofile_email);
        userMobileNo = findViewById(R.id.Userprofile_mobile);

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


        if (getIntent().getExtras().getString("Name") != null){
        userProfileName.setText(mName);}
        else{
      userProfileName.setText("");
  }

        if (getIntent().getExtras().getString("Status") != null){
            userProfileStatus.setText(mStatus);}
            else{
            userProfileStatus.setText(" ");
        }
       if (getIntent().getExtras().getString("CodeTribe") != null){
            userProfileCodeTribe.setText(mCodeTribe);}
            else{
           userProfileCodeTribe.setText("  ");
       }

        if (getIntent().getExtras().getString("Employee_code") != null){
            userEMC.setText(mEMC);}
        else{
            userEthncity.setText("");
        }

        if (getIntent().getExtras().getString("Gender") != null){
            userGender.setText(mGender);}
        else{
            userGender.setText("");
        }

        if (getIntent().getExtras().getString("Ethnicity") != null){
            userEthncity.setText(mEthnicity);}
        else{
            userEthncity.setText("");
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

       userProfileSurname.setText(mSurname);

    }




}
