package com.example.android.connectcodetribe;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.connectcodetribe.Model.TribeMate;
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

/**
 * Created by RP on 2017/09/02.
 */

@TargetApi(Build.VERSION_CODES.N)
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
    private Button mProfileStartDatePickerButton;
    private EditText mProfileEmployeeCodeEditText;
    private Button mProfilecodeTribeSaveButton;
    private EditText mEmployeeCodeEditText;
    private Spinner mEmployeeTribeSpinner;
    private Button mEmployeeSearchButton;

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

    public static final int TRIBE_SOWETO = 1;
    public static final int TRIBE_PRETORIA = 2;
    public static final int TRIBE_TEMBISA = 3;



    public static final int IN_PROGRAM = 1;
    public static final int POST_PROGRAM = 2;

    private Uri filepath;


    private Spinner mProfileGenderSpinner, mProfileEthnicitySpinner,
            mProfileEmploymentStatusSpinner, mProfileSalarySpinner;

    private Spinner mProfileCodeTribeSpinner;
    private Spinner mProfileProgramStateSpinner;

    DatabaseReference MyRef;

    private boolean mUserHasChanged = false;
    FirebaseUser currentUser;
    private int mGender = GENDER_UNKNOWN;
    private int mEthinicity = ETHNIC_BLACK;
    private int mEmployment = STATUS_EMPLOYED;
    private int mSalary = SALARY_1;
    private int mCodeTribe = TRIBE_SOWETO;
    private int mStatus = IN_PROGRAM;
    private int mTribe = TRIBE_SOWETO;

