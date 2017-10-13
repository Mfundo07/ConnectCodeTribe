package com.example.android.connectcodetribe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.android.connectcodetribe.Adapters.ViewPagerAdapter;
import com.example.android.connectcodetribe.Fragments.CodeTribesFragment;
import com.example.android.connectcodetribe.Fragments.ItemFragment;
import com.example.android.connectcodetribe.Fragments.PortfolioFragment;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    ItemFragment chatFragment;
    CodeTribesFragment codeTribeFragment;
    PortfolioFragment portfolioFragment;
    MenuItem prevMenuItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

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
        adapter.addFragment(chatFragment);
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sign_out_menu:
                //signout
                Intent intent = new Intent(this,Login.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
