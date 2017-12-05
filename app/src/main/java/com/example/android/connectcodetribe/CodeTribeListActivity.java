package com.example.android.connectcodetribe;

import android.support.v4.app.Fragment;

/**
 * Created by Admin on 12/4/2017.
 */

public class CodeTribeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new SingleCodeTribeListFragment();
    }
}
