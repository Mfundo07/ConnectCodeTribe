package com.example.android.connectcodetribe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.android.connectcodetribe.Adapters.AdminRequestCategoryAdapter;
import com.example.android.connectcodetribe.Model.TribeMate;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class RequestActivity extends AppCompatActivity
implements NavigationView.OnNavigationItemSelectedListener{

    StorageReference mStoragereference;

    private final int PICK_IMAGE_REQUEST = 71;

    private CircleImageView mProfileCircleImage;

    FloatingActionButton mProfileBackFabButton;

    private ActionBarDrawerToggle mDrawerToggle;

    private Uri filepath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigatorrr_request);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarrequest);
        toolbar.setTitle ( "Request List" );
        setSupportActionBar(toolbar);

        mProfileCircleImage =(CircleImageView ) findViewById(R.id.profile_image);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);



        AdminRequestCategoryAdapter adapter = new AdminRequestCategoryAdapter(getSupportFragmentManager(), this);

        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        //mProfileBackFabButton.setOnClickListener(new View.OnClickListener() {
         //   @Override
          //  public void onClick(View v) {
             //   startActivity(new Intent(RequestActivity.this, Admin_profile.class));
          //  }
       // });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_add_members) {

            Intent logout = new Intent(RequestActivity.this, Admin_user_profile_editor.class);
            startActivity(logout);
            return true;

        } else if (id == R.id.nav_request) {

            Intent request = new Intent(RequestActivity.this, RequestActivity.class);
            startActivity(request);
            return true;

        }else if (id == R.id.nav_my_profile) {

            Intent profile = new Intent(RequestActivity.this, Admin_profile.class);
            startActivity(profile);
            return true;

        } else if (id == R.id.nav_accepted_request) {

            Intent accept = new Intent(RequestActivity.this, AcceptedActivity.class);
            startActivity(accept);
            return true;

        } else if (id == R.id.nav_logout) {

            Intent logout = new Intent(RequestActivity.this, Admin_Login_Activity.class);
            startActivity(logout);
            return true;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;




    }    private void chooseProfileImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filepath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                mProfileCircleImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadProfileImage() {
        if (filepath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading Information...");
            progressDialog.show();

            StorageReference ref = mStoragereference.child("profile_images");
            final TribeMate tribeMate = new TribeMate();


            ref.putFile(filepath)
               .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot> () {
                   @Override
                   public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                       Uri downloadUri = taskSnapshot.getDownloadUrl ( );

                       tribeMate.setProfileImage(downloadUri.toString());

                   }
               });
        }
    }


}
