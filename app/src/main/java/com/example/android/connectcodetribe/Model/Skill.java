package com.example.android.connectcodetribe.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 10/23/2017.
 */

public class Skill {

    String title, skillImage;
    long skillLevel;

    public Skill() {
    }

    public Skill(String title, long skillLevel,String skillImage) {
        this.title = title;
        this.skillLevel = skillLevel;
        this.skillImage = skillImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }




    public String getSkillImage() {
        return skillImage;
    }

    public void setSkillImage(String skillImage) {
        this.skillImage = skillImage;
    }





    public long getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(long skillLevel) {
        this.skillLevel = skillLevel;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("skill", title);
        result.put("level", skillLevel);
        return result;
    }

}
