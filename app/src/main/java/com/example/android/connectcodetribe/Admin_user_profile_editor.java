package com.example.android.connectcodetribe;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.connectcodetribe.Adapters.ProjectsHorizontalAdapter;
import com.example.android.connectcodetribe.Model.TribeMate;
import com.google.android.gms.tasks.OnSuccessListener;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RP on 2017/09/02.
 */

@TargetApi(Build.VERSION_CODES.N)
public class Admin_user_profile_editor extends AppCompatActivity {
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;
    private EditText mAdmin_name_edit_text;
    private EditText mAdmin_surname_edit_text;
    private EditText mAdmin_age_edit_text;
    private EditText mAdmin_cell_number_edit_text;
    private EditText mAdmin_emc_edit_text;
    private EditText mAdmin_email_edit_text;
    private FloatingActionButton mProfileListButton;
    private Button mAdmin_code_tribe_save_button,mAdmin_personal_search_button;
    private TextView mBio;
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
    public static final int IN_PROGRAM = 1;
    private Uri filepath;
    RecyclerView mProjectsRecyclerView;
    ProjectsHorizontalAdapter mProjectsAdapter;
    private Spinner mAdmin_gender_spinner, mAdmin_ethnicity_spinner,
            mAdmin_code_tribe_name_spinner, mAdmin_tribe_status_spinner;


    DatabaseReference MyRef;

    List<TribeMate> projects = new ArrayList<>();
    private boolean mUserHasChanged = false;

    private boolean expandable = true;
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
    private ImageButton ProjectImage;
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
        setContentView(R.layout.activity_admin_user_editor);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        mStoragereference = FirebaseStorage.getInstance().getReference("/users/");
        MyRef = FirebaseDatabase.getInstance().getReference("/users/");
// Updated upstream
        mAdmin_name_edit_text = (EditText) findViewById(R.id.admin_name_edit_text);
        mAdmin_surname_edit_text = (EditText) findViewById(R.id.admin_surname_edit_text);
        mAdmin_gender_spinner = (Spinner) findViewById(R.id.admin_gender_spinner);
        mAdmin_ethnicity_spinner = (Spinner) findViewById(R.id.admin_ethnicity_spinner);
        mAdmin_age_edit_text = (EditText) findViewById(R.id.admin_age_edit_text);
        mAdmin_cell_number_edit_text = (EditText) findViewById(R.id.admin_cell_number_edit_text);
        mAdmin_email_edit_text = (EditText) findViewById(R.id.admin_email_edit_text);
        mAdmin_emc_edit_text = (EditText) findViewById(R.id.admin_emc_edit_text);
        mAdmin_code_tribe_name_spinner = (Spinner) findViewById(R.id.admin_code_tribe_name_spinner);
        mAdmin_tribe_status_spinner = (Spinner) findViewById(R.id.admin_tribe_status_spinner);
        mAdmin_personal_search_button = findViewById(R.id.admin_personal_search_button);
        mAdmin_code_tribe_save_button = findViewById(R.id.admin_code_tribe_save_button);
//
        mAdmin_name_edit_text = (EditText) findViewById(R.id.profile_name_edit_text);
        mAdmin_surname_edit_text = (EditText) findViewById(R.id.profile_surname_edit_text);
        mAdmin_gender_spinner = (Spinner) findViewById(R.id.profile_gender_spinner);
        mAdmin_ethnicity_spinner = (Spinner) findViewById(R.id.profile_ethnicity_spinner);
        mAdmin_age_edit_text = (EditText) findViewById(R.id.profile_age_edit_text);
        mAdmin_cell_number_edit_text = (EditText) findViewById(R.id.profile_cell_number_edit_text);
        mAdmin_email_edit_text = (EditText) findViewById(R.id.profile_email_edit_text);
        mAdmin_emc_edit_text = (EditText) findViewById(R.id.profile_emc_edit_text);
        mAdmin_code_tribe_name_spinner = (Spinner) findViewById(R.id.profile_code_tribe_name_spinner);
        mAdmin_tribe_status_spinner = (Spinner) findViewById(R.id.profile_tribe_status_spinner);
       // mAdmin_personal_search_button = findViewById(R.id.admin_personal_search_button);
        mAdmin_code_tribe_save_button = findViewById(R.id.admin_code_tribe_save_button);
//Stashed changes
        mBio = findViewById(R.id.userBio);
        mProfileListButton = findViewById(R.id.profile_list_back_fab_button);
        mAdmin_code_tribe_save_button.setEnabled(false);

        mProjectsRecyclerView = (RecyclerView) findViewById(R.id.projectsRecyclerview);
        //Setup layout manager to a horizontal scrolling recyclerView
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(Admin_user_profile_editor.this, LinearLayoutManager.VERTICAL, false);
        mProjectsAdapter = new ProjectsHorizontalAdapter(Admin_user_profile_editor.this, projects);


        //Setup layout manager to a staggered scrolling recyclerView


        mAdmin_gender_spinner.setOnTouchListener(mTouchListener);
        setupGenderSpinner();
        mAdmin_ethnicity_spinner.setOnTouchListener(mTouchListener);
        setupEthnicitySpinner();
        mAdmin_code_tribe_name_spinner.setOnTouchListener(mTouchListener);
        setupEmploymentStatusSpinner();
        mAdmin_tribe_status_spinner.setOnTouchListener(mTouchListener);
        setupSalarySpinner();


        String tribe = mAdmin_code_tribe_name_spinner.getSelectedItem().toString();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child(tribe);


