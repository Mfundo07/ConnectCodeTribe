package com.example.android.connectcodetribe.Model;

/**
 * Created by Admin on 10/4/2017.
 */

public class ActiveUser {

    private String activeUserName;
    private String activeUserSurname;
    private String activeUserOccupation;
    private String activeUserStatus;
    private String activeUser;
    private String activeUserNumber;
    private String activeUserEmail;

    public String getActiveUserImageUrl() {
        return activeUserImageUrl;
    }

    public void setActiveUserImageUrl(String activeUserImageUrl) {
        this.activeUserImageUrl = activeUserImageUrl;
    }

    private String activeUserImageUrl;

    public String getActiveUserName() {
        return activeUserName;
    }

    public void setActiveUserName(String activeUserName) {
        this.activeUserName = activeUserName;
    }

    public String getActiveUserSurname() {
        return activeUserSurname;
    }

    public void setActiveUserSurname(String activeUserSurname) {
        this.activeUserSurname = activeUserSurname;
    }

    public String getActiveUserOccupation() {
        return activeUserOccupation;
    }

    public void setActiveUserOccupation(String activeUserOccupation) {
        this.activeUserOccupation = activeUserOccupation;
    }

    public String getActiveUserStatus() {
        return activeUserStatus;
    }

    public void setActiveUserStatus(String activeUserStatus) {
        this.activeUserStatus = activeUserStatus;
    }

    public String getActiveUser() {
        return activeUser;
    }

    public ActiveUser() {
    }

    public String getActiveUserNumber() {
        return activeUserNumber;
    }

    public void setActiveUserNumber(String activeUserNumber) {
        this.activeUserNumber = activeUserNumber;
    }

    public String getActiveUserEmail() {
        return activeUserEmail;
    }

    public void setActiveUserEmail(String activeUserEmail) {
        this.activeUserEmail = activeUserEmail;
    }

    public ActiveUser(String activeUserName, String activeUserSurname, String activeUserOccupation, String activeUserStatus, String activeUser, String activeUserNumber, String activeUserEmail, String activeUserImageUrl) {
        this.activeUserName = activeUserName;
        this.activeUserSurname = activeUserSurname;
        this.activeUserOccupation = activeUserOccupation;
        this.activeUserStatus = activeUserStatus;
        this.activeUser = activeUser;
        this.activeUserNumber = activeUserNumber;
        this.activeUserEmail = activeUserEmail;
        this.activeUserImageUrl = activeUserImageUrl;
    }


}
