package com.example.android.connectcodetribe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class ProfileActivity extends AppCompatActivity {

    CardView cardBio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        cardBio = (CardView) findViewById(R.id.cardView_bio);
        cardBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, UserProfileEditorActivity.class);
                startActivity(intent);
            }
        });
    }
}
