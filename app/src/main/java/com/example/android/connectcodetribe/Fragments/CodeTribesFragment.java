package com.example.android.connectcodetribe.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.connectcodetribe.ActiveUserActivity;
import com.example.android.connectcodetribe.ChatActivityAlexandra;
import com.example.android.connectcodetribe.ChatActivityPretoria;
import com.example.android.connectcodetribe.ChatActivitySoweto;
import com.example.android.connectcodetribe.R;

/**
 * Created by Admin on 10/4/2017.
 */

public class CodeTribesFragment extends Fragment {
    private CardView soweto;
    private CardView  tembisa;
    private CardView  pretoria;
    private CardView  alex;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_codetribes,container, false);

        soweto = rootView.findViewById(R.id.codeTribe_soweto);
        tembisa = rootView.findViewById(R.id.codeTribe_tembisa);
        pretoria = rootView.findViewById(R.id.codeTribe_pretoria);
        alex = rootView.findViewById(R.id.codeTribe_alex);

        soweto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ChatActivitySoweto.class);
                startActivity(intent);
            }
        });
        tembisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ActiveUserActivity.class);
                startActivity(intent);
            }
        });
        alex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ChatActivityAlexandra.class);
                startActivity(intent);
            }
        });
        pretoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ChatActivityPretoria.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}
