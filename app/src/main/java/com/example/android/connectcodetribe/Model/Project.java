package com.example.android.connectcodetribe.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 10/23/2017.
 */

public class Project {

    String projectDisplayPicture, projectTitle, projectUrl;

    public Project() {
    }

    public Project(String projectDisplayPicture, String projectTitle, String projectUrl) {
        this.projectDisplayPicture = projectDisplayPicture;
        this.projectTitle = projectTitle;
        this.projectUrl = projectUrl;
    }

    public String getProjectDisplayPicture() {
        return projectDisplayPicture;
    }

    public void setProjectDisplayPicture(String projectDisplayPicture) {
        this.projectDisplayPicture = projectDisplayPicture;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("display_picture", this.projectDisplayPicture);
        result.put("title", this.projectTitle);
        result.put("url", this.projectUrl);
        return result;
    }
}
