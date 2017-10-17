package com.example.android.connectcodetribe;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by Admin on 10/12/2017.
 */

public class ChatFirebaseAdapter extends FirebaseRecyclerAdapter<ChatModel, ChatFirebaseAdapter.MyChatViewHolder> {

    private static final int RIGHT_MSG = 0;
    private static final int LEFT_MSG = 1;
    private static final int RIGHT_MSG_IMG = 2;
    private static final int LEFT_MSG_IMG = 3;

    private ClickListenerChatFirebase mClickListenerChatFirebase;

    private String nameUser;



    public ChatFirebaseAdapter(DatabaseReference ref, String nameUser, ClickListenerChatFirebase mClickListenerChatFirebase) {
        super(ChatModel.class, R.layout.item_message_left, ChatFirebaseAdapter.MyChatViewHolder.class, ref);
        this.nameUser = nameUser;
        this.mClickListenerChatFirebase = mClickListenerChatFirebase;
    }

    @Override
    public MyChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == RIGHT_MSG){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_right,parent,false);
            return new MyChatViewHolder(view);
        }else if (viewType == LEFT_MSG){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_left,parent,false);
            return new MyChatViewHolder(view);
        }else if (viewType == RIGHT_MSG_IMG){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_right_img,parent,false);
            return new MyChatViewHolder(view);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_left_img,parent,false);
            return new MyChatViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        ChatModel model = getItem(position);
         if (model.getFile() != null){
            if (model.getFile().getType().equals("img") && model.getUserModel().getName().equals(nameUser)){
                return RIGHT_MSG_IMG;
            }else{
                return LEFT_MSG_IMG;
            }
        }else if (model.getUserModel().getName().equals(nameUser)){
            return RIGHT_MSG;
        }else{
            return LEFT_MSG;
        }
    }

    @Override
    protected void populateViewHolder(MyChatViewHolder viewHolder, ChatModel model, int position) {
        viewHolder.setIvUser(model.getUserModel().getPhoto_profile());
        viewHolder.setTxtMessage(model.getMessage());
        viewHolder.setTvTimestamp(model.getTimeStamp());
        if (model.getFile() != null){
            viewHolder.setIvChatPhoto(model.getFile().getUrl_file());
        }
    }

    public class MyChatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTimestamp;
        TextView tvTimeStamp_left;
        TextView txtMessage;
        ImageView ivUser,ivChatPhoto;

        public MyChatViewHolder(View itemView) {
            super(itemView);
            tvTimestamp = (TextView)itemView.findViewById(R.id.timestamp_right);
            txtMessage = (TextView)itemView.findViewById(R.id.textMessage);
            ivChatPhoto = (ImageView)itemView.findViewById(R.id.img_chat);
            ivUser = (ImageView)itemView.findViewById(R.id.ivUserChat);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            ChatModel model = getItem(position);

        }

        public void setTxtMessage(String message){
            if (txtMessage == null)return;
            txtMessage.setText(message);
        }

        public void setIvUser(String urlPhotoUser){
            if (ivUser == null)return;
            Glide.with(ivUser.getContext()).load(urlPhotoUser).centerCrop().transform(new CircleTransform(ivUser.getContext())).override(40,40).into(ivUser);
        }

        public void setTvTimestamp(String timestamp){
            if (tvTimestamp == null)return;
            tvTimestamp.setText(converteTimestamp(timestamp));
        }

        public void setIvChatPhoto(String url){
            if (ivChatPhoto == null)return;
            Glide.with(ivChatPhoto.getContext()).load(url)
                    .override(100, 100)
                    .fitCenter()
                    .into(ivChatPhoto);
            ivChatPhoto.setOnClickListener(this);
        }



    }

    private CharSequence converteTimestamp(String mileSegundos){
        return DateUtils.getRelativeTimeSpanString(Long.parseLong(mileSegundos),System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
    }

}
