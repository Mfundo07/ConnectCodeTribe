package com.example.android.connectcodetribe;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.connectcodetribe.Adapters.SectionsPageAdapter;
import com.example.android.connectcodetribe.Fragments.Tab1_fragment;
import com.example.android.connectcodetribe.Fragments.Tab2_Fragment;
import com.example.android.connectcodetribe.Fragments.Tab3_Fragment;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "Main2Activity";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.d(TAG, "onCreate: Starting.");

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(mViewPager);

    }

    private void  setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1_fragment(),"Personal");
        adapter.addFragment(new Tab2_Fragment(),"Educational");
        adapter.addFragment(new Tab3_Fragment(),"Tribal");
        viewPager.setAdapter(adapter);
    }

}
