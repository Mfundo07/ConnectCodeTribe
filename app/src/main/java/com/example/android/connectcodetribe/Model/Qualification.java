package com.example.android.connectcodetribe.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 10/18/2017.
 */

public class Qualification {

    private String userDegree;
    private String userInstitution;
    private String startYear;
    private String endYear;
    private String faculty;
    private String major;

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Qualification(String userDegree, String userInstitution, String startYear, String endYear, String faculty, String major) {

        this.userDegree = userDegree;
        this.userInstitution = userInstitution;
        this.startYear = startYear;
        this.endYear = endYear;
        this.faculty = faculty;
        this.major = major;
    }

    public String getUserDegree() {
        return userDegree;
    }

    public void setUserDegree(String userDegree) {
        this.userDegree = userDegree;
    }

    public String getUserInstitution() {
        return userInstitution;
    }

    public void setUserInstitution(String userInstitution) {
        this.userInstitution = userInstitution;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public Qualification(String userDegree, String userInstitution, String startYear, String endYear) {

        this.userDegree = userDegree;
        this.userInstitution = userInstitution;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public Qualification() {
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("userDegree", userDegree);
        result.put("userInstitution", userInstitution);
        result.put("userStartYear", startYear);
        result.put("userEndYear", endYear);
        result.put("userDegreeFaculty",faculty);
        result.put("userFacultyMajor", major);


        return result;
    }
}
