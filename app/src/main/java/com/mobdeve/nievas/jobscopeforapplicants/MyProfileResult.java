package com.mobdeve.nievas.jobscopeforapplicants;

import java.util.ArrayList;

public class MyProfileResult {

    private String username;
    private String name;
    private String contact;
    private String email;
    private String education;
    private String experience;
    private String skill;

    private ArrayList<Applications> arrApplications;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public ArrayList<Applications> getArrApplications() {
        return arrApplications;
    }

    public void setArrApplications(ArrayList<Applications> arrApplications) {
        this.arrApplications = arrApplications;
    }
}


