package com.example.android.connectcodetribe.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.connectcodetribe.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;

/**
 * Created by Admin on 1/19/2018.
 */

public class SelectCodeTribeFragment extends Fragment {
    public static final int TRIBE_SOWETO = 1;
    public static final int TRIBE_PRETORIA = 2;
    public static final int TRIBE_TEMBISA = 3;

    private int mTribe = TRIBE_SOWETO;

    private boolean mUserHasChanged = false;

    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mUserHasChanged = true;
            return false;
        }

    };

    DatabaseReference mDatabaseReference;
    StorageReference mStoragereference;
    FirebaseUser mUser;

    private Spinner codeTribeSpinner;
    private EditText mEmail;
    private Button nextStepButton, mSearchStep_button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.welcome_slide1, container, false);
        codeTribeSpinner = rootView.findViewById(R.id.codeTribe_select_spinner);
        nextStepButton = rootView.findViewById(R.id.nextStep_button);
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        mEmail = rootView.findViewById(R.id.select_email);
        mEmail.setText(mUser.getEmail());

        codeTribeSpinner.setOnTouchListener(mTouchListener);
        setupCodeTribeSpinner();

        nextStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Fragment fragment = new SingleCodeTribeListFragment();
                bundle.putString("CodeTribe", codeTribeSpinner.getSelectedItem().toString());
                bundle.putString("Email", mEmail.getText().toString());
                fragment.setArguments(bundle);
                Toast.makeText(getActivity(), "Searched", Toast.LENGTH_SHORT).show();

            }
        });
        return rootView;
    }
    private void setupCodeTribeSpinner() {
        ArrayAdapter codeTribeSinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.array_code_tribe_option, android.R.layout.simple_spinner_item);
        codeTribeSinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        codeTribeSpinner.setAdapter(codeTribeSinnerAdapter);
        codeTribeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(R.string.tab_soweto)) {
                        mTribe = TRIBE_SOWETO;
                    } else if (selection.equals(R.string.tab_tembisa)) {
                        mTribe = TRIBE_TEMBISA;
                    } else {
                        mTribe = TRIBE_PRETORIA;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mTribe = TRIBE_SOWETO;
            }
        });


    }

}
