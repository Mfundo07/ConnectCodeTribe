package com.example.android.connectcodetribe;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.android.connectcodetribe.Adapters.ViewPagerAdapter;
import com.example.android.connectcodetribe.Fragments.CodeTribesFragment;
import com.example.android.connectcodetribe.Fragments.ItemFragment;
import com.example.android.connectcodetribe.Fragments.PortfolioFragment;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    ItemFragment chatFragment;
    CodeTribesFragment codeTribeFragment;
    PortfolioFragment portfolioFragment;
    MenuItem prevMenuItem;


    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private GoogleApiClient mGoogleApiClient;
    private DatabaseReference mFirebaseDatabaseReference;
    FirebaseStorage storage = FirebaseStorage.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_chat:
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.navigation_codeTribes:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.navigation_portfolio:
                                viewPager.setCurrentItem(1);
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (prevMenuItem != null){
                    prevMenuItem.setChecked(true);
                }
                else{
                    bottomNavigationView.getMenu().getItem(position).setChecked(true);
                }
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        chatFragment=new ItemFragment();
        codeTribeFragment=new CodeTribesFragment();
        portfolioFragment=new PortfolioFragment();
        adapter.addFragment(codeTribeFragment);
        adapter.addFragment(portfolioFragment);
        //adapter.addFragment(chatFragment);
        viewPager.setAdapter(adapter);
    }







}
