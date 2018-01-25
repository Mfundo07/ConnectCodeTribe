package com.example.android.connectcodetribe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Admin on 1/19/2018.
 */

public class WalkThroughActivity extends AppCompatActivity {

    private PrefManager prefManager;
    DatabaseReference mDatabaseReference;
    FirebaseUser mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Checking for first time launch - before calling setContentView()
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }


        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_walkthrough);

        mAuth = FirebaseAuth.getInstance().getCurrentUser();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Soweto");

        Button basicInfoButton = findViewById(R.id.basic_info);
        Button  profileImageButton = findViewById(R.id.profile_image_button);

        basicInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDatabaseReference.orderByChild("emailAddress").equalTo(mAuth.getEmail()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Intent intent = new Intent(WalkThroughActivity.this, CodeTribeListActivity.class);
                        intent.putExtra("CodeTribe", "Soweto");
                        intent.putExtra("Email", mAuth.getEmail());
                        startActivity(intent);
                        Toast.makeText ( WalkThroughActivity.this, "Searched", Toast.LENGTH_SHORT ).show ( );


                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        profileImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDatabaseReference.orderByChild("emailAddress").equalTo(mAuth.getEmail()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Intent intent = new Intent(WalkThroughActivity.this, PhotoSetupActivity.class);
                        intent.putExtra("Name", (String) dataSnapshot.child("name").getValue());
                        intent.putExtra("Surname", (String) dataSnapshot.child("surname").getValue());
                        intent.putExtra("CodeTribe", (String) dataSnapshot.child("codeTribe").getValue());
                        intent.putExtra("Status",(String) dataSnapshot.child("status").getValue());
                        intent.putExtra("Email", (String) dataSnapshot.child("email").getValue());

                        intent.putExtra("Employee_code",(String) dataSnapshot.child("employee_code").getValue());
                        intent.putExtra("Gender", (String) dataSnapshot.child("gender").getValue());
                        intent.putExtra("Ethnicity", (String) dataSnapshot.child("ethnicity").getValue());
                        intent.putExtra("Age",(String) dataSnapshot.child("age").getValue());
                        intent.putExtra("Email", (String) dataSnapshot.child("email").getValue());
                        intent.putExtra("Mobile", (String) dataSnapshot.child("mobile").getValue());
                        intent.putExtra("image", (String) dataSnapshot.child("image").getValue());
                        intent.putExtra("bio", (String) dataSnapshot.child("bio").getValue());
                        intent.putExtra("company_name",(String) dataSnapshot.child("company_name").getValue());
                        intent.putExtra("employed_year", (String) dataSnapshot.child("employed_year").getValue());
                        intent.putExtra("company_contact", (String) dataSnapshot.child("company_contact").getValue());
                        intent.putExtra("employment_status", (String) dataSnapshot.child("employee_status").getValue());
                        intent.putExtra("salary", (String) dataSnapshot.child("salary").getValue());
                        intent.putExtra("start_date",(String) dataSnapshot.child("start_date").getValue());
                        intent.putExtra("user_code",(String) dataSnapshot.child("user_code").getValue());
                        intent.putExtra("tribe_underline", (String) dataSnapshot.child("tribe").getValue());
                        intent.putExtra("qualification", (String) dataSnapshot.child("qualification").getValue());
                        intent.putExtra("institution",(String) dataSnapshot.child("institution").getValue());
                        intent.putExtra("faculty",(String) dataSnapshot.child("faculty").getValue());
                        startActivity(intent);


                        }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

        changeStatusBarColor();
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(WalkThroughActivity.this, DifferentCodetribeTabs.class));
        finish();
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }


}
