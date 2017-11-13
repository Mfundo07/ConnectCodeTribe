package com.example.android.connectcodetribe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.example.android.connectcodetribe.Model.Profile;

import java.util.List;
import java.util.UUID;

/**
 * Created by Admin on 11/13/2017.
 */

public class ProfilePagerActivity extends FragmentActivity {
    public static final String EXTRA_PROFILE_ID = "com.example.android.connectcodetribe.profile_id";
    public static ProfilePagerActivity sProfilePagerActivity;

    private ViewPager mViewPager;
    private List<Profile> mProfiles;

    public static Intent newIntent(Context packageContext, UUID profileId){
        Intent intent = new Intent(packageContext, ProfilePagerActivity.class);
        intent.putExtra(EXTRA_PROFILE_ID, profileId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_pager);

        String profileId = (String) getIntent()
                .getSerializableExtra(EXTRA_PROFILE_ID);
        mViewPager = findViewById(R.id.activity_profile_pager_view_pager);



    }


}
