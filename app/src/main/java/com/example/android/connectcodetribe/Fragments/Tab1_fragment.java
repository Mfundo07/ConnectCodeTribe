package com.example.android.connectcodetribe.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.connectcodetribe.R;

/**
 * Created by Admin on 11/14/2017.
 */

public class Tab1_fragment extends android.support.v4.app.Fragment {
    private static final String TAG = "Tab1Fragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.tab1_fragment,container,false);

       return view;
    }
}
