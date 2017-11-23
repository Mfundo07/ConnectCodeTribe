package com.example.android.connectcodetribe.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 11/10/2017.
 */

public class TribeMate {
    private String mName;
    private String mSurname;
    private String mEMC;
    private String mGender;
    private String mEthnicity;
    private String mAge;
    private String mMobile;
    private String mEmail;
    private String mIntakeYear;
    private String mStatus;
    private String mCodeTribe;
    private String mProfileImage;

    public String getBio() {
        return mBio;
    }

    public void setBio(String bio) {
        mBio = bio;
    }

    private String mBio;

    public String getProfileImage() {
        return mProfileImage;
    }

    public void setProfileImage(String profileImage) {
        mProfileImage = profileImage;
    }

    public String getCodeTribe() {
        return mCodeTribe;
    }

    public void setCodeTribe(String codeTribe) {
        mCodeTribe = codeTribe;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getIntakeYear() {
        return mIntakeYear;
    }

    public void setIntakeYear(String intakeYear) {
        mIntakeYear = intakeYear;
    }

    public TribeMate(String name, String surname, String EMC, String gender, String ethnicity, String age, String mobile, String email, String intakeYear) {

        mName = name;
        mSurname = surname;
        mEMC = EMC;
        mGender = gender;
        mEthnicity = ethnicity;
        mAge = age;
        mMobile = mobile;
        mEmail = email;
        mIntakeYear = intakeYear;
    }

    public TribeMate() {
    }

    public String getName() {

        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSurname() {
        return mSurname;
    }

    public void setSurname(String surname) {
        mSurname = surname;
    }

    public String getEMC() {
        return mEMC;
    }

    public void setEMC(String EMC) {
        mEMC = EMC;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public String getEthnicity() {
        return mEthnicity;
    }

    public void setEthnicity(String ethnicity) {
        mEthnicity = ethnicity;
    }

    public String getAge() {
        return mAge;
    }

    public void setAge(String age) {
        mAge = age;
    }

    public String getMobile() {
        return mMobile;
    }

    public void setMobile(String mobile) {
        mMobile = mobile;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }


    public TribeMate(String name, String surname, String EMC, String gender, String ethnicity, String age, String mobile, String email) {

        mName = name;
        mSurname = surname;
        mEMC = EMC;
        mGender = gender;
        mEthnicity = ethnicity;
        mAge = age;
        mMobile = mobile;
        mEmail = email;

    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", mName);
        result.put("surname", mSurname);
        result.put("gender", mGender);
        result.put("age", mAge);
        result.put("ethnicity", mEthnicity);
        result.put("cell_number", mMobile);
        result.put("email", mEmail);
        result.put("profile_image", mProfileImage);
        result.put("bio", mBio);
        result.put("employee_code", mEMC);
        return result;
    }
}
