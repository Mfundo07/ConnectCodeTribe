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
import com.example.android.connectcodetribe.Model.ActiveUser;
import com.example.android.connectcodetribe.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Admin on 10/4/2017.
 */

public class ActiveUserAdapter extends ArrayAdapter {
    public ActiveUserAdapter(Context context, int resource,  List<ActiveUser> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.user_item,parent,false);
        }

        ActiveUser currentUser = (ActiveUser) getItem(position);

        TextView userName  = listItemView.findViewById(R.id.User_Name);
        TextView userSurname = listItemView.findViewById(R.id.User_Surname);
        TextView userStatus = listItemView.findViewById(R.id.User_Status);
        CircleImageView userProfileImage = listItemView.findViewById(R.id.profile_image);

        boolean isImage = currentUser.getActiveUserImageUrl() != null;

        if (isImage){

            Glide.with(userProfileImage.getContext())
                    .load(currentUser.getActiveUserImageUrl())
                    .into(userProfileImage);
            userName.setText(currentUser.getActiveUserName());
            userSurname.setText(currentUser.getActiveUserSurname());
            userStatus.setText(currentUser.getActiveUserStatus());


        }else{
            userProfileImage.setImageResource(R.drawable.kratos);
            userName.setText(currentUser.getActiveUserName());
            userSurname.setText(currentUser.getActiveUserSurname());
            userStatus.setText(currentUser.getActiveUserStatus());
        }

        return listItemView;
    }
}
