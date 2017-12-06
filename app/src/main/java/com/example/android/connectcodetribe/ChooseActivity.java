package com.example.android.connectcodetribe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.choose_user_or_admin );

        Button users_section=(Button)findViewById ( R.id.users_section );
        Button admin_section=(Button)findViewById ( R.id.admin_section );
        users_section.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                Intent kb =new Intent ( ChooseActivity.this, CodeTribeSelectActivity.class );
                startActivity ( kb );
            }
        } );

        admin_section.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                Intent kb =new Intent ( ChooseActivity.this, Admin_Login_Activity.class );
                startActivity ( kb );
            }
        } );
    }
}
