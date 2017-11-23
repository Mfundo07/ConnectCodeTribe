package com.example.android.connectcodetribe.Model;

/**
 * Created by Admin on 10/18/2017.
 */

public class Education {
    private String mQualification;
    private String mDesc;
    private String mInstitute;

    public Education() {
    }

    public String getQualification() {

        return mQualification;
    }

    public void setQualification(String qualification) {
        mQualification = qualification;
    }

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String desc) {
        mDesc = desc;
    }

    public String getInstitute() {
        return mInstitute;
    }

    public void setInstitute(String institute) {
        mInstitute = institute;
    }

    public Education(String qualification, String desc, String institute) {

        mQualification = qualification;
        mDesc = desc;
        mInstitute = institute;
    }


}


