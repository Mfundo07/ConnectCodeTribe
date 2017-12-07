package com.example.android.connectcodetribe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.connectcodetribe.Adapters.ProjectsHorizontalAdapter;
import com.example.android.connectcodetribe.profile.ProfileActivity;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;


public class Admin_profile extends AppCompatActivity {

    private static final int GALLERY_REQUEST = 1;
    private static final int CAMERA_REQUEST_CODE = 1;
    public static final int RC_PHOTO_PICKER = 2;

    private final int PICK_IMAGE_REQUEST = 71;

    private Button ButUpload;

    private EditText mProfileNameEditText;
    private EditText mProfileSurnameEditText;
    private EditText mProfileEmailEditText;
    private TextView profileName,profileSurname,profileCell_number,profileEmail,profileAge;
    private Spinner profileGender,profileEthnicity,profileCodetribeName;
    private FloatingActionButton mProfileListButton;
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

    public static final int TRIBE_SOWETO = 1;
    public static final int TRIBE_PRETORIA = 2;
    public static final int TRIBE_TEMBISA = 3;


    private Uri filepath;

    RecyclerView mProjectsRecyclerView;
    ProjectsHorizontalAdapter mProjectsAdapter;




    private Spinner mProfileGenderSpinner, mProfileEthnicitySpinner,
            mProfileEmploymentStatusSpinner, mProfileSalarySpinner;

    private Spinner mProfileCodeTribeSpinner;
    private Spinner mProfileProgramStateSpinner;

    DatabaseReference MyRef;


    FloatingActionButton ProfileEditFAButton;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile_view);

       // profileName = findViewById(R.id.profile_name_edit);


    }

}

