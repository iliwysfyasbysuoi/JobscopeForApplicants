package com.mobdeve.nievas.jobscopeforapplicants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.ResourceBundle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JobDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvEmployer;
    private TextView tvTitle;
    private TextView tvDescription;
    private TextView tvLocation;
    private TextView tvResponsibilities;
    private TextView tvSpecialization;
    private TextView tvEducation;
    private Button btnApply;
    private SharedPreferences sharedPreferences;

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000"; //localhost of computer or emulator idk

    private int JOBLISTINGID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);

        // for connecting to mongodb/js server
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        tvEmployer = (TextView) findViewById(R.id.tvEmployer);
        tvTitle = (TextView) findViewById(R.id.tvJobTitle);
        tvDescription =  (TextView)findViewById(R.id.tvDescription);
        tvLocation = (TextView) findViewById(R.id.tvLocation);
        tvResponsibilities = (TextView) findViewById(R.id.tvResponsibilities);
        tvSpecialization = (TextView) findViewById(R.id.tvSpecialization);
        tvEducation = (TextView) findViewById(R.id.tvEducation);
        btnApply = (Button) findViewById(R.id.btnApply);

        Bundle bundle = getIntent().getExtras();
        sharedPreferences = getSharedPreferences("SavedPreferences", Context.MODE_PRIVATE);
        JobListing joblisting = (JobListing) getIntent().getSerializableExtra("job");

        tvEmployer.setText("Employer: " + joblisting.getEmployer() );
        tvTitle.setText("Job Title: " + joblisting.getTitle());
        tvDescription.setText("Job Description: " + joblisting.getDescription());
        tvLocation.setText("Location: " + joblisting.getLocation());
        tvResponsibilities.setText("Responsibilities: " + joblisting.getResponsibilities());
        tvSpecialization.setText("Specialziation: " + joblisting.getSpecialization());
        tvEducation.setText("Education: " + joblisting.getEducation());

        JOBLISTINGID = joblisting.getJobListingID();

        btnApply.setOnClickListener(this);

        // checks if the user already applied to the job so that the app can disable the apply button
        // serves as a bridge for data from UI to server
        HashMap<String, String> map = new HashMap<>();
        map.put("applicant", sharedPreferences.getString("LOGGED_USER_KEY", ""));
        map.put("jobListingID", String.valueOf(JOBLISTINGID));



        Call<Void> call = retrofitInterface.executeUserHasAppliedAlready(map);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                // if successful
                if (response.code() == 404) {
//                    Toast.makeText(JobDetailsActivity.this, "Applied already!", Toast.LENGTH_LONG).show();
                    btnApply.setText("Applied Already");
                    btnApply.setEnabled(false);
                }else if (response.code() == 200){
//                    Toast.makeText(JobDetailsActivity.this, "Not yet applied.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                //shows the error
                Toast.makeText(JobDetailsActivity.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
            switch(v.getId()){
                case R.id.btnApply: {
                    String applicant;
                    Integer joblistingID;

                    applicant = sharedPreferences.getString("LOGGED_USER_KEY", "");

                    joblistingID = JOBLISTINGID;



                    // serves as a bridge for data from UI to server
                    HashMap<String, String> map = new HashMap<>();
                    map.put("applicant", applicant);
                    map.put("jobListingID", String.valueOf(joblistingID));
                    map.put("status", "pending");
                    map.put("employer", tvEmployer.getText().toString().split(":")[1].trim());
                    map.put("title", tvTitle.getText().toString().split(":")[1].trim());
                    map.put("description", tvDescription.getText().toString().split(":")[1].trim());
                    map.put("location", tvLocation.getText().toString().split(":")[1].trim());
                    map.put("responsibilities", tvResponsibilities.getText().toString().split(":")[1].trim());
                    map.put("specialization", tvSpecialization.getText().toString().split(":")[1].trim());
                    map.put("education", tvEducation.getText().toString().split(":")[1].trim());

                    Call<Void> call = retrofitInterface.executeApplyForJob(map);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            // if successful
                            if (response.code() == 200) {
                                Toast.makeText(JobDetailsActivity.this, "Applied Successfully!", Toast.LENGTH_LONG).show();
                                btnApply.setText("Applied Already");
                                btnApply.setEnabled(false);

                            }else{

                            }

                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            //shows the error
                            Toast.makeText(JobDetailsActivity.this, t.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });



                }
                break;
            }

    }
}
