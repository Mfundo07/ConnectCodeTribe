package com.example.android.connectcodetribe;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Button more_info =(Button)findViewById(R.id.more_info);
        ImageButton location=(ImageButton)findViewById(R.id.location);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("https://www.google.co.za/search?rlz=1C1CHBF_enZA743ZA743&q=codetribe+mlab&npsic=0&rflfq=1&rlha=0&rllag=-26004861,28108735,32565&tbm=lcl&ved=0ahUKEwi_qszDltfXAhXEbVAKHfgzB-0QtgMIOg&tbs=lrf:!2m1!1e2!2m1!1e3!3sIAE,lf:1,lf_ui:2&rldoc=1#rlfi=hd:;si:13728111948302230818;mv:!1m3!1d277905.4223280851!2d28.10873515!3d-26.004861950000002!2m3!1f0!2f0!3f0!3m2!1i259!2i465!4f13.1");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });

        more_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.mlab.co.za/codetribe/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}
