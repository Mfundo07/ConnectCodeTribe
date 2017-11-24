package com.example.android.connectcodetribe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin_register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register);

        Button button =(Button)findViewById(R.id.admin_register_Button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kb =new Intent(Admin_register.this, Admin_Login_Activity.class);
                startActivity(kb);
            }
        });
    }
}
