package com.example.android.connectcodetribe.Model;

/**
 * Created by Admin on 11/10/2017.
 */

public class Employment {
    public String getEmploymentStatus() {
        return mEmploymentStatus;
    }

    public String getCompanyContactNumber() {
        return mCompanyContactNumber;
    }

    public String getCompanyName() {
        return mCompanyName;
    }

    public String getSalary() {
        return mSalary;
    }

    public String getStartDate() {
        return mStartDate;
    }

    public void setEmploymentStatus(String employmentStatus) {
        mEmploymentStatus = employmentStatus;
    }

    public void setCompanyContactNumber(String companyContactNumber) {
        mCompanyContactNumber = companyContactNumber;
    }

    public void setCompanyName(String companyName) {
        mCompanyName = companyName;
    }

    public void setSalary(String salary) {
        mSalary = salary;
    }

    public void setStartDate(String startDate) {
        mStartDate = startDate;
    }

    private String mEmploymentStatus;
    private String mCompanyContactNumber;
    private String mCompanyName;
    private String mSalary;
    private String mStartDate;

}


