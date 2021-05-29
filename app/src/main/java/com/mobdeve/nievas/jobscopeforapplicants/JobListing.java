package com.mobdeve.nievas.jobscopeforapplicants;

import org.bson.types.ObjectId;

import java.io.Serializable;

public class JobListing implements Serializable {
    private Integer jobListingID;
    private String employer;
    private String title;
    private String description;
    private String location;
    private String responsibilities;
    private String specialization;
    private String education;

    public JobListing(){

    }

    public JobListing(Integer jobListingID, String employer, String title, String description, String location, String responsibilities, String specialization, String education) {
        this.jobListingID = jobListingID;
        this.employer = employer;
        this.title = title;
        this.description = description;
        this.location = location;
        this.responsibilities = responsibilities;
        this.specialization = specialization;
        this.education = education;
    }

    public Integer getJobListingID() {
        return jobListingID;
    }

    public String getEmployer() {
        return employer;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getEducation() {
        return education;
    }

    public void setJobListingID(Integer jobListingID) {
        this.jobListingID = jobListingID;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setEducation(String education) {
        this.education = education;
    }




}
