package com.example.android.connectcodetribe.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 10/18/2017.
 */

public class Education {

    private String mQualification;
    private  String mDescription;
    private String mInstitution;

    public String getQualification() {
        return mQualification;
    }

    public void setQualification(String qualification) {
        mQualification = qualification;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getInstitution() {
        return mInstitution;
    }

    public void setInstitution(String institution) {
        mInstitution = institution;
    }

    public Education(String qualification, String description, String institution) {

        mQualification = qualification;
        mDescription = description;
        mInstitution = institution;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("qualification", mQualification);
        result.put("institution", mInstitution);
        result.put("description", mDescription);



        return result;
    }
}
