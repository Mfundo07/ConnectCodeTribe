package com.example.android.connectcodetribe.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

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
    private String mIntakeYear;
    private String mDisability;
    private String mProfileCellNumber;
    private String mProfileQualification;
    private String mProfileEthnicity;
    private String mProfileInstitute;
    private String mProfileQualDescription;
    private String mProfileSubjectName;
    private String mProfileEmploymentStatus;
    private String mProfileCompaanyName;
    private String mProfileJobTitle;
    private String mProfileEMC;
    private String mProfileStartDate;
    private String mProfileSalary;
    private String mProfileCompanyDetails;
    private String mProfileAge;
    private String mProfileGender;

    public String getProfileGender() {
        return mProfileGender;
    }

    public void setProfileGender(String profileGender) {
        mProfileGender = profileGender;
    }

    public String getProfileAge() {
        return mProfileAge;
    }

    public void setProfileAge(String profileAge) {
        mProfileAge = profileAge;
    }

    public Profile() {
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

    public String getCodeTribe() {
        return mCodeTribe;
    }

    public void setCodeTribe(String codeTribe) {
        mCodeTribe = codeTribe;
    }

    public String getProfileSurname() {
        return mProfileSurname;
    }

    public void setProfileSurname(String profileSurname) {
        mProfileSurname = profileSurname;
    }

    public String getIntakeYear() {
        return mIntakeYear;
    }

    public void setIntakeYear(String intakeYear) {
        mIntakeYear = intakeYear;
    }

    public String getDisability() {
        return mDisability;
    }

    public void setDisability(String disability) {
        mDisability = disability;
    }

    public String getProfileCellNumber() {
        return mProfileCellNumber;
    }

    public void setProfileCellNumber(String profileCellNumber) {
        mProfileCellNumber = profileCellNumber;
    }

    public String getProfileQualification() {
        return mProfileQualification;
    }

    public void setProfileQualification(String profileQualification) {
        mProfileQualification = profileQualification;
    }

    public String getProfileEthnicity() {
        return mProfileEthnicity;
    }

    public void setProfileEthnicity(String profileEthnicity) {
        mProfileEthnicity = profileEthnicity;
    }

    public String getProfileInstitute() {
        return mProfileInstitute;
    }

    public void setProfileInstitute(String profileInstitute) {
        mProfileInstitute = profileInstitute;
    }

    public String getProfileQualDescription() {
        return mProfileQualDescription;
    }

    public void setProfileQualDescription(String profileQualDescription) {
        mProfileQualDescription = profileQualDescription;
    }

    public String getProfileSubjectName() {
        return mProfileSubjectName;
    }

    public void setProfileSubjectName(String profileSubjectName) {
        mProfileSubjectName = profileSubjectName;
    }

    public String getProfileEmploymentStatus() {
        return mProfileEmploymentStatus;
    }

    public void setProfileEmploymentStatus(String profileEmploymentStatus) {
        mProfileEmploymentStatus = profileEmploymentStatus;
    }

    public String getProfileCompaanyName() {
        return mProfileCompaanyName;
    }

    public void setProfileCompaanyName(String profileCompaanyName) {
        mProfileCompaanyName = profileCompaanyName;
    }

    public String getProfileJobTitle() {
        return mProfileJobTitle;
    }

    public void setProfileJobTitle(String profileJobTitle) {
        mProfileJobTitle = profileJobTitle;
    }

    public String getProfileEMC() {
        return mProfileEMC;
    }

    public void setProfileEMC(String profileEMC) {
        mProfileEMC = profileEMC;
    }

    public String getProfileStartDate() {
        return mProfileStartDate;
    }

    public void setProfileStartDate(String profileStartDate) {
        mProfileStartDate = profileStartDate;
    }

    public String getProfileSalary() {
        return mProfileSalary;
    }

    public void setProfileSalary(String profileSalary) {
        mProfileSalary = profileSalary;
    }

    public String getProfileCompanyDetails() {
        return mProfileCompanyDetails;
    }

    public void setProfileCompanyDetails(String profileCompanyDetails) {
        mProfileCompanyDetails = profileCompanyDetails;
    }

    public Profile(String profileImage, String profileName, String status, String occupation, String profileEmail, String codeTribe, String profileSurname, String intakeYear, String disability, String profileCellNumber, String profileQualification, String profileEthnicity, String profileInstitute, String profileQualDescription, String profileSubjectName, String profileEmploymentStatus, String profileCompaanyName, String profileJobTitle, String profileEMC, String profileStartDate, String profileSalary, String profileCompanyDetails) {

        mProfileImage = profileImage;
        mProfileName = profileName;
        mStatus = status;
        mOccupation = occupation;
        mProfileEmail = profileEmail;
        mCodeTribe = codeTribe;
        mProfileSurname = profileSurname;
        mIntakeYear = intakeYear;
        mDisability = disability;
        mProfileCellNumber = profileCellNumber;
        mProfileQualification = profileQualification;
        mProfileEthnicity = profileEthnicity;
        mProfileInstitute = profileInstitute;
        mProfileQualDescription = profileQualDescription;
        mProfileSubjectName = profileSubjectName;
        mProfileEmploymentStatus = profileEmploymentStatus;
        mProfileCompaanyName = profileCompaanyName;
        mProfileJobTitle = profileJobTitle;
        mProfileEMC = profileEMC;
        mProfileStartDate = profileStartDate;
        mProfileSalary = profileSalary;
        mProfileCompanyDetails = profileCompanyDetails;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("display_picture", mProfileImage);
        result.put("name", mProfileName);
        result.put("occupation", mOccupation);
        result.put("email", mProfileEmail);
        result.put("status", mStatus);
        result.put("age", mProfileAge);
        result.put("codeTribeLocation", mCodeTribe);
        result.put("companyContactDetails", mProfileCompanyDetails);
        result.put("disability", mDisability);
        result.put("emailAddress", mProfileEmail);
        result.put("employed", mProfileEmploymentStatus);
        result.put("ethnicity", mProfileEthnicity);
        result.put("gender", mProfileGender);
        result.put("highestQualification", mProfileQualification);
        result.put("qualificationDescription", mProfileQualDescription);
        return result;
    }
}
