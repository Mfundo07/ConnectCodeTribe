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

    private String mQualification;
    private String mDesc;
    private String mInstitute;

    private String mEmploymentStatus;
    private String mCompanyContactNumber;
    private String mCompanyName;
    private String mSalary;
    private String mStartDate;

    private String mCodeTribeLocation;
    private String mEmployeeCode;
    private String mCodeTribeProgramStatus;
    private String mTribeUnderline;
    private String mTribeEmploymentCodeUnderline;

    private String mProjectTitle;
    private String mProjectLink;

    public String getProjectTitle() {
        return mProjectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        mProjectTitle = projectTitle;
    }

    public String getProjectLink() {
        return mProjectLink;
    }

    public void setProjectLink(String projectLink) {
        mProjectLink = projectLink;
    }

    public String getTribeUnderline() {
        return mTribeUnderline;
    }

    public void setTribeUnderline(String tribeUnderline) {
        mTribeUnderline = tribeUnderline;
    }

    public String getTribeEmploymentCodeUnderline() {
        return mTribeEmploymentCodeUnderline;
    }

    public void setTribeEmploymentCodeUnderline(String tribeEmploymentCodeUnderline) {
        mTribeEmploymentCodeUnderline = tribeEmploymentCodeUnderline;
    }

    public String getCodeTribeLocation() {
        return mCodeTribeLocation;
    }

    public void setCodeTribeLocation(String codeTribeLocation) {
        mCodeTribeLocation = codeTribeLocation;
    }

    public String getEmployeeCode() {
        return mEmployeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        mEmployeeCode = employeeCode;
    }

    public String getCodeTribeProgramStatus() {
        return mCodeTribeProgramStatus;
    }

    public void setCodeTribeProgramStatus(String codeTribeProgramStatus) {
        mCodeTribeProgramStatus = codeTribeProgramStatus;
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

    public String getEmploymentStatus() {
        return mEmploymentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        mEmploymentStatus = employmentStatus;
    }

    public String getCompanyContactNumber() {
        return mCompanyContactNumber;
    }

    public void setCompanyContactNumber(String companyContactNumber) {
        mCompanyContactNumber = companyContactNumber;
    }

    public String getCompanyName() {
        return mCompanyName;
    }

    public void setCompanyName(String companyName) {
        mCompanyName = companyName;
    }

    public String getSalary() {
        return mSalary;
    }

    public void setSalary(String salary) {
        mSalary = salary;
    }

    public String getStartDate() {
        return mStartDate;
    }

    public void setStartDate(String startDate) {
        mStartDate = startDate;
    }

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
        result.put("age", mAge);
        result.put("name", mName);
        result.put("surname", mSurname);
        result.put("employeeCode", mEMC);
        result.put("emailAddress",mEmail);
        result.put("mobileNo", mMobile);
        result.put("profile_picture",mProfileImage);
        result.put("intakePeriod", mIntakeYear);
        result.put("gender", mGender);
        result.put("ethnicity",mEthnicity);
        result.put("employed", mEmploymentStatus);
        result.put("companyContactDetails", mCompanyContactNumber);
        result.put("companyName", mCompanyName);
        result.put("monthlySalary(ZAR)", mSalary);
        result.put("startDate", mStartDate);
        result.put("highestQualification", mQualification);
        result.put("qualificationDescription", mDesc);
        result.put("qualificationInstitution", mInstitute);
        result.put("codeTribeLocation", mCodeTribeLocation);
        result.put("employeeCode", mEmployeeCode);
        result.put("status", mCodeTribeProgramStatus);
        result.put("tribe_underline", mTribeUnderline);
        result.put("code_underline", mTribeEmploymentCodeUnderline);
        result.put("project_name", mProjectTitle);
        result.put("github_link", mProjectLink);
        return result;
    }


}
