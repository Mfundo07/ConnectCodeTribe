package com.example.android.connectcodetribe.Model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Admin on 11/13/2017.
 */

public class ProfileLab {
    private static  ProfileLab sProfileLab;
    private List<Profile> mProfiles;

    public static ProfileLab get(Context context){
        if (sProfileLab == null){
            sProfileLab = new ProfileLab(context);
        }
        return sProfileLab;
    }

    private ProfileLab(Context context){
        mProfiles = new ArrayList<>();
        for (int i = 0; i < mProfiles.size() ; i++) {

            Profile profile = new Profile();

        }
    }

    public List<Profile> getProfiles(){return mProfiles;}

    public Profile getProfile(UUID id){
        for (Profile profile: mProfiles){
            if (profile.getId().equals(id)){
                return profile;
            }
        }return null;
    }


}
