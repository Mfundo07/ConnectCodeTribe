package com.example.android.connectcodetribe;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Welcome_activity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mPager;
    private int[] layouts = {R.layout.first_slide,R.layout.second_slide,R.layout.third_slide,R.layout.fourth_slide};
    private MpagerAdapter mpagerAdapter;
    private LinearLayout Dots_Layout;
    private ImageView[] dots;
    private Button BnNext,BnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (new Preference_Manager(this).checkPreference()){
            loadHome();
        }

        if (Build.VERSION.SDK_INT>26)

        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }
        else
        {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }
        setContentView(R.layout.activity_welcome_activity);

        mPager = (ViewPager)findViewById(R.id.viewPager);
        mpagerAdapter = new MpagerAdapter(layouts,this);
        mPager.setAdapter(mpagerAdapter);

        Dots_Layout = (LinearLayout) findViewById(R.id.dotsLayout);
        BnNext = (Button)findViewById(R.id.bnNext);
        BnSave = (Button)findViewById(R.id.bnSave);
        BnNext.setOnClickListener(this);
        BnSave.setOnClickListener(this);
        createDots(0);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                createDots(position);
                if (position==layouts.length-1){
                    BnNext.setText("Finish");
                    BnSave.setVisibility(View.INVISIBLE);
                }
                else {
                    BnNext.setText("Next");
                    BnSave.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private  void  createDots (int current_position){
        if(Dots_Layout!=null)
            Dots_Layout.removeAllViews();
        dots = new ImageView[layouts.length];
        for (int i = 0; i<layouts.length; i++){

            dots[i] = new ImageView(this);
            if (i==current_position){

                dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.active_dots));

            }
            else

                {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.default_dots));



                }
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            params.setMargins(4,0,4,0);

            Dots_Layout.addView(dots[i],params);

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.bnNext:
                loadNextSlide();
                       break;

            case R.id.bnSave:
                loadHome();
                new Preference_Manager(this).writePreference();
                break;


        }



    }
    private void loadHome(){
        startActivity(new Intent(this,Main3Activity.class));
        finish();
    }

    private void loadNextSlide(){

        int next_slide = mPager.getCurrentItem()+1;

        if (next_slide<layouts.length){

            mPager.setCurrentItem(next_slide);

        }
        else {
            loadHome();
            new Preference_Manager(this).writePreference();
        }

    }
}