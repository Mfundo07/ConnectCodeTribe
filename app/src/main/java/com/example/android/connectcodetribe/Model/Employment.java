package com.example.android.connectcodetribe.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 11/10/2017.
 */

public class Employment {
    public Employment() {
    }

    public String getEmploy() {
        return mEmploy;
    }

    public String getCurrent() {
        return mCurrent;
    }

    public String getCompany() {
        return mCompany;
    }

    public String getJobTitle() {
        return mJobTitle;
    }

    public String getStart() {
        return mStart;
    }

    public void setEmploy(String employ) {
        mEmploy = employ;
    }

    public void setCurrent(String current) {
        mCurrent = current;
    }

    public void setCompany(String company) {
        mCompany = company;
    }

    public void setJobTitle(String jobTitle) {
        mJobTitle = jobTitle;
    }

    public void setStart(String start) {
        mStart = start;
    }

    private String mEmploy;
    private String mCurrent;
    private String mCompany;
    private String mJobTitle;
    private String mStart;
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("employed", mEmploy);
        result.put("Job Title", mJobTitle);
        result.put("Company Name", mCompany);
        result.put("companyContactDetails", mCurrent);
        return result;
    }
}


