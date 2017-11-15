package com.example.android.connectcodetribe.Model;

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
}