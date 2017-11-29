package com.example.android.connectcodetribe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Admin on 11/24/2017.
 */

public class Admin_profile extends AppCompatActivity {

    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;

    DatabaseReference myRef;
    FirebaseUser currentUser;

    EditText profileName,profileSurname,profileCell_number,profileEmail,profileAge;
    Spinner profileGender,profileEthnicity,profileCodetribeName;
    Button profilePersonalInfobutton;

    public static final int GENDER_UNKNOWN = 0;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_FEMALE = 2;

    public static final int ETHNIC_BLACK = 1;
    public static final int ETHNIC_WHITE = 2;
    public static final int ETHNIC_COLORED = 3;
    public static final int ETHNIC_ASIAN = 4;

    public static final int TRIBE_SOWETO = 1;
    public static final int TRIBE_PRETORIA = 2;
    public static final int TRIBE_TEMBISA = 3;

    private int mGender = GENDER_UNKNOWN;
    private int mEthinicity = ETHNIC_BLACK;
    private int mTribe = TRIBE_SOWETO;
    private int mCodeTribe = TRIBE_SOWETO;

    private boolean mUserHasChanged = false;

    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mUserHasChanged = true;
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_editor);

        profileName = findViewById(R.id.profile_name_edit);
        profileSurname = findViewById(R.id.profile_surname_edit);
        profileGender = findViewById(R.id.profile_gender_spinner);
        profileEthnicity = findViewById(R.id.profile_ethnicity_spinner);
        profileCell_number = findViewById(R.id.profile_cell_number_edit);
        profileEmail = findViewById(R.id.profile_email_edit);
        profileAge = findViewById(R.id.profile_age_edit);
        profilePersonalInfobutton = findViewById(R.id.profile_personal_info_button);
        profileCodetribeName = findViewById(R.id.profile_code_tribe_name_spinner);

        profileEthnicity.setOnTouchListener(mTouchListener);
        setupEthnicitySpinner();
        profileGender.setOnTouchListener(mTouchListener);
        setupGenderSpinner();

        String tribe = profileCodetribeName.getSelectedItem().toString();

        myRef = FirebaseDatabase.getInstance().getReference().child(tribe);

        profileName.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        profileSurname.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        profileCell_number.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        profileEmail.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        profileAge.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});

        profilePersonalInfobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin_profile.this, Admin_codetribe_fragment.class));
            }

            });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                profileName.setText((String) dataSnapshot.child(currentUser.getUid()).child("name").getValue());
                profileAge.setText((String) dataSnapshot.child(currentUser.getUid()).child("age").getValue());
                profileCell_number.setText((String) dataSnapshot.child(currentUser.getUid()).child("mobileNo").getValue());
                profileSurname.setText((String) dataSnapshot.child(currentUser.getUid()).child("surname").getValue());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        }

        private void setupEthnicitySpinner() {
        ArrayAdapter ethnicitySpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_ethnicity_option, android.R.layout.simple_spinner_item);
        ethnicitySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        profileEthnicity.setAdapter(ethnicitySpinnerAdapter);
        profileEthnicity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selection = (String) adapterView.getItemAtPosition(i);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(R.string.black)) {
                        mEthinicity = ETHNIC_WHITE;

                    } else if (selection.equals(R.string.white)) {
                        mEthinicity = ETHNIC_WHITE;
                    } else if (selection.equals(R.string.indian)) {
                        mEthinicity = ETHNIC_ASIAN;
                    } else {
                        mEthinicity = ETHNIC_BLACK;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mEthinicity = ETHNIC_BLACK;

            }
        });

    }
    private void setupProfilecodeTribeSpinner(){
        ArrayAdapter profileCodeTribeSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_code_tribe_option,android.R.layout.simple_dropdown_item_1line);
        profileCodetribeName.setAdapter(profileCodeTribeSpinnerAdapter);
       profileCodetribeName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)){
                    if (selection.equals(R.string.tab_soweto)){
                        mCodeTribe = TRIBE_SOWETO;
                    }else if (selection.equals(R.string.tab_tembisa)){
                        mCodeTribe = TRIBE_TEMBISA;
                    }else{
                        mCodeTribe = TRIBE_PRETORIA;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mTribe = TRIBE_SOWETO;
            }
        });

    }



    private void setupGenderSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter statusSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        statusSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        profileGender.setAdapter(statusSpinnerAdapter);

        // Set the integer mSelected to the constant values
        profileGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.male))) {
                        mGender = GENDER_MALE;
                    } else if (selection.equals(getString(R.string.female))) {
                        mGender = GENDER_FEMALE;
                    } else {
                        mGender = GENDER_UNKNOWN;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = GENDER_UNKNOWN;
            }


        });
    }
}

