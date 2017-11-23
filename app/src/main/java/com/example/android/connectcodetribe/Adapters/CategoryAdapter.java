package com.example.android.connectcodetribe.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.connectcodetribe.Fragments.PretoriaFragment;
import com.example.android.connectcodetribe.Fragments.SowetoFragment;
import com.example.android.connectcodetribe.Fragments.TembisaFragment;
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
        if (position == 0){
            return new SowetoFragment();}
            else if (position == 1){
            return new PretoriaFragment();
        }else{
                return new TembisaFragment();
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
