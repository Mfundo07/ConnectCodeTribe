package com.example.android.connectcodetribe.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 10/27/2017.
 */

public class Experience {
    String startYear;
    String endYear;
    String CompanyName;
    String position;

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

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Experience() {

    }

    public Experience(String startYear, String endeYear, String companyName, String position) {
        this.startYear = startYear;
        this.endYear = endeYear;
        CompanyName = companyName;

        this.position = position;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("startYear", startYear);
        result.put("endYear", endYear);
        result.put("company_name", CompanyName );
        result.put("job_position",position);
        return result;
    }
}
