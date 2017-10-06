package com.example.android.connectcodetribe;

/**
 * Created by Admin on 10/6/2017.
 */

public class ChatMessage {
    private String text;
    private String name;
    private String photoUrl;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public ChatMessage() {

    }

    public ChatMessage(String text, String name, String photoUrl) {

        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
    }
}
