package com.example.android.connectcodetribe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class ScrollingFragment extends AppCompatActivity {

    private CircleImageView userProfileImage;
    private TextView userProfileName;
    private TextView userProfileStatus;
    private TextView userProfileCodeTribe;

    String mName;
    String mImage;
    String mStatus;
    String mCodeTribe;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_screen);


        userProfileName = findViewById(R.id.user_profile_name);
        userProfileImage = findViewById(R.id.user_profile_image_view);
        userProfileStatus = findViewById(R.id.user_profile_status);
        userProfileCodeTribe = findViewById(R.id.user_profile_codeTribe);

        mName = getIntent().getExtras().getString("Name");
        mImage = getIntent().getExtras().getString("Image");
        mStatus = getIntent().getExtras().getString("Status");
        mCodeTribe = getIntent().getExtras().getString("CodeTribe");

  if (getIntent().getExtras().getString("Name") != null){
        userProfileName.setText(mName);}
        else{
      userProfileName.setText("");
  }
        if (getIntent().getExtras().getString("Image") != null){
      Glide.with(userProfileImage.getContext())
                .load(mImage)
                .into(userProfileImage);}
                else {
            userProfileImage.setVisibility(View.INVISIBLE);
        }
        if (getIntent().getExtras().getString("Status") != null){
            userProfileStatus.setText(mStatus);}
            else{
            userProfileStatus.setText(" ");
        }
       if (getIntent().getExtras().getString("CodeTribe") != null){
            userProfileCodeTribe.setText(mCodeTribe);}
            else{
           userProfileCodeTribe.setText(" ");
       }


    }


}
