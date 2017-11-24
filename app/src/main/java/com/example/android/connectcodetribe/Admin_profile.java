package com.example.android.connectcodetribe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Admin on 11/24/2017.
 */

public class Admin_profile extends AppCompatActivity {

    TextView profileName,profileSurname,profileGender,profileEthnicity,profileCell_number,profileEmail,profileCountryOfBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_profile);

        profileName = findViewById(R.id.profile_name);
        profileSurname = findViewById(R.id.profile_surname);
        profileGender = findViewById(R.id.profile_gender);
        profileEthnicity = findViewById(R.id.profile_ethnicity);
        profileCell_number = findViewById(R.id.profile_cell_number);
        profileEmail = findViewById(R.id.profile_email);
        profileCountryOfBirth = findViewById(R.id.profile_countryOfBirth);




    }
}
