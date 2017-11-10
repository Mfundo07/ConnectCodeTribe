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
import com.example.android.connectcodetribe.Model.ActiveUser;
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
    private EditText userNameEditText;
    private EditText userSurnameEditText;
    private EditText userCurrentOccupation;
    private EditText userPhoneNumber;
    private EditText userEmailEditText;
    private Button userUpdateButton;
    private CircleImageView profileImage;
    DatabaseReference mDatabaseReference;
    StorageReference mStoragereference;
    Uri FilePathUri;
    public static final int STATUS_UNKNOWN = 0;
    public static final int STATUS_ALUMNI = 1;
    public static final int STATUS_INTERN = 2;

    public static final int TRIBE_SOWETO = 1;
    public static final int TRIBE_PRETORIA = 2;
    public static final int TRIBE_TEMBISA = 3;
    public static final int TRIBE_ALEX = 4;


    private Spinner mStatusSpinner, mCodeTribeSpinner;
    private boolean mUserHasChanged = false;
    FirebaseUser currentUser;
    private int mStatus = STATUS_UNKNOWN;
    private  int mCodeTribe = TRIBE_SOWETO;
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
        if (gender == STATUS_UNKNOWN || gender == STATUS_ALUMNI || gender == STATUS_INTERN) {
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
        userNameEditText = (EditText) findViewById(R.id.name_editView);
        userSurnameEditText = (EditText) findViewById(R.id.surnameEditText);
        userCurrentOccupation = (EditText) findViewById(R.id.occpateEditText);
        userPhoneNumber = (EditText) findViewById(R.id.cell_editTextView);
        userEmailEditText = (EditText) findViewById(R.id.emailEditText);
        userUpdateButton = (Button) findViewById(R.id.profile_edit);
        mStatusSpinner = (Spinner) findViewById(R.id.spinner_status);
        profileImage = (CircleImageView) findViewById(R.id.profile_image);
        mCodeTribeSpinner = (Spinner) findViewById(R.id.spinner_codeTribe);
        Glide.with(profileImage.getContext())
                .load(currentUser.getPhotoUrl())
                .into(profileImage);
        userEmailEditText.setText(currentUser.getEmail().toString());


        userUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Profile items = new Profile();
                items.setProfileName(userNameEditText.getText().toString());
                items.setOccupation(userCurrentOccupation.getText().toString());
                items.setProfileEmail(userEmailEditText.getText().toString());
                items.setStatus(mStatusSpinner.getSelectedItem().toString());
                items.setProfileSurname(userSurnameEditText.getText().toString());
                items.setProfileImage(null);
                items.setCodeTribe(mCodeTribeSpinner.getSelectedItem().toString());
                mDatabaseReference.child( UUID.randomUUID().toString()).setValue(items.toMap()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Profile updated", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            task.getException().printStackTrace();
                        }
                    }
                });;

            }
        });


        mStatusSpinner.setOnTouchListener(mTouchListener);
        setupSpinner();
        mCodeTribeSpinner.setOnTouchListener(mTouchListener);
        codetribeSpinner();


        userNameEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        userSurnameEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        userCurrentOccupation.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        userPhoneNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        userEmailEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});

    }

    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter statusSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        statusSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mStatusSpinner.setAdapter(statusSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mStatusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.status_alumni))) {
                        mStatus = STATUS_ALUMNI;
                    } else if (selection.equals(getString(R.string.status_intern))) {
                        mStatus = STATUS_INTERN;
                    } else {
                        mStatus = STATUS_UNKNOWN;
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mStatus = STATUS_UNKNOWN;
            }
        });
    }

    private void codetribeSpinner(){
        ArrayAdapter codeTribeSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.code_tribe_options, android.R.layout.simple_spinner_item);
        codeTribeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mCodeTribeSpinner.setAdapter(codeTribeSpinnerAdapter);
        mCodeTribeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selection = (String) adapterView.getItemAtPosition(i);
                if (!TextUtils.isEmpty(selection)){
                    if (selection.equals(R.string.pretoria)){
                        mCodeTribe = TRIBE_PRETORIA;

                    }else if (selection.equals(R.string.tembisa)){
                        mCodeTribe = TRIBE_TEMBISA;
                    }else if (selection.equals(R.string.alex)){
                        mCodeTribe = TRIBE_ALEX;
                    }else {
                        mCodeTribe = TRIBE_SOWETO;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mCodeTribe = TRIBE_PRETORIA;

            }
        });

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()){
                    userNameEditText.setText((String) dataSnapshot.child(currentUser.getUid()).child("activeUserName").getValue());
                    if (dataSnapshot.child(currentUser.getUid()).child("activeUserEmail").getValue() == null){
                    userEmailEditText.setText(currentUser.getEmail());}
                    else{
                        userEmailEditText.setText((String)dataSnapshot.child(currentUser.getUid()).child("activeUserEmail").getValue());
                    }
                    userSurnameEditText.setText((String) dataSnapshot.child(currentUser.getUid()).child("activeUserSurname").getValue());
                    userCurrentOccupation.setText((String) dataSnapshot.child(currentUser.getUid()).child("activeUserOccupation").getValue());
                    userPhoneNumber.setText((String) dataSnapshot.child(currentUser.getUid()).child("activeUserNumber").getValue());
                    Glide.with(profileImage.getContext())
                            .load((String) dataSnapshot.child("0").child("display_picture").getValue())
                            .into(profileImage);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                profileImage.setImageBitmap(bm);

            } catch (IOException e) {
                e.printStackTrace();
            }
            StorageReference photoRef = mStoragereference;
            photoRef.putFile(selectedImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUri = taskSnapshot.getDownloadUrl();
                    ActiveUser user = new ActiveUser();
                    user.setActiveUserImageUrl(downloadUri.toString());
                }
            });


        }
    }

    // Creating Method to get the selected image file Extension from File Path URI.
}

