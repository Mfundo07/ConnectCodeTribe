package com.example.android.connectcodetribe.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.connectcodetribe.Fragments.SowetoFragment;
import com.example.android.connectcodetribe.R;

/**
 * Created by Admin on 11/17/2017.
 */

public class CategoryAdapter extends FragmentPagerAdapter {
    Context mContext;
    public CategoryAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
            return new SowetoFragment();

    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public CharSequence getPageTitle(int position) {

            return mContext.getString(R.string.tab_soweto);

    }
}
