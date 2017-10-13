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
import com.example.android.connectcodetribe.Model.ChatMessage;
import com.example.android.connectcodetribe.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Admin on 10/11/2017.
 */

public class ChatUserAdapter extends ArrayAdapter {
    public ChatUserAdapter(Context context, int resource, List<ChatMessage> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.user_item,parent,false);
        }

        ChatMessage currentUser = (ChatMessage) getItem(position);

        TextView userName  = listItemView.findViewById(R.id.User_Name);
        TextView userStatus = listItemView.findViewById(R.id.User_Status);
        CircleImageView userProfileImage = listItemView.findViewById(R.id.profile_image);

        boolean isImage = currentUser.getImageUrl() != null;

        if (isImage){

            Glide.with(userProfileImage.getContext())
                    .load(currentUser.getImageUrl())
                    .into(userProfileImage);
            userName.setText(currentUser.getName());
            userStatus.setText(currentUser.getText());


        }else{
            userProfileImage.setImageResource(R.drawable.kratos);
            userName.setText(currentUser.getName());
            userStatus.setText(currentUser.getText());
        }

        return listItemView;
    }
}