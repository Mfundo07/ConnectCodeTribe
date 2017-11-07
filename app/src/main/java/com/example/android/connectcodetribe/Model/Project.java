package com.example.android.connectcodetribe.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 10/23/2017.
 */

public class Project {

    private String snapshot, name, github_link;

    public Project() {
    }

    public Project(String projectDisplayPicture, String projectTitle, String projectUrl) {
        this.snapshot = projectDisplayPicture;
        this.name = projectTitle;
        this.github_link = projectUrl;
    }

    public String getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGithub_link() {
        return github_link;
    }

    public void setGithub_link(String github_link) {
        this.github_link = github_link;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("snapshot", this.snapshot);
        result.put("name", this.name);
        result.put("github_link", this.github_link);
        return result;
    }
}
