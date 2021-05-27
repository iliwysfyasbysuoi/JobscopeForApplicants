package com.mobdeve.nievas.jobscopeforapplicants;

import java.util.ArrayList;

public class JobListingResult{

    private ArrayList<JobListing> arrJobListing = new ArrayList<>();


    public ArrayList<JobListing> getArrJobListing() {
        return arrJobListing;
    }

    public void setArrJobListing(ArrayList<JobListing> arrJobListing) {
        this.arrJobListing = arrJobListing;
    }

    public void addJobListing(JobListing jobListing){
        this.arrJobListing.add(jobListing);
    }
}
