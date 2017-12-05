package com.example.android.connectcodetribe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Admin on 12/5/2017.
 */

public class RequestingUserProfileActivity extends AppCompatActivity {


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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_editor66);

        TextView userEthnicity = findViewById(R.id.user_ethnicity);
        TextView userGender = findViewById(R.id.user_gender);
        TextView userAge = findViewById(R.id.user_age);
        TextView userEmail = findViewById(R.id.user_email);
        TextView userMobileNo = findViewById(R.id.user_cell_number);
        TextView userProfileStatus = findViewById(R.id.userStatus);
        TextView userEMC = findViewById(R.id.user_code);
        final Button userAcceptButton = findViewById(R.id.decline_btn);
        final DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("/accepted/");
        final DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference("/requested/");


        mName = getIntent().getExtras().getString("Name");
        mImage = getIntent().getExtras().getString("Image");
        mStatus = getIntent().getExtras().getString("Status");
        mCodeTribe = getIntent().getExtras().getString("CodeTribe");
        mSurname = getIntent().getExtras().getString("Surname");
        mImage = getIntent().getExtras().getString("image");
        mBio = getIntent().getExtras().getString("bio");


        mGender = getIntent().getExtras().getString("Gender");
        mEMC = getIntent().getExtras().getString("Employee_code");
        mEthnicity = getIntent().getExtras().getString("Ethnicity");
        mAge = getIntent().getExtras().getString("Age");
        mEmail = getIntent().getExtras().getString("Email");
        mMobile = getIntent().getExtras().getString("Mobile");

        userEMC.setText(mEMC);
        userProfileStatus.setText(mStatus);

        final DatabaseReference mine = FirebaseDatabase.getInstance().getReference().child("requested").child("Soweto").child(mEMC);




        if (getIntent().getExtras().getString("Gender") != null){
            userGender.setText(mGender);}
        else{
            userGender.setText("");
        }

        if (getIntent().getExtras().getString("Ethnicity") != null){
            userEthnicity.setText(mEthnicity);}
        else{
            userEthnicity.setText("");
        }

        if (getIntent().getExtras().getString("Age") != null){
            userAge.setText(mAge);}
        else{
            userAge.setText("");
        }

        if (getIntent().getExtras().getString("Email") != null){
            userEmail.setText(mEmail);}
        else{
            userEmail.setText("");
        }

        if (getIntent().getExtras().getString("Mobile") != null){
            userMobileNo.setText(mMobile);}
        else{
            userMobileNo.setText("");
        }

        userAcceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "EC: " + mEMC, Toast.LENGTH_SHORT).show();
                mine.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        System.out.println("EMAIL: " + dataSnapshot.child("emailAddress").getValue());
                        mine.removeValue();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        databaseError.getMessage();
                    }
                });
            }
        });

       /* userAcceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TribeMate mate = new TribeMate();
                mate.setName(mName);
                mate.setSurname(mSurname);
                mate.setAge(Long.valueOf(mAge));
                mate.setEMC(mEMC);
                mate.setGender(mGender);
                mate.setEthnicity(mEthnicity);
                mate.setMobile(mMobile);
                mate.setEmail(mEmail);
                mRef.child(mCodeTribe).child(mEMC).setValue(mate.toMap()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        userAcceptButton.setText("Accepted");
                        userAcceptButton.setEnabled(false);

                        mDatabaseReference.orderByChild(mEMC).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot child: dataSnapshot.getChildren()) {
                                    child.getRef().setValue(null);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                });

            }
        });*/
    }
}
