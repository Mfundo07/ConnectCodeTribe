package com.example.android.connectcodetribe;

import android.support.v4.app.Fragment;

/**
 * Created by Admin on 12/1/2017.
 */

public class NewUserListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new UserFragmentList();
    }
}
