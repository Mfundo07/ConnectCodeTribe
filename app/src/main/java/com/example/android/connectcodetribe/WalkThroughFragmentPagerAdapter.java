package com.example.android.connectcodetribe;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.connectcodetribe.Fragments.PhotoSetupFragment;
import com.example.android.connectcodetribe.Fragments.SelectCodeTribeFragment;
import com.example.android.connectcodetribe.Fragments.SingleCodeTribeListFragment;

/**
 * Created by Admin on 1/19/2018.
 */

public class WalkThroughFragmentPagerAdapter extends FragmentPagerAdapter {
    public WalkThroughFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new SelectCodeTribeFragment();
        }
        else if (position == 1){
            return new SingleCodeTribeListFragment();
        }
        else if (position == 2){
            return new UserInfoEditorFragment();
        }
        return new PhotoSetupFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }
}
