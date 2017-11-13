package com.example.android.connectcodetribe.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.connectcodetribe.Model.Profile;
import com.example.android.connectcodetribe.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Admin on 10/4/2017.
 */

public class ProfileAdapter extends ArrayAdapter {
    public ProfileAdapter(Context context, int resource, List<Profile> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.user_item,parent,false);
        }

        Profile currentProfile = (Profile) getItem(position);

        TextView userName  = listItemView.findViewById(R.id.User_Name);
        TextView userSurname = listItemView.findViewById(R.id.User_Surname);
        TextView userStatus = listItemView.findViewById(R.id.User_Status);
        TextView userIntakeYear = listItemView.findViewById(R.id.intake_year_text);
        CircleImageView userProfileImage = listItemView.findViewById(R.id.profile_image);

        boolean isImage = currentProfile.getProfileImage() != null;

        if (isImage){

            Glide.with(userProfileImage.getContext())
                    .load(currentProfile.getProfileImage())
                    .into(userProfileImage);
            userName.setText(currentProfile.getProfileName());
            userSurname.setText(currentProfile.getProfileSurname());
            userStatus.setText(currentProfile.getStatus());
            userIntakeYear.setText(currentProfile.getIntakeYear());


        }else{
            userProfileImage.setImageResource(R.drawable.kratos);
            userName.setText(currentProfile.getProfileName());
            userSurname.setText(currentProfile.getProfileSurname());
            userStatus.setText(currentProfile.getStatus());
            userIntakeYear.setText(currentProfile.getIntakeYear());
        }

        return listItemView;
    }
}
