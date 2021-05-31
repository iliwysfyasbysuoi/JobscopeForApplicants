package com.mobdeve.nievas.jobscopeforapplicants;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/loginApplicant")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> map);

    @POST("/ApplicantRegister")
    Call<Void> executeApplicantRegister(@Body HashMap<String, String> map);

    @POST("/GetAllJobListing")
    Call<JobListingResult> GetAllJobListing(@Body HashMap<String, String> map);

    @POST("/ApplyForJob")
    Call<Void> executeApplyForJob(@Body HashMap<String, String> map);

    @POST("/UserHasAppliedAlready")
    Call<Void> executeUserHasAppliedAlready(@Body HashMap<String, String> map);

    @POST("/GetMyProfileApplicant")
    Call<MyProfileResult> executeGetMyProfileApplicant(@Body HashMap<String, String> map);





}
