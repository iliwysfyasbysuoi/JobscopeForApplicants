package com.mobdeve.nievas.jobscopeforapplicants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ResourceBundle;

public class JobDetailsActivity extends AppCompatActivity {

    private TextView tvEmployer;
    private TextView tvTitle;
    private TextView tvDescription;
    private TextView tvLocation;
    private TextView tvResponsibilities;
    private TextView tvSpecialization;
    private TextView tvEducation;
    private SharedPreferences sharedPreferences;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);

            Log.d("DEETS", "(String) tvEmployer.getText()");

        tvEmployer = findViewById(R.id.tvEmployer);
        tvTitle= findViewById(R.id.tvTitle);
        tvDescription= findViewById(R.id.tvDescription);
        tvLocation= findViewById(R.id.tvLocation);
        tvResponsibilities= findViewById(R.id.tvResponsibilities);
        tvSpecialization= findViewById(R.id.tvSpecialization);
        tvEducation= findViewById(R.id.tvEducation);

        Bundle bundle = getIntent().getExtras();
        sharedPreferences = getSharedPreferences("SavedPreferences", Context.MODE_PRIVATE);
        JobListing joblisting = (JobListing) getIntent().getSerializableExtra("job");

//            findViewById(R.id.tvEmployer);
//        tvEmployer.setText("asdf");
//        tvTitle.setText("Job Title: " + joblisting.getEmployer());
//        tvDescription.setText("Job Description: " + joblisting.getEmployer());
//        tvLocation.setText("Location: " + joblisting.getEmployer());
//        tvResponsibilities.setText("Responsibilities: " + joblisting.getEmployer());
//        tvSpecialization.setText("Specialziation: " + joblisting.getEmployer());
//        tvEducation.setText("Education: " + joblisting.getEmployer());





    }
}
