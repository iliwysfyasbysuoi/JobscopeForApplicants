package com.mobdeve.nievas.jobscopeforapplicants;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/loginApplicant")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> map);

    @POST("/ApplicantRegister")
    Call<Void> executeApplicantRegister(@Body HashMap<String, String> map);

}
