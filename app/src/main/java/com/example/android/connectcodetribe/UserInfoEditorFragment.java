package com.example.android.connectcodetribe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.android.connectcodetribe.Model.TribeMate;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

/**
 * Created by Admin on 1/22/2018.
 */

public class UserInfoEditorFragment extends Fragment {
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

    DatabaseReference MyRef;
    FirebaseUser currentUser;

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


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = this.getArguments();

        mName = args.getString("Name");
        mStatus = args.getString("Status");
        mCodeTribe = args.getString("CodeTribe");
        mSurname = args.getString("Surname");
        mImage = args.getString("image");
        mBio = args.getString("bio");
        mStartDate = args.getString("start_date");


        mGender = args.getString("Gender");
        mEMC = args.getString("Employee_code");
        mEthnicity = args.getString("Ethnicity");
        mAge = args.getString("Age");
        mEmail = args.getString("Email");
        mMobile = args.getString("Mobile");
        mSalary = args.getString("salary");
        mCompanyName = args.getString("company_name");
        mCompanyContact = args.getString("company_contact");

        mUserCode = args.getString("user_code");
        mTribeUnderline = args.getString("tribe_underline");
        mQualification = args.getString("qualification");
        mInstitution = args.getString("institution");
        mFaculty = args.getString("faculty");
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.welcome_slide3, container, false);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        mRegisterEmployeeCodeEditText = rootView.findViewById(R.id.register_emc_edit_text);
        mRegisterNameEditText = (EditText) rootView.findViewById(R.id.register_name_edit_text);
        mRegisterSurnameEditText = (EditText) rootView.findViewById(R.id.register_surname_edit_text);
        mRegisterAgeEditText = (EditText) rootView.findViewById(R.id.register_age_edit_text);
        mRegisterCellPhoneNumberEditText = (EditText) rootView.findViewById(R.id.register_cell_number_edit_text);
        mRegisterEmailEditText = (EditText) rootView.findViewById(R.id.register_email_edit_text);
        mRegisterQualificationEditText = (EditText) rootView.findViewById(R.id.register_qualification_type_edit_text);
        mRegisterInstitutionEditText = (EditText) rootView.findViewById(R.id.register_institution_edit_text);
        mRegisterFacultyCourseEditText = (EditText) rootView.findViewById(R.id.register_faculty_course_edit_text);
        mRegisterGenderEditText = rootView.findViewById(R.id.register_gender_edit_text);
        mRegisterProgramStatusEditText = rootView.findViewById(R.id.register_tribe_status_spinner);
        mRegisterEthnicityEditText = rootView.findViewById(R.id.register_ethnicity_edit_text);
        mRegisterCodeTribeEditText = rootView.findViewById(R.id.register_code_tribe_name_spinner);
        mRegisterListButton = rootView.findViewById(R.id.profile_list_back_fab_button);



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
                mDatabaseReference = FirebaseDatabase.getInstance().getReference("/requested/");
                MyRef = FirebaseDatabase.getInstance().getReference("/registered/");
                TribeMate tribeMate = new TribeMate();
                tribeMate.setCodeTribeLocation(mRegisterCodeTribeEditText.getText().toString());
                tribeMate.setCodeTribeProgramStatus(mRegisterProgramStatusEditText.getText().toString());
                tribeMate.setEmployeeCode(mRegisterEmployeeCodeEditText.getText().toString());
                tribeMate.setQualification(mRegisterQualificationEditText.getText().toString());
                tribeMate.setInstitute(mRegisterInstitutionEditText.getText().toString());
                tribeMate.setDesc(mRegisterFacultyCourseEditText.getText().toString());
                tribeMate.setName(mRegisterNameEditText.getText().toString());
                tribeMate.setSurname(mRegisterSurnameEditText.getText().toString());
                tribeMate.setAge(Long.valueOf(mRegisterAgeEditText.getText().toString()));
                tribeMate.setGender(mRegisterGenderEditText.getText().toString());
                tribeMate.setEthnicity(mRegisterEthnicityEditText.getText().toString());
                tribeMate.setMobile(mRegisterCellPhoneNumberEditText.getText().toString());

                tribeMate.setEmail(mRegisterEmailEditText.getText().toString());
                MyRef.child(mRegisterEmployeeCodeEditText.getText().toString()).setValue(tribeMate.toMap());
                mDatabaseReference.child(mCodeTribe).child(mEMC).setValue(tribeMate.toMap()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });

            }});


        return rootView;
    }
}
