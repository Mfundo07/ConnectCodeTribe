package com.example.android.connectcodetribe.Model;

/**
 * Created by Admin on 11/10/2017.
 */

public class Employment {
    private String mStartDate;
    private String mCompany;
    private String mTitle;
    private String mSalary;

    public String getStartDate() {
        return mStartDate;
    }

    public void setStartDate(String startDate) {
        mStartDate = startDate;
    }

    public String getCompany() {
        return mCompany;
    }

    public void setCompany(String company) {
        mCompany = company;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getSalary() {
        return mSalary;
    }

    public void setSalary(String salary) {
        mSalary = salary;
    }

    public Employment() {

    }

    public Employment(String startDate, String company, String title, String salary) {

        mStartDate = startDate;
        mCompany = company;
        mTitle = title;
        mSalary = salary;
    }
}
