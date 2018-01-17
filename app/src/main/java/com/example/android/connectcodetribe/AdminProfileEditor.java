package com.example.android.connectcodetribe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Admin on 11/29/2017.
 */

public class AdminProfileEditor extends AppCompatActivity {

    EditText profileName,profileSurname,profileCell_number,profileEmail,profileAge;
    Spinner profileGender,profileEthnicity,profileCodeTribeName;
    Button profile_personal_info_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_profile_editor);

        profileName = findViewById(R.id.profile_name_edit_text);
        profileSurname = findViewById(R.id.profile_surname_edit_text);
        profileGender = findViewById(R.id.profile_gender_spinner);
        profileEthnicity = findViewById(R.id.profile_ethnicity_spinner);
        profileCell_number = findViewById(R.id.profile_cell_number_edit_text);
        profileEmail = findViewById(R.id.profile_email_edit_text);
        profileAge = findViewById(R.id.profile_age_edit_text);

        profileCodeTribeName = findViewById(R.id.profile_code_tribe_name_spinner);

        profile_personal_info_button=(Button)findViewById ( R.id.profile_personal_info_button );
        profile_personal_info_button.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent ( AdminProfileEditor.this, Admin_profile.class );
                startActivity ( intent );
            }
        } );

    }

}

