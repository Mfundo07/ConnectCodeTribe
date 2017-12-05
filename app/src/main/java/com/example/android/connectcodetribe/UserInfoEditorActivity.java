package com.example.android.connectcodetribe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;

import com.example.android.connectcodetribe.Model.TribeMate;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

/**
 * Created by Admin on 12/4/2017.
 */

public class UserInfoEditorActivity extends AppCompatActivity {
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;


    private EditText mRegisterNameEditText;
    private EditText mRegisterSurnameEditText;
    private EditText mRegisterEmailEditText;
    private EditText mRegisterCellPhoneNumberEditText;
    private EditText mRegisterQualificationEditText;
    private EditText mRegisterInstitutionEditText;
    private EditText mRegisterFacultyCourseEditText;
    private EditText mRegisterCompanyNameEditText;
    private EditText mRegisterCompanyContactEditText;
    private EditText mRegisterCodeTribeEditText;
    private EditText mRegisterEmployeeCodeEditText;
    private FloatingActionButton mRegisterListButton;
    private EditText mRegisterGenderEditText, mRegisterEthnicityEditText,    mRegisterProgramStatusEditText;

    private EditText mRegisterAgeEditText;


    DatabaseReference mDatabaseReference;
    StorageReference mStoragereference;


    String mName;
    String mStatus;
    String mCodeTribe;
    String mSurname;
    String mBio;
    String mGender;
    String mEMC;
    String mEthnicity;
    String mAge;
    String mEmail;
    String mMobile;
    String mImage;
    String mSalary;
    String mCompanyName;
    String mCompanyContact;
    String mStartDate;
    String mUserCode;
    String mTribeUnderline;
    String mQualification;
    String mInstitution;
    String mFaculty;








    DatabaseReference MyRef;
    FirebaseUser currentUser;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_editor);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        mRegisterEmployeeCodeEditText = findViewById(R.id.register_emc_edit_text);
        mRegisterNameEditText = (EditText) findViewById(R.id.register_name_edit_text);
        mRegisterSurnameEditText = (EditText) findViewById(R.id.register_surname_edit_text);
        mRegisterAgeEditText = (EditText) findViewById(R.id.register_age_edit_text);
        mRegisterCellPhoneNumberEditText = (EditText) findViewById(R.id.register_cell_number_edit_text);
        mRegisterEmailEditText = (EditText) findViewById(R.id.register_email_edit_text);
        mRegisterQualificationEditText = (EditText) findViewById(R.id.register_qualification_type_edit_text);
        mRegisterInstitutionEditText = (EditText) findViewById(R.id.register_institution_edit_text);
        mRegisterFacultyCourseEditText = (EditText) findViewById(R.id.register_faculty_course_edit_text);
        mRegisterGenderEditText = findViewById(R.id.register_gender_edit_text);
        mRegisterProgramStatusEditText = findViewById(R.id.register_tribe_status_spinner);
        mRegisterEthnicityEditText = findViewById(R.id.register_ethnicity_edit_text);
        mRegisterCodeTribeEditText = findViewById(R.id.register_code_tribe_name_spinner);
        mRegisterListButton = findViewById(R.id.profile_list_back_fab_button);

        //Setup layout manager to a horizontal scrolling recyclerView

        //Setup layout manager to a staggered scrolling recyclerView

        mName = getIntent().getExtras().getString("Name");
        mStatus = getIntent().getExtras().getString("Status");
        mCodeTribe = getIntent().getExtras().getString("CodeTribe");
        mSurname = getIntent().getExtras().getString("Surname");
        mImage = getIntent().getExtras().getString("image");
        mBio = getIntent().getExtras().getString("bio");
        mStartDate = getIntent().getExtras().getString("start_date");


        mGender = getIntent().getExtras().getString("Gender");
        mEMC = getIntent().getExtras().getString("Employee_code");
        mEthnicity = getIntent().getExtras().getString("Ethnicity");
        mAge = getIntent().getExtras().getString("Age");
        mEmail = getIntent().getExtras().getString("Email");
        mMobile = getIntent().getExtras().getString("Mobile");
        mSalary = getIntent().getExtras().getString("salary");
        mCompanyName = getIntent().getExtras().getString("company_name");
        mCompanyContact = getIntent().getExtras().getString("company_contact");

        mUserCode = getIntent().getExtras().getString("user_code");
        mTribeUnderline = getIntent().getExtras().getString("tribe_underline");
        mQualification = getIntent().getExtras().getString("qualification");
        mInstitution = getIntent().getExtras().getString("institution");
        mFaculty = getIntent().getExtras().getString("faculty");

        mRegisterNameEditText.setText(mName);
        mRegisterSurnameEditText.setText(mSurname);
        mRegisterAgeEditText.setText(mAge);
        mRegisterEthnicityEditText.setText(mEthnicity);
        mRegisterProgramStatusEditText.setText(mStatus);
        mRegisterEmailEditText.setText(mEmail);
        mRegisterGenderEditText.setText(mGender);
        mRegisterEmployeeCodeEditText.setText(mEMC);
        mRegisterCellPhoneNumberEditText.setText(mMobile);
        mRegisterQualificationEditText.setText(mQualification);
        mRegisterInstitutionEditText.setText(mInstitution);
        mRegisterFacultyCourseEditText.setText(mFaculty);
        mRegisterCodeTribeEditText.setText(mCodeTribe);















        mRegisterNameEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mRegisterSurnameEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mRegisterAgeEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mRegisterEmailEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mRegisterQualificationEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mRegisterInstitutionEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mRegisterFacultyCourseEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});


        mRegisterListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseReference = FirebaseDatabase.getInstance().getReference(mCodeTribe);
                MyRef = FirebaseDatabase.getInstance().getReference().child(mCodeTribe);
                TribeMate tribeMate = new TribeMate();
                tribeMate.setCodeTribeLocation(mRegisterCodeTribeEditText.getText().toString());
                tribeMate.setCodeTribeProgramStatus(mRegisterProgramStatusEditText.getText().toString());
                tribeMate.setEmployeeCode(mRegisterEmployeeCodeEditText.getText().toString());
                tribeMate.setQualification(mRegisterQualificationEditText.getText().toString());
                tribeMate.setInstitute(mRegisterInstitutionEditText.getText().toString());
                tribeMate.setDesc(mRegisterInstitutionEditText.getText().toString());
                tribeMate.setName(mRegisterNameEditText.getText().toString());
                tribeMate.setSurname(mRegisterSurnameEditText.getText().toString());
                tribeMate.setAge(Long.valueOf(mRegisterAgeEditText.getText().toString()));
                tribeMate.setGender(mRegisterGenderEditText.getText().toString());
                tribeMate.setEthnicity(mRegisterEthnicityEditText.getText().toString());
                tribeMate.setMobile(mRegisterCellPhoneNumberEditText.getText().toString());
                tribeMate.setEmail(mRegisterEmailEditText.getText().toString());
                mDatabaseReference.child(mEMC).setValue(tribeMate.toMap()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        startActivity(new Intent(UserInfoEditorActivity.this, DifferentCodetribeTabs.class));
                    }
                });
            }
        });









}
}
