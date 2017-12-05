package com.example.android.connectcodetribe.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.connectcodetribe.Fragments.Admin_PretoriaFragment;
import com.example.android.connectcodetribe.Fragments.Admin_SowetoFragment;
import com.example.android.connectcodetribe.Fragments.Admin_TembisaFragment;
import com.example.android.connectcodetribe.R;

/**
 * Created by Admin on 11/17/2017.
 */

public class Admin_CategoryAdapter extends FragmentPagerAdapter {
    Context mContext;
    public Admin_CategoryAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new Admin_SowetoFragment();}
        else if (position == 1){
            return new Admin_PretoriaFragment();
        }else{
            return new Admin_TembisaFragment();
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0){
            return mContext.getString(R.string.tab_soweto);
        }
        else if (position == 1){
            return mContext.getString(R.string.tab_pretoria);
        }
        else{
            return mContext.getString(R.string.tab_tembisa);
        }

    }
}
