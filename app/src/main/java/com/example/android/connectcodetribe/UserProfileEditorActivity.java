package com.example.android.connectcodetribe;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.connectcodetribe.Model.Education;
import com.example.android.connectcodetribe.Model.Employment;
import com.example.android.connectcodetribe.Model.TribeMate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

/**
 * Created by RP on 2017/09/02.
 */

public class UserProfileEditorActivity extends AppCompatActivity {
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;


    private static final int GALLERY_REQUEST = 1;
    private static final int CAMERA_REQUEST_CODE = 1;
    public static final int RC_PHOTO_PICKER = 2;

    private final int PICK_IMAGE_REQUEST = 71;

    private EditText mProfileNameEditText;
    private EditText mProfileSurnameEditText;
    private EditText mProfileEmailEditText;
    private EditText mProfileCellPhoneNumberEditText;
    private EditText mProfileQualificationEditText;
    private EditText mProfileInstitutionEditText;
    private EditText mProfileFacultyCourseEditText;
    private EditText mProfileCompanyNameEditText;
    private EditText mProfileCompanyContactEditText;

    private EditText mProfileAgeEditText;
    private Button mProfileIntakePeriodButton, mProfilePersonaInfoButton, mProfileEducationSaveButton,
            mProfileEmploymentSaveButton, mProfileImageEditButton, mProfileImageSaveButton;


    private ImageView mProfileCircleImage;
    DatabaseReference mDatabaseReference;
    StorageReference mStoragereference;
    Uri FilePathUri;
    public static final int GENDER_UNKNOWN = 0;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_FEMALE = 2;

    public static final int ETHNIC_BLACK = 1;
    public static final int ETHNIC_WHITE = 2;
    public static final int ETHNIC_COLORED = 3;
    public static final int ETHNIC_ASIAN = 4;

    public static final int STATUS_EMPLOYED = 1;
    public static final int STATUS_UNEMPLOYED = 2;
    public static final int STATUS_SELF_EMPLOYED = 3;


    public static final int SALARY_1 = 1;
    public static final int SALARY_2 = 2;
    public static final int SALARY_3 = 3;
    public static final int SALARY_4 = 4;
    public static final int SALARY_5 = 5;
    public static final int SALARY_6 = 6;

    private Uri filepath;


    private Spinner mProfileGenderSpinner, mProfileEthnicitySpinner,
            mProfileEmploymentStatusSpinner, mProfileSalarySpinner;
    private boolean mUserHasChanged = false;
    FirebaseUser currentUser;
    private int mGender = GENDER_UNKNOWN;
    private int mEthinicity = ETHNIC_BLACK;
    private int mEmployment = STATUS_EMPLOYED;
    private int mSalary = SALARY_1;
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
        mProfileNameEditText = (EditText) findViewById(R.id.profile_name_edit_text);
        mProfileSurnameEditText = (EditText) findViewById(R.id.profile_surname_edit_text);
        mProfileAgeEditText = (EditText) findViewById(R.id.profile_age_edit_text);
        mProfileGenderSpinner = (Spinner) findViewById(R.id.profile_gender_spinner);
        mProfileEthnicitySpinner = (Spinner) findViewById(R.id.profile_ethnicity_spinner);
        mProfileCellPhoneNumberEditText = (EditText) findViewById(R.id.profile_cell_number_edit_text);
        mProfileEmailEditText = (EditText) findViewById(R.id.profile_email_edit_text);
        mProfileQualificationEditText = (EditText) findViewById(R.id.profile_qualification_type_edit_text);
        mProfileInstitutionEditText = (EditText) findViewById(R.id.profile_institution_edit_text);
        mProfileFacultyCourseEditText = (EditText) findViewById(R.id.profile_faculty_course_edit_text);
        mProfileEmploymentStatusSpinner = (Spinner) findViewById(R.id.profile_employment_status_spinner);
        mProfileCompanyNameEditText = (EditText) findViewById(R.id.profile_company_name_edit_text);
        mProfileIntakePeriodButton = (Button) findViewById(R.id.profile_intake_period_button);
        mProfileSalarySpinner = (Spinner) findViewById(R.id.profile_salary_spinner);
        mProfileCompanyContactEditText = (EditText) findViewById(R.id.profile_company_contact_edit_text);
        mProfilePersonaInfoButton = findViewById(R.id.profile_personal_info_button);
        mProfileEducationSaveButton = findViewById(R.id.profile_education_save_button);
        mProfileEmploymentSaveButton = findViewById(R.id.profile_employment_save_button);
        mProfileImageEditButton = findViewById(R.id.profile_image_edit_button);
        mProfileImageSaveButton = findViewById(R.id.profile_image_save_button);
        mProfileCircleImage = findViewById(R.id.profile_circle_image);
        mProfileImageSaveButton.setEnabled(false);
        mProfileImageSaveButton.setVisibility(View.INVISIBLE);


