package com.mobdeve.nievas.jobscopeforapplicants;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mongodb.util.JSON;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FindJobActivity extends AppCompatActivity implements View.OnClickListener, JobListingAdapter.OnOrderListener {

    private RecyclerView rvJobListings;
    private Button btnProfile;
    private Button btnLogout;
    private Button btnFindJobs;
    private JobListingAdapter jobListingAdapter;

    private SharedPreferences sharedpreferences; //for storing current logged user
    private ArrayList<JobListing> arrJobListing = new ArrayList<>();

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000"; //localhost of computer or emulator idk

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_job);

        // for connecting to mongodb/js server
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        rvJobListings = findViewById(R.id.rvJobListings);

        btnProfile = findViewById(R.id.btnProfile);
        btnLogout = findViewById(R.id.btnLogout);
        btnFindJobs = findViewById(R.id.btnFindJobs);

        btnProfile.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
        btnFindJobs.setOnClickListener(this);


        // serves as a bridge for data from UI to server
        arrJobListing.clear();
        HashMap<String, String> map = new HashMap<>();

        Call<JobListingResult> call = retrofitInterface.GetAllJobListing(map);

        // calls an http request
        call.enqueue(new Callback<JobListingResult>() {
            @Override
            public void onResponse(Call<JobListingResult> call, Response<JobListingResult> response) {
                if(response.code() == 200){
                    Gson gson = new Gson();
//                    ArrayList<JobListing> result = response.body(); // the result from server
                    JobListingResult result = response.body();

//                    Log.d("LIST",  "result.zise() " + result.getArrJobListing().get(0).getJobListingID());



                    int i;
                    JobListing JO, job;
                    for(  i = 0; i < result.getArrJobListing().size() ; i++ ){
                        JO =  result.getArrJobListing().get(i);
                        job = new JobListing(JO.getJobListingID(), JO.getEmployer(),JO.getTitle(),JO.getDescription(),JO.getLocation(),JO.getResponsibilities(),JO.getSpecialization(),JO.getEducation());
                        arrJobListing.add(job);
                    }



                    // lists the jobs in recyclerview
                    if (arrJobListing.size() != 0) {
                        jobListingAdapter = new JobListingAdapter(FindJobActivity.this, arrJobListing, FindJobActivity.this);
                        rvJobListings.setAdapter(jobListingAdapter);
                        rvJobListings.setLayoutManager(new LinearLayoutManager(FindJobActivity.this));
                    }
                }
            }
            @Override
            public void onFailure(Call<JobListingResult> call, Throwable t) {
                //shows the error
                Toast.makeText(FindJobActivity.this, t.getMessage() ,
                        Toast.LENGTH_LONG).show();
                Log.d("LIST",  t.getMessage());

            }
        });
    }

    public void onPause() {
        super.onPause();
        // serves as a bridge for data from UI to server
        arrJobListing.clear();
        HashMap<String, String> map = new HashMap<>();

        Call<JobListingResult> call = retrofitInterface.GetAllJobListing(map);

        // calls an http request
        call.enqueue(new Callback<JobListingResult>() {
            @Override
            public void onResponse(Call<JobListingResult> call, Response<JobListingResult> response) {
                if(response.code() == 200){
                    Gson gson = new Gson();
//                    ArrayList<JobListing> result = response.body(); // the result from server
                    JobListingResult result = response.body();

                    Log.d("LIST",  "result.zise() " + result.getArrJobListing().get(0).getJobListingID());



                    int i;
                    JobListing JO, job;
                    for(  i = 0; i < result.getArrJobListing().size() ; i++ ){
                        JO =  result.getArrJobListing().get(i);
                        job = new JobListing(JO.getJobListingID(), JO.getEmployer(),JO.getTitle(),JO.getDescription(),JO.getLocation(),JO.getResponsibilities(),JO.getSpecialization(),JO.getEducation());
                        arrJobListing.add(job);
                    }



                    // lists the jobs in recyclerview
                    if (arrJobListing.size() != 0) {
                        jobListingAdapter = new JobListingAdapter(FindJobActivity.this, arrJobListing, FindJobActivity.this);
                        rvJobListings.setAdapter(jobListingAdapter);
                        rvJobListings.setLayoutManager(new LinearLayoutManager(FindJobActivity.this));
                    }
                }
            }
            @Override
            public void onFailure(Call<JobListingResult> call, Throwable t) {
                //shows the error
                Toast.makeText(FindJobActivity.this, t.getMessage() ,
                        Toast.LENGTH_LONG).show();
                Log.d("LIST",  t.getMessage());

            }
        });
    }




    @Override
    public void onOrderClick(int position) {
        Intent intent = new Intent(this,  JobDetailsActivity.class);
        //putting the Serialized object in Intent
        intent.putExtra("job", arrJobListing.get(position));
        startActivity(intent);
    }



    @Override
    public void onClick(View v) {

        switch(v.getId()) {


            case R.id.btnProfile: {
                Intent intentProfile = new Intent(this, MyProfileActivity.class);
                startActivity(intentProfile);

            }
            break;

            case R.id.btnFindJobs: {


            }
            break;

            case R.id.btnLogout: {

                Toast.makeText(FindJobActivity.this, "pressed logout", Toast.LENGTH_LONG).show();
                sharedpreferences = getSharedPreferences("SavedPreferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                //sets LOGGED_USER_KEY value to "" indicating that no user is logged in.
                editor.putString("LOGGED_USER_KEY", "");
                editor.commit();

                Intent intentLogout = new Intent(this, LoginActivity.class);
                startActivity(intentLogout);
                finish();
            }
            break;

        }


    }


}
