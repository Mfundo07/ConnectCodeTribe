package com.example.android.connectcodetribe.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.connectcodetribe.ChatModel;
import com.example.android.connectcodetribe.R;

import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<String> mItemNames, mItemSuurname, mItemStatus;
    private  Context context;

    public MyItemRecyclerViewAdapter(List<String> mItemNames, List<String> mItemSuurname, List<String> mItemStatus, Context context) {
        this.mItemNames = mItemNames;
        this.mItemSuurname = mItemSuurname;
        this.mItemStatus = mItemStatus;
        this.context = context;
    }

    public MyItemRecyclerViewAdapter(List<String> itemName, List<String> itemSurname, List<String> itemStatus) {
        mItemNames = itemName;
        mItemSuurname = itemSurname;
        mItemStatus = itemStatus;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mItemNames.get(position);
        holder.mIdView.setText(mItemNames.get(position));
        holder.mContentView.setText(mItemSuurname.get(position));
        holder.mStatus.setText(mItemStatus.get(position));
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChatModel.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mItemNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mStatus;
        public String mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.User_Surname);
            mContentView = (TextView) view.findViewById(R.id.User_Name);
            mStatus = (TextView) view.findViewById(R.id.User_Status);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
