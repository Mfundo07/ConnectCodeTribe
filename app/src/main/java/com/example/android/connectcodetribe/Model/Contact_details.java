package com.example.android.connectcodetribe.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 11/17/2017.
 */

public class Contact_details {
    public String getMobile() {
        return mMobile;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getIntake() {
        return mIntake;
    }

    public String getTribe() {
        return mTribe;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setMobile(String mobile) {
        mMobile = mobile;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public void setIntake(String intake) {
        mIntake = intake;
    }

    public void setTribe(String tribe) {
        mTribe = tribe;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    private String mMobile;
    private String mEmail;
    private String mIntake;
    private String mTribe;
    private String mStatus;

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("mobileMobile", mMobile);
        result.put("emailAddress", mEmail);
        result.put("status", mStatus);
        result.put("codeTribeLocation", mTribe);
        return result;
    }
}
