package com.example.android.connectcodetribe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.connectcodetribe.Model.Profile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by RP on 2017/09/02.
 */

public class UserProfileEditorActivity extends AppCompatActivity {
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;
    public static final int RC_PHOTO_PICKER = 2;
    private EditText mProfileNameEditText;
    private EditText mProfileSurnameEditText;
    private EditText mProfileEmailEditText;
    private EditText mProfileCellPhoneNumberEditText;
    private EditText mProfileQualificationEditText;
    private EditText mProfileInstitutionEditText;
    private EditText mProfileFacultycourseEditText;
    private EditText mProfileCompanyNameEditText;
    private EditText mProfileCompanyContactEditText;

    private EditText mProfileAgeEditText;
    private Button mProfileIntakePeriodButton;


    private CircleImageView mProfileCircleImage;
    DatabaseReference mDatabaseReference;
    StorageReference mStoragereference;
    Uri FilePathUri;
    public static final int GENDER_UNKNOWN = 0;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_FEMALE = 2;

    public static final int ETHNIC_BLACK = 1;
    public static final int ETHNIC_WHITE = 2;
    public static final int ETHNIC_COLORED= 3;
    public static final int ETHNIC_ASIAN = 4;


    private Spinner mProfileGenderSpinner, mProfileEthnicitySpinner,
            mProfileEmploymentStatusSpinner, mProfileSalarySpinner;
    private boolean mUserHasChanged = false;
    FirebaseUser currentUser;
    private int mStatus = GENDER_UNKNOWN;
    private  int mCodeTribe = ETHNIC_BLACK;
    String Database_Path = "All_Image_Uploads_Database";
    String Storage_Path = "All_Image_Uploads/";
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mUserHasChanged = true;
            return false;
        }
    };


    public static boolean isValidGender(int gender) {
        if (gender == GENDER_UNKNOWN || gender == GENDER_MALE || gender == GENDER_FEMALE) {
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("/UserProfiles/").child(currentUser.getUid());
        mStoragereference = FirebaseStorage.getInstance().getReference("/UserProfiles").child(currentUser.getUid());
        mProfileNameEditText= (EditText) findViewById(R.id.profile_name_edit_text);
        mProfileSurnameEditText = (EditText) findViewById(R.id.profile_surname_edit_text);
        mProfileAgeEditText = (EditText) findViewById(R.id.profile_age_edit_text);
        mProfileGenderSpinner = (Spinner) findViewById(R.id.profile_gender_spinner);
        mProfileEthnicitySpinner = (Spinner) findViewById(R.id.profile_ethnicity_spinner);
        mProfileCellPhoneNumberEditText = (EditText) findViewById(R.id.profile_cell_number_edit_text);
        mProfileEmailEditText = (EditText) findViewById(R.id.profile_email_edit_text);
        mProfileQualificationEditText = (EditText) findViewById(R.id.profile_qualification_type_edit_text);
        mProfileInstitutionEditText = (EditText) findViewById(R.id.profile_institution_edit_text);
        mProfileFacultycourseEditText  = (EditText) findViewById(R.id.profile_faculty_course_edit_text);
        mProfileEmploymentStatusSpinner = (Spinner) findViewById(R.id.profile_employment_status_spinner);
        mProfileCompanyNameEditText = (EditText) findViewById(R.id.profile_company_name_edit_text);
        mProfileIntakePeriodButton = (Button) findViewById(R.id.profile_intake_period_button);
        mProfileSalarySpinner = (Spinner) findViewById(R.id.profile_salary_spinner);
        mProfileCompanyContactEditText = (EditText) findViewById(R.id.profile_company_contact_edit_text);




        mProfileGenderSpinner.setOnTouchListener(mTouchListener);
        setupSpinner();
        mProfileEthnicitySpinner.setOnTouchListener(mTouchListener);
        codetribeSpinner();


        mProfileNameEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mProfileSurnameEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mProfileAgeEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mProfileEmailEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mProfileInstitutionEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});

    }

    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter statusSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        statusSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mProfileGenderSpinner.setAdapter(statusSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mProfileEthnicitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.male))) {
                        mStatus = GENDER_MALE;
                    } else if (selection.equals(getString(R.string.female))) {
                        mStatus = GENDER_FEMALE;
                    } else {
                        mStatus = GENDER_UNKNOWN;
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mStatus = GENDER_UNKNOWN;
            }
        });
    }

    private void codetribeSpinner(){
        ArrayAdapter codeTribeSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_ethnicity_option, android.R.layout.simple_spinner_item);
        codeTribeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mProfileGenderSpinner.setAdapter(codeTribeSpinnerAdapter);
        mProfileEthnicitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selection = (String) adapterView.getItemAtPosition(i);
                if (!TextUtils.isEmpty(selection)){
                    if (selection.equals(R.string.black)){
                        mCodeTribe = ETHNIC_WHITE;

                    }else if (selection.equals(R.string.white)){
                        mCodeTribe = ETHNIC_WHITE;
                    }else if (selection.equals(R.string.indian)){
                        mCodeTribe = ETHNIC_ASIAN;
                    }else {
                        mCodeTribe = ETHNIC_BLACK;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mCodeTribe = ETHNIC_BLACK;

            }
        });

    }



    // Creating Method to get the selected image file Extension from File Path URI.
}