        mAdmin_name_edit_text = (EditText) findViewById(R.id.profile_name_edit_text);
        mAdmin_surname_edit_text = (EditText) findViewById(R.id.profile_surname_edit_text);
        mAdmin_age_edit_text = (EditText) findViewById(R.id.profile_age_edit_text);
        mAdmin_cell_number_edit_text = (EditText) findViewById(R.id.profile_cell_number_edit_text);
        mAdmin_email_edit_text = (EditText) findViewById(R.id.profile_email_edit_text);
        mAdmin_emc_edit_text.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mAdmin_emc_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() > 0) {
                    mAdmin_code_tribe_save_button.setEnabled(true);

                } else {
                    mAdmin_code_tribe_save_button.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mAdmin_code_tribe_save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadProfileImage();


            }
        });

        mAdmin_personal_search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println(mAdmin_emc_edit_text.getText().toString());
                String tribe = mAdmin_code_tribe_name_spinner.getSelectedItem().toString();

                mDatabaseReference = FirebaseDatabase.getInstance().getReference().child(tribe);
                mDatabaseReference.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mAdmin_name_edit_text.setText((String) dataSnapshot.child(mAdmin_emc_edit_text.getText().toString()).child("name").getValue());
                        mAdmin_surname_edit_text.setText((String) dataSnapshot.child(mAdmin_emc_edit_text.getText().toString()).child("surname").getValue());
                        mAdmin_age_edit_text.setText((String) dataSnapshot.child(mAdmin_emc_edit_text.getText().toString()).child("age").getValue());
                        mAdmin_cell_number_edit_text.setText((String) dataSnapshot.child(mAdmin_emc_edit_text.getText().toString()).child("mobileNo").getValue());
                        mAdmin_email_edit_text.setText((String) dataSnapshot.child(mAdmin_emc_edit_text.getText().toString()).child("email").getValue());
                        mAdmin_emc_edit_text.setText((String) dataSnapshot.child(mAdmin_emc_edit_text.getText().toString()).child("employment_code").getValue());


                        mAdmin_emc_edit_text.setEnabled(false);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });


        MyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mAdmin_name_edit_text.setText((String) dataSnapshot.child(currentUser.getUid()).child("name").getValue());
                mAdmin_surname_edit_text.setText((String) dataSnapshot.child(currentUser.getUid()).child("surname").getValue());
                mAdmin_age_edit_text.setText((String) dataSnapshot.child(currentUser.getUid()).child("mobileNo").getValue());
                mAdmin_cell_number_edit_text.setText((String) dataSnapshot.child(currentUser.getUid()).child("highestQualification").getValue());
                mAdmin_email_edit_text.setText((String) dataSnapshot.child(currentUser.getUid()).child("qualificationInstitution").getValue());
                mAdmin_emc_edit_text.setText((String) dataSnapshot.child(currentUser.getUid()).child("mobileNo").getValue());


                projects.clear();
                for (DataSnapshot snapshot : dataSnapshot.child("user_projects").child(currentUser.getUid()).child("projects").getChildren()) {
                    TribeMate project = new TribeMate();
                    project.setProjectTitle((String) snapshot.child("project_name").getValue());
                    project.setProjectLink((String) snapshot.child("github_link").getValue());
                    System.out.println(project.toMap());
                    projects.add(project);
                }
                mProjectsAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }







    private void setupGenderSpinner() {
        ArrayAdapter statusSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);
        statusSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mAdmin_gender_spinner.setAdapter(statusSpinnerAdapter);
        mAdmin_gender_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void setupEthnicitySpinner() {
        ArrayAdapter ethnicitySpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_ethnicity_option, android.R.layout.simple_spinner_item);
        ethnicitySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mAdmin_ethnicity_spinner.setAdapter(ethnicitySpinnerAdapter);
        mAdmin_ethnicity_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        ArrayAdapter employmentStatusSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_employee_options,
                android.R.layout.simple_dropdown_item_1line);
        mAdmin_code_tribe_name_spinner.setAdapter(employmentStatusSpinnerAdapter);
        mAdmin_code_tribe_name_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        ArrayAdapter salarySpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_status_options,
                android.R.layout.simple_dropdown_item_1line);
        mAdmin_tribe_status_spinner.setAdapter(salarySpinnerAdapter);
        mAdmin_tribe_status_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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




    private void uploadProfileImage() {
        if (filepath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading Information...");
            progressDialog.show();

            StorageReference ref = mStoragereference.child("profile_images");
            final TribeMate tribeMate = new TribeMate();


            ref.putFile(filepath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadUri = taskSnapshot.getDownloadUrl();

                            tribeMate.setQualification(mAdmin_name_edit_text.getText().toString());
                            tribeMate.setInstitute(mAdmin_surname_edit_text.getText().toString());
                            tribeMate.setName(mAdmin_age_edit_text.getText().toString());
                            tribeMate.setSurname(mAdmin_cell_number_edit_text.getText().toString());
                            tribeMate.setGender(mAdmin_gender_spinner.getSelectedItem().toString());
                            tribeMate.setEthnicity(mAdmin_ethnicity_spinner.getSelectedItem().toString());
                            tribeMate.setMobile(mAdmin_email_edit_text.getText().toString());
                            tribeMate.setEmail(mAdmin_emc_edit_text.getText().toString());
                            tribeMate.setEmploymentStatus(mAdmin_code_tribe_name_spinner.getSelectedItem().toString());
                            tribeMate.setSalary(mAdmin_tribe_status_spinner.getSelectedItem().toString());
                            MyRef.child(currentUser.getUid()).setValue(tribeMate.toMap());
                            mDatabaseReference.child(mAdmin_emc_edit_text.getText().toString()).setValue(tribeMate.toMap());
                            progressDialog.dismiss();
                            Toast.makeText(Admin_user_profile_editor.this, "Image Upload Successful ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Admin_user_profile_editor.this, DifferentCodetribeTabs.class));




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

        }
    };
}


