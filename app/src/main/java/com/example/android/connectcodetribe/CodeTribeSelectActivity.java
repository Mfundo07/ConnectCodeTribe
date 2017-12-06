package com.example.android.connectcodetribe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

/**
 * Created by Admin on 12/4/2017.
 */

public class CodeTribeSelectActivity extends AppCompatActivity {

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

    private Spinner codeTribeSpinner;
    private EditText mNaming, mSurnaming;
    private Button nextStepButton, mSearchStep_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.codetribe_select);

        codeTribeSpinner = findViewById(R.id.codeTribe_select_spinner);
        nextStepButton = findViewById(R.id.nextStep_button);
        mNaming = (EditText) findViewById(R.id.codetribeName_edit);
        mSurnaming = (EditText) findViewById(R.id.codetribeSurname_edit);

        codeTribeSpinner.setOnTouchListener(mTouchListener);
        setupCodeTribeSpinner();

        nextStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CodeTribeSelectActivity.this, CodeTribeListActivity.class);
                intent.putExtra("CodeTribe", codeTribeSpinner.getSelectedItem().toString());
                intent.putExtra("Surname", mSurnaming.getText().toString());
                intent.putExtra("Name", mNaming.getText().toString());
                startActivity(intent);
            }
        });


    }


    private void setupCodeTribeSpinner() {
        ArrayAdapter codeTribeSinnerAdapter = ArrayAdapter.createFromResource(this,
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

//                mSearchStep_button.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {

        System.out.println(mSurnaming.getText().toString());
        String tribe = codeTribeSpinner.getSelectedItem().toString();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child(tribe);
        mDatabaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mNaming.setText((String) dataSnapshot.child(mSurnaming.getText().toString()).child("name").getValue());
                mSurnaming.setText((String) dataSnapshot.child(mSurnaming.getText().toString()).child("surname").getValue());


                mSurnaming.setEnabled(false);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}



