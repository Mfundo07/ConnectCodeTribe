package com.example.android.connectcodetribe.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 10/18/2017.
 */

public class Education {
    private String mQualifiction;
    private String mDesc;
    private String mInstitute;

    public Education() {
    }

    public String getQualifiction() {

        return mQualifiction;
    }

    public void setQualifiction(String qualifiction) {
        mQualifiction = qualifiction;
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

    public Education(String qualifiction, String desc, String institute) {

        mQualifiction = qualifiction;
        mDesc = desc;
        mInstitute = institute;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("qualificationInstitution", mInstitute);
        result.put("qualificationDescription", mDesc);
        result.put("company_name", mQualifiction);
        return result;
    }
}