Calendar mCalendar = Calendar.getInstance();
    int day = mCalendar.get(Calendar.DAY_OF_MONTH) ;
    int month = mCalendar.get(Calendar.MONTH);
    int year = mCalendar.get(Calendar.YEAR);
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
        mEmployeeCodeEditText = findViewById(R.id.edit_employee_code);
        mEmployeeTribeSpinner = findViewById(R.id.tribe_profile_option_spinner);
        mEmployeeSearchButton = findViewById(R.id.employee_search_button);
        mStoragereference = FirebaseStorage.getInstance().getReference("/users/");
        MyRef = FirebaseDatabase.getInstance().getReference("/users/");
        mProfileEmployeeCodeEditText = findViewById(R.id.profile_emc_edit_text);
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
        mProfileSalarySpinner = (Spinner) findViewById(R.id.profile_salary_spinner);
        mProfileCompanyContactEditText = (EditText) findViewById(R.id.profile_company_contact_edit_text);
        mProfilePersonaInfoButton = findViewById(R.id.profile_personal_info_button);
        mProfileEducationSaveButton = findViewById(R.id.profile_education_save_button);
        mProfileEmploymentSaveButton = findViewById(R.id.profile_employment_save_button);
        mProfileCodeTribeSpinner = findViewById(R.id.profile_code_tribe_name_spinner);
        mProfileStartDatePickerButton = findViewById(R.id.profile_intake_period_button);
        mProfileProgramStateSpinner = findViewById(R.id.profile_tribe_status_spinner);
        mProfileImageEditButton = findViewById(R.id.profile_image_edit_button);
        mProfileCircleImage = findViewById(R.id.profile_circle_image);
        mProfileCircleImage.setImageResource(R.drawable.man_user_user);
        mProfileEmploymentSaveButton.setEnabled(false);


        mProfilecodeTribeSaveButton = findViewById(R.id.profile_code_tribe_save_button);
        mProfilecodeTribeSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        mProfileGenderSpinner.setOnTouchListener(mTouchListener);
        setupGenderSpinner();
        mProfileEthnicitySpinner.setOnTouchListener(mTouchListener);
        setupEthnicitySpinner();
        mProfileEmploymentStatusSpinner.setOnTouchListener(mTouchListener);
        setupEmploymentStatusSpinner();
        mProfileSalarySpinner.setOnTouchListener(mTouchListener);
        setupSalarySpinner();
        mProfileCodeTribeSpinner.setOnTouchListener(mTouchListener);
        setupCodeTribeSpinner();
        mProfileProgramStateSpinner.setOnTouchListener(mTouchListener);
        setupProgramStatusSpinner();
        mEmployeeTribeSpinner.setOnTouchListener(mTouchListener);
        setupEmployeeTribeSpinner();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference(mEmployeeTribeSpinner.getSelectedItem().toString());

        mProfileNameEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mProfileSurnameEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mProfileAgeEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mProfileEmailEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mProfileQualificationEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mProfileInstitutionEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mProfileFacultyCourseEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mProfileCompanyNameEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mProfileCompanyContactEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mEmployeeCodeEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mEmployeeCodeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length()>0){
                    mProfileEmploymentSaveButton.setEnabled(true);

                }else {
                    mProfileEmploymentSaveButton.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mProfileStartDatePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(0);
            }
        });

        mProfileEmploymentSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
                startActivity(new Intent(UserProfileEditorActivity.this, DifferentCodetribeTabs.class));



        }});
        mEmployeeSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println(mEmployeeCodeEditText.getText().toString());
                mDatabaseReference.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mProfileNameEditText.setText((String) dataSnapshot.child(mEmployeeCodeEditText.getText().toString()).child("name").getValue());
                        mProfileSurnameEditText.setText((String) dataSnapshot.child(mEmployeeCodeEditText.getText().toString()).child("surname").getValue());
                        mProfileAgeEditText.setText((String) dataSnapshot.child(mEmployeeCodeEditText.getText().toString()).child("age").getValue());
                        mProfileCellPhoneNumberEditText.setText((String) dataSnapshot.child(mEmployeeCodeEditText.getText().toString()).child("mobileNo").getValue());
                        mProfileEmployeeCodeEditText.setText((String) dataSnapshot.child(mEmployeeCodeEditText.getText().toString()).child("employeeCode").getValue());
                        mProfileQualificationEditText.setText((String) dataSnapshot.child(mEmployeeCodeEditText.getText().toString()).child("highestQualification").getValue());
                        mProfileInstitutionEditText.setText((String) dataSnapshot.child(mEmployeeCodeEditText.getText().toString()).child("qualificationInstitution").getValue());
                        mProfileFacultyCourseEditText.setText((String) dataSnapshot.child(mEmployeeCodeEditText.getText().toString()).child("qualificationDescription").getValue());
                        Glide.with(mProfileCircleImage.getContext())
                                .load((String) dataSnapshot.child(mEmployeeCodeEditText.getText().toString()).child("profile_picture").getValue())
                                .into(mProfileCircleImage);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

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

        MyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mProfileNameEditText.setText((String) dataSnapshot.child(currentUser.getUid()).child("name").getValue());
                mProfileSurnameEditText.setText((String) dataSnapshot.child(currentUser.getUid()).child("surname").getValue());
                mProfileAgeEditText.setText((String) dataSnapshot.child(currentUser.getUid()).child("age").getValue());
                mProfileCellPhoneNumberEditText.setText((String) dataSnapshot.child(currentUser.getUid()).child("mobileNo").getValue());
                mProfileEmployeeCodeEditText.setText((String) dataSnapshot.child(currentUser.getUid()).child("employeeCode").getValue());
                mProfileQualificationEditText.setText((String) dataSnapshot.child(currentUser.getUid()).child("highestQualification").getValue());
                mProfileInstitutionEditText.setText((String) dataSnapshot.child(currentUser.getUid()).child("qualificationInstitution").getValue());
                mProfileFacultyCourseEditText.setText((String) dataSnapshot.child(currentUser.getUid()).child("qualificationDescription").getValue());
                mEmployeeCodeEditText.setText((String) dataSnapshot.child(currentUser.getUid()).child("employeeCode").getValue());
                mProfileCellPhoneNumberEditText.setText((String) dataSnapshot.child(currentUser.getUid()).child("mobileNo").getValue());
                mProfileStartDatePickerButton.setText((String) dataSnapshot.child(currentUser.getUid()).child("startDate").getValue());
                mProfileCompanyNameEditText.setText((String) dataSnapshot.child(currentUser.getUid()).child("companyName").getValue());
                mProfileCompanyContactEditText.setText((String) dataSnapshot.child(currentUser.getUid()).child("companyContactDetails").getValue());
                mProfileEmailEditText.setText((String) dataSnapshot.child(currentUser.getUid()).child("emailAddress").getValue());
                Glide.with(mProfileCircleImage.getContext())
                        .load((String) dataSnapshot.child(currentUser.getUid()).child("profile_picture").getValue())
                        .into(mProfileCircleImage);




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






    }


    private void setupProgramStatusSpinner(){
        ArrayAdapter programStatusSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_status_options, android.R.layout.simple_spinner_item);

        programStatusSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mProfileProgramStateSpinner.setAdapter(programStatusSpinnerAdapter);
        mProfileProgramStateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (selection.equals(R.string.in_program)){
                    mStatus = IN_PROGRAM;
                }else{
                    mStatus = POST_PROGRAM;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mStatus = IN_PROGRAM;

            }
        });

    }
    private void setupEmployeeTribeSpinner(){
        ArrayAdapter employeeTribeSinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_employee_options, android.R.layout.simple_spinner_item);
        employeeTribeSinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mEmployeeTribeSpinner.setAdapter(employeeTribeSinnerAdapter);
        mEmployeeTribeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (selection.equals(R.string.soweto)){
                    mTribe = TRIBE_SOWETO;
                }
                else if (selection.equals(R.string.pretoria)){
                    mTribe = TRIBE_PRETORIA;
                }
                else{
                    mTribe = TRIBE_TEMBISA;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mTribe = TRIBE_SOWETO;
            }
        });
    }


    private void setupCodeTribeSpinner(){
        ArrayAdapter codetribeNameSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_code_tribe_option, android.R.layout.simple_spinner_item);

        codetribeNameSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mProfileCodeTribeSpinner.setAdapter(codetribeNameSpinnerAdapter);

        mProfileCodeTribeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)){
                    if (selection.equals(R.string.tab_soweto)){
                        mCodeTribe = TRIBE_SOWETO;

                    }else if (selection.equals(R.string.tab_pretoria)){
                        mCodeTribe = TRIBE_PRETORIA;
                    }
                }
                else {
                    mCodeTribe = TRIBE_TEMBISA;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mCodeTribe = TRIBE_SOWETO;

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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void uploadImage() {
        if (filepath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading Information...");
            progressDialog.show();

            StorageReference ref = mStoragereference.child("profile_images");


            ref.putFile(filepath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadUri = taskSnapshot.getDownloadUrl();
                            final TribeMate tribeMate = new TribeMate();

                            tribeMate.setProfileImage(downloadUri.toString());

                            tribeMate.setCodeTribeLocation(mProfileCodeTribeSpinner.getSelectedItem().toString());
                            tribeMate.setCodeTribeProgramStatus(mProfileProgramStateSpinner.getSelectedItem().toString());
                            tribeMate.setEmployeeCode(mProfileEmployeeCodeEditText.getText().toString());

                            tribeMate.setQualification(mProfileQualificationEditText.getText().toString());
                            tribeMate.setInstitute(mProfileInstitutionEditText.getText().toString());
                            tribeMate.setDesc(mProfileFacultyCourseEditText.getText().toString());

                            tribeMate.setName(mProfileNameEditText.getText().toString());
                            tribeMate.setSurname(mProfileSurnameEditText.getText().toString());
                            tribeMate.setAge(mProfileAgeEditText.getText().toString());
                            tribeMate.setGender(mProfileGenderSpinner.getSelectedItem().toString());
                            tribeMate.setEthnicity(mProfileEthnicitySpinner.getSelectedItem().toString());
                            tribeMate.setMobile(mProfileCellPhoneNumberEditText.getText().toString());
                            tribeMate.setEmail(mProfileEmailEditText.getText().toString());
                            tribeMate.setEmploymentStatus(mProfileEmploymentStatusSpinner.getSelectedItem().toString());
                            tribeMate.setCompanyName(mProfileCompanyNameEditText.getText().toString());
                            tribeMate.setCompanyContactNumber(mProfileCompanyContactEditText.getText().toString());
                            tribeMate.setSalary(mProfileSalarySpinner.getSelectedItem().toString());
                            tribeMate.setStartDate(mProfileStartDatePickerButton.getText().toString());

                            MyRef.child(currentUser.getUid()).setValue(tribeMate.toMap());
                            mDatabaseReference.child(mEmployeeCodeEditText.getText().toString()).setValue(tribeMate.toMap()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    try {
                                        Bitmap bitmap  = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                                        mProfileCircleImage.setImageBitmap(bitmap);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                            progressDialog.dismiss();
                            Toast.makeText(UserProfileEditorActivity.this, "Image Upload Successful", Toast.LENGTH_SHORT).show();




                            // Creating Method to get the selected image file Extension from File Path URI.
                        }

                    });
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, datePickerListener, year, month, day);
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
            day = selectedDay;
            month = selectedMonth;
            year = selectedYear;
            mProfileStartDatePickerButton.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);

        }
    };
}