        mProfileGenderSpinner.setOnTouchListener(mTouchListener);
        setupGenderSpinner();
        mProfileEthnicitySpinner.setOnTouchListener(mTouchListener);
        setupEthnicitySpinner();
        mProfileEmploymentStatusSpinner.setOnTouchListener(mTouchListener);
        setupEmploymentStatusSpinner();
        mProfileSalarySpinner.setOnTouchListener(mTouchListener);
        setupSalarySpinner();

        mProfileNameEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mProfileSurnameEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mProfileAgeEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mProfileEmailEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mProfileQualificationEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mProfileInstitutionEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mProfileFacultyCourseEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mProfileCompanyNameEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mProfileCompanyContactEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});

        mProfilePersonaInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TribeMate tribeMate = new TribeMate();
                tribeMate.setName(mProfileNameEditText.getText().toString());
                tribeMate.setSurname(mProfileSurnameEditText.getText().toString());
                tribeMate.setAge(mProfileAgeEditText.getText().toString());
                tribeMate.setGender(mProfileGenderSpinner.getSelectedItem().toString());
                tribeMate.setEthnicity(mProfileEthnicitySpinner.getSelectedItem().toString());
                tribeMate.setMobile(mProfileCellPhoneNumberEditText.getText().toString());
                tribeMate.setEmail(mProfileEmailEditText.getText().toString());
                mDatabaseReference.child("personal_details").setValue(tribeMate.toMap()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @TargetApi(Build.VERSION_CODES.M)
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            mProfileNameEditText.setText(tribeMate.getName());
                            mProfileSurnameEditText.setText(tribeMate.getSurname());
                            mProfileAgeEditText.setText(tribeMate.getAge());
                            mProfileCellPhoneNumberEditText.setText(tribeMate.getMobile());
                            mProfileEmailEditText.setText(tribeMate.getEmail());
                            Toast.makeText(getApplicationContext(), "Personal Details updated", Toast.LENGTH_SHORT).show();
                            mProfilePersonaInfoButton.setEnabled(false);
                            mProfilePersonaInfoButton.setTextColor(getColor(R.color.grey_300));
                        } else {
                            task.getException().printStackTrace();
                        }
                    }
                });

            }
        });


        mProfileEducationSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Education education = new Education();
                education.setQualification(mProfileQualificationEditText.getText().toString());
                education.setInstitute(mProfileInstitutionEditText.getText().toString());
                education.setDesc(mProfileFacultyCourseEditText.getText().toString());
                mDatabaseReference.child("education").setValue(education.toMap()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @TargetApi(Build.VERSION_CODES.M)
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            mProfileQualificationEditText.setText(education.getQualification());
                            mProfileInstitutionEditText.setText(education.getInstitute());
                            mProfileFacultyCourseEditText.setText(education.getDesc());
                            Toast.makeText(getApplicationContext(), "Education updated", Toast.LENGTH_SHORT).show();
                            mProfileEducationSaveButton.setEnabled(false);
                            mProfileEducationSaveButton.setTextColor(getColor(R.color.grey_300));
                        } else {
                            task.getException().printStackTrace();
                        }
                    }
                });
            }
        });

        mProfileEmploymentSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Employment employment = new Employment();
                employment.setEmploymenyStatus(mProfileEmploymentStatusSpinner.getSelectedItem().toString());
                employment.setEmploymenyStatus(mProfileCompanyNameEditText.getText().toString());
                employment.setEmploymenyStatus(mProfileSalarySpinner.getSelectedItem().toString());
                employment.setSalary(mProfileCompanyContactEditText.getText().toString());
                mDatabaseReference.child("employment").setValue(employment.toMap()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @TargetApi(Build.VERSION_CODES.M)
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            mProfileCompanyNameEditText.setText(employment.getCompanyName());
                            mProfileCompanyContactEditText.setText(employment.getSalary());
                            Toast.makeText(getApplicationContext(), "Employment updated", Toast.LENGTH_SHORT).show();
                            mProfileEmploymentSaveButton.setEnabled(false);
                            mProfileEmploymentSaveButton.setTextColor(getColor(R.color.grey_300));
                        } else {
                            task.getException().printStackTrace();
                        }
                    }
                });
            }
        });


        mProfileImageEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();

            }
        });
        mProfileImageSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
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
        mProfileGenderSpinner.setAdapter(statusSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mProfileGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = GENDER_UNKNOWN;
            }
        });
    }

    private void setupEthnicitySpinner() {
        ArrayAdapter ethnicitySpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_ethnicity_option, android.R.layout.simple_spinner_item);
        ethnicitySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mProfileEthnicitySpinner.setAdapter(ethnicitySpinnerAdapter);
        mProfileEthnicitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void setupEmploymentStatusSpinner() {
        ArrayAdapter employmentStatusSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_employment_option,
                android.R.layout.simple_dropdown_item_1line);
        mProfileEmploymentStatusSpinner.setAdapter(employmentStatusSpinnerAdapter);
        mProfileEmploymentStatusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selection = (String) adapterView.getItemAtPosition(i);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(R.string.employed_status)) {
                        mEmployment = STATUS_EMPLOYED;
                    } else if (selection.equals(R.string.unemployed_status)) {
                        mEmployment = STATUS_UNEMPLOYED;
                    } else {
                        mEmployment = STATUS_SELF_EMPLOYED;
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mEmployment = STATUS_UNEMPLOYED;

            }
        });

    }

    private void setupSalarySpinner() {
        ArrayAdapter salarySpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_salary_option,
                android.R.layout.simple_dropdown_item_1line);
        mProfileSalarySpinner.setAdapter(salarySpinnerAdapter);
        mProfileSalarySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selection = (String) adapterView.getItemAtPosition(i);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(R.string.salary_1)) {
                        mSalary = SALARY_1;
                    } else if (selection.equals(R.string.salary_2)) {
                        mSalary = SALARY_2;
                    } else if (selection.equals(R.string.salary_3)) {
                        mSalary = SALARY_3;
                    } else if (selection.equals(R.string.salary_4)) {
                        mSalary = SALARY_4;
                    } else if (selection.equals(R.string.salary_5)) {
                        mSalary = SALARY_5;
                    } else {
                        mSalary = SALARY_6;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                mSalary = SALARY_1;

            }
        });
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filepath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                mProfileCircleImage.setImageBitmap(bitmap);
                mProfileImageSaveButton.setEnabled(true);
                mProfileImageSaveButton.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void uploadImage() {
        if (filepath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading Image.....");
            progressDialog.show();

            StorageReference ref = mStoragereference.child("profile_images");
            ref.putFile(filepath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadUri = taskSnapshot.getDownloadUrl();
                            TribeMate item = new TribeMate();
                            item.setProfileImage(downloadUri.toString());
                            mDatabaseReference.child("personal_details").setValue(item);

                            progressDialog.dismiss();
                            Toast.makeText(UserProfileEditorActivity.this, "Image Upload Successful", Toast.LENGTH_SHORT).show();
                            mProfileImageSaveButton.setEnabled(false);
                            mProfileImageSaveButton.setVisibility(View.INVISIBLE);
                            Bitmap bitmap = null;
                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                                mProfileCircleImage.setImageBitmap(bitmap);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }



                            // Creating Method to get the selected image file Extension from File Path URI.
                        }
                    });
        }
    }
}


