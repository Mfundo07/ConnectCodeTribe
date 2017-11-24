package com.example.android.connectcodetribe.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 11/21/2017.
 */

public class CodeTribe {
    private String mCodeTribeLocation;
    private String mEmployeeCode;
    private String mCodeTribeProgramStatus;

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

    public CodeTribe() {

    }

    public CodeTribe(String codeTribeLocation, String employeeCode, String codeTribeProgramStatus) {

        mCodeTribeLocation = codeTribeLocation;
        mEmployeeCode = employeeCode;
        mCodeTribeProgramStatus = codeTribeProgramStatus;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("codeTribeLocation", mCodeTribeLocation);
        result.put("employeeCode", mEmployeeCode);
        result.put("status", mCodeTribeProgramStatus);
        return result;
    }


}
