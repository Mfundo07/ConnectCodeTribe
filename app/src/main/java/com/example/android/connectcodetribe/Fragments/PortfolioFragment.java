package com.example.android.connectcodetribe.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.connectcodetribe.ExperienceActivity;
import com.example.android.connectcodetribe.ProfileActivity;
import com.example.android.connectcodetribe.ProjectsActivity;
import com.example.android.connectcodetribe.QualificationActivity;
import com.example.android.connectcodetribe.R;

/**
 * Created by Admin on 10/4/2017.
 */

public class PortfolioFragment extends Fragment {
    public CardView codeTribeCardView, codeTribeCardView2,codeTribeCardView3, codeTribeCardView4;

    public PortfolioFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.user_portfolio,container, false);
        codeTribeCardView = rootView.findViewById(R.id.card1);
        codeTribeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);


            }
        });
        codeTribeCardView2 = rootView.findViewById(R.id.card2);
        codeTribeCardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), QualificationActivity.class);
                startActivity(intent);


            }
        });
        codeTribeCardView3 = rootView.findViewById(R.id.card3);
        codeTribeCardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ProjectsActivity.class);
                startActivity(intent);


            }
        });
        codeTribeCardView4 = rootView.findViewById(R.id.card4);

        codeTribeCardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ExperienceActivity.class);
                startActivity(intent);


            }
        });







        return rootView;
    }
}
