package com.example.android.connectcodetribe;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.connectcodetribe.Adapters.Admin_History_CategoryAdapter;
import com.example.android.connectcodetribe.Adapters.CategoryAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class Users_request_Activity extends AppCompatActivity {

    FirebaseUser currentUser;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_request_);

        Button history = (Button)findViewById( R.id.id_history );
        history.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gabaza = new Intent( Users_request_Activity.this, HistoryActivity.class );
                startActivity( gabaza );
            }
        } );

    }
}