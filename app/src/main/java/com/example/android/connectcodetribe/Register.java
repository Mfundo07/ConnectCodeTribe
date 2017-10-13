package com.example.android.connectcodetribe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Admin on 10/5/2017.
 */


public class Register extends AppCompatActivity {
    private static final int RC_PHOTO_PICKER =  2;
    EditText username, password, firstName, surname,
            occupation, cellNumber, email;
    Button registerButton;
    String user, pass,userFirstName, userSurname, userOccupation, userCellNumber, userEmail;
    TextView login;
    DatabaseReference mReference;
    CircleImageView profileImage;
    StorageReference mStorageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        registerButton = (Button)findViewById(R.id.registerButton);
        login = (TextView)findViewById(R.id.login);
        firstName = (EditText) findViewById(R.id.firstName);
        surname = (EditText) findViewById(R.id.surname);
        occupation = (EditText) findViewById(R.id.occupation);
        cellNumber = (EditText) findViewById(R.id.cellNumber);
        email = (EditText) findViewById(R.id.email);
        profileImage = (CircleImageView) findViewById(R.id.profile_image);
        mStorageReference = FirebaseStorage.getInstance().getReference().child("user_profile_images");


        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString();
                pass = password.getText().toString();
                userFirstName = firstName.getText().toString();
                userSurname = surname.getText().toString();
                userOccupation = occupation.getText().toString();
                userCellNumber = cellNumber.getText().toString();
                userEmail = email.getText().toString();

                if(user.equals("")){
                    username.setError("can't be blank");
                }
                else if(pass.equals("")){
                    password.setError("can't be blank");
                }
                else if(!user.matches("[A-Za-z0-9]+")){
                    username.setError("only alphabet or number allowed");
                }
                else if(user.length()<5){
                    username.setError("at least 5 characters long");
                }
                else if(pass.length()<5){
                    password.setError("at least 5 characters long");
                }
                else if(!userSurname.matches("[A-Za-z0-9]+")){
                    surname.setError("only alphabet or number allowed");
                }
                else if(!userFirstName.matches("[A-Za-z0-9]+")){
                    firstName.setError("only alphabet or number allowed");
                }
                else if(!userOccupation.matches("[A-Za-z0-9]+")){
                    occupation.setError("only alphabet or number allowed");
                }
                else if(!userEmail.matches("[A-Za-z0-9]+")){
                    email.setError("only alphabet or number allowed");
                }
                else {
                    final ProgressDialog pd = new ProgressDialog(Register.this);
                    pd.setMessage("Loading...");
                    pd.show();

                    String url = "https://codetribeconnect.firebaseio.com/users.json ";

                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
                        @Override
                        public void onResponse(String s) {
                            mReference =FirebaseDatabase.getInstance().getReferenceFromUrl("https://codetribeconnect.firebaseio.com/users");

                            if(s.equals("null")) {
                                mReference.child(user).child("password").setValue(pass);
                                mReference.child(user).child("firstName").setValue(userFirstName);
                                mReference.child(user).child("surname").setValue(userSurname);
                                mReference.child(user).child("occupation").setValue(userOccupation);
                                mReference.child(user).child("cellNumber").setValue(cellNumber);
                                mReference.child(user).child("email").setValue(email);
                                Toast.makeText(Register.this, "registration successful", Toast.LENGTH_LONG).show();
                            }
                            else {
                                try {
                                    JSONObject obj = new JSONObject(s);

                                    if (!obj.has(user)) {
                                        mReference.child(user).child("password").setValue(pass);
                                        mReference.child(user).child("firstName").setValue(userFirstName);
                                        mReference.child(user).child("surname").setValue(userSurname);
                                        mReference.child(user).child("occupation").setValue(userOccupation);
                                        mReference.child(user).child("cellNumber").setValue(cellNumber);
                                        mReference.child(user).child("email").setValue(email);
                                        Toast.makeText(Register.this, "registration successful", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(Register.this, "username already exists", Toast.LENGTH_LONG).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            pd.dismiss();
                        }

                    },new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            System.out.println("" + volleyError );
                            pd.dismiss();
                        }
                    });

                    RequestQueue rQueue = Volley.newRequestQueue(Register.this);
                    rQueue.add(request);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK){
            Uri selectedImageUri = data.getData();

            Bitmap bm = null;
            try {
                bm = MediaStore.Images.Media.getBitmap(getContentResolver(),selectedImageUri);
                profileImage.setImageBitmap(bm);
            } catch (IOException e) {
                e.printStackTrace();
            }
            StorageReference photoRef = mStorageReference.child(selectedImageUri.getLastPathSegment());
            photoRef.putFile(selectedImageUri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUri =  taskSnapshot.getDownloadUrl();
                    mReference.child(user).setValue(downloadUri.toString());
                    profileImage.setImageResource(R.drawable.kratos);
                }
            });


        }
    }
}