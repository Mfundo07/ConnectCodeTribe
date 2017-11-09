package com.example.android.connectcodetribe.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.connectcodetribe.Adapters.SingleListAdapter;
import com.example.android.connectcodetribe.R;
import com.example.android.connectcodetribe.profile.GridMargin;
import com.example.android.connectcodetribe.profile.Single;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by docotel on 4/14/16.
 */
public class FragmentSingle extends Fragment implements SingleListAdapter.OnGridItemSelectedListener{

    private RecyclerView lvSingle;
    private GridLayoutManager gridLayoutManager;
    private SingleListAdapter singleListAdapter;

    private Context context;

    public static FragmentSingle newInstance() {
        return new FragmentSingle();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_single, container, false);

        lvSingle = (RecyclerView) rootView.findViewById(R.id.lvSingle);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        singleListAdapter = new SingleListAdapter(this);
        gridLayoutManager = new GridLayoutManager(context, 2);

        lvSingle.setLayoutManager(gridLayoutManager);
        lvSingle.addItemDecoration(new GridMargin(context, 2, 2, 2, 2));
        lvSingle.setAdapter(singleListAdapter);

        loadData();
    }

    private void loadData(){
        List<Single> singleList = new ArrayList<>();
        Single single;

        int img[] = {R.drawable.snap10, R.drawable.snap2,
                R.drawable.snap3, R.drawable.snap5,
                R.drawable.snap6, R.drawable.snap7,
                R.drawable.snap8, R.drawable.snap9};

        String title[] = {"App10", "App2",
                "App3", "App4",
                "App5", "App6",
                "App7", "App9"};

        for (int i = 0; i < img.length; i++){
            single = new Single();

            single.setImg(img[i]);
            single.setTitle(title[i]);

            singleList.add(single);
        }

        singleListAdapter.addAll(singleList);
    }

    @Override
    public void onGridItemClick(View v, int position) {
        Toast.makeText(context, singleListAdapter.getItem(position).getTitle(), Toast.LENGTH_SHORT).show();
    }
}
