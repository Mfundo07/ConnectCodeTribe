package com.example.android.connectcodetribe.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Admin on 11/7/2017.
 */

public class Profile {
    private String mProfileImage;
    private String mProfileName;
    private String mStatus;
    private String mOccupation;
    private String mProfileEmail;
    private String mCodeTribe;
    private String mProfileSurname;
    private UUID mId;

    public UUID getId() {
        return mId;
    }

    public Profile(UUID id) {
        mId = id;
    }

    public String getProfileSurname() {
        return mProfileSurname;
    }

    public void setProfileSurname(String profileSurname) {
        mProfileSurname = profileSurname;
    }

    public Profile(String profileImage, String profileName, String status, String occupation, String profileEmail, String codeTribe, String profileSurname) {

        mProfileImage = profileImage;
        mProfileName = profileName;
        mStatus = status;
        mOccupation = occupation;
        mProfileEmail = profileEmail;
        mCodeTribe = codeTribe;
        mProfileSurname = profileSurname;
    }

    public String getCodeTribe() {
        return mCodeTribe;
    }

    public void setCodeTribe(String codeTribe) {
        mCodeTribe = codeTribe;
    }

    public Profile(String profileImage, String profileName, String status, String occupation, String profileEmail, String codeTribe) {

        mProfileImage = profileImage;
        mProfileName = profileName;
        mStatus = status;
        mOccupation = occupation;
        mProfileEmail = profileEmail;
        mCodeTribe = codeTribe;
    }

    public String getProfileImage() {
        return mProfileImage;
    }

    public void setProfileImage(String profileImage) {
        mProfileImage = profileImage;
    }

    public String getProfileName() {
        return mProfileName;
    }

    public void setProfileName(String profileName) {
        mProfileName = profileName;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getOccupation() {
        return mOccupation;
    }

    public void setOccupation(String occupation) {
        mOccupation = occupation;
    }

    public String getProfileEmail() {
        return mProfileEmail;
    }

    public void setProfileEmail(String profileEmail) {
        mProfileEmail = profileEmail;
    }

    public Profile(String profileImage, String profileName, String status, String occupation, String profileEmail) {

        mProfileImage = profileImage;
        mProfileName = profileName;
        mStatus = status;
        mOccupation = occupation;
        mProfileEmail = profileEmail;
    }

    public Profile() {
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("display_picture", mProfileImage);
        result.put("name", mProfileName);
        result.put("occupation", mOccupation);
        result.put("email", mProfileEmail);
        result.put("status", mStatus);
        result.put("codeTribe", mCodeTribe);

        return result;
    }
}
