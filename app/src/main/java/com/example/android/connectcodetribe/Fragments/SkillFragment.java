package com.example.android.connectcodetribe.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.connectcodetribe.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SkillFragment extends Fragment {


    public SkillFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.skills_items, container, false);
        // Inflate the layout for this fragment
        return rootView;
    }

}
