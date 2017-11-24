package com.example.android.connectcodetribe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin_Login_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__login_);

        Button button = (Button)findViewById(R.id.admin_register);
        Button button1 = (Button)findViewById(R.id.admin_sign_in_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kb =new Intent(Admin_Login_Activity.this, Admin_register.class);
                startActivity(kb);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bk =new Intent(Admin_Login_Activity.this, Admin_codetribe_fragment.class);
                startActivity(bk);
            }
        });


    }
}
