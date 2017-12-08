package com.example.android.connectcodetribe;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SimpleAdmin_Login extends AppCompatActivity  {

    EditText email,pass;
    Button signin;
    TextInputLayout passa,emaila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_simple_admin__login );

        passa=(TextInputLayout ) findViewById ( R.id.input_password );
        emaila=(TextInputLayout ) findViewById ( R.id.input_email );
        email=(EditText)findViewById ( R.id.a );
        pass=(EditText)findViewById ( R.id.b );
        signin=(Button ) findViewById ( R.id.c );

        signin.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {

                String name = "1";
                String password = "1";
                String name1 = email.getText ().toString ();
                String pass1 = pass.getText ().toString ();
                if (name.equals ( name1 )&& password.equals ( pass1 )){

                    Intent gabi = new Intent ( SimpleAdmin_Login.this, RequestActivity.class );
                    startActivity ( gabi );
                    Toast.makeText ( SimpleAdmin_Login.this, "Logged In", Toast.LENGTH_SHORT ).show ( );
                }else{

                    Toast.makeText ( SimpleAdmin_Login.this, "Email or Password Incorrect", Toast.LENGTH_SHORT ).show ( );

                }

            }
        } );


    }
/**
    @Override
    public void onClick(View view) {

        String name = "adm1n@gmail.com";
        String password = "10111";
        String name1 = email.getText ().toString ();
        String pass1 = pass.getText ().toString ();
        if (name.equals ( name1 )&& password.equals ( pass1 )){

            Intent gabi = new Intent ( SimpleAdmin_Login.this, RequestActivity.class );
                    startActivity ( gabi );
        }else{

            Toast.makeText ( this, "Email or password inccorect", Toast.LENGTH_SHORT ).show ( );
        }

    }**/
}
