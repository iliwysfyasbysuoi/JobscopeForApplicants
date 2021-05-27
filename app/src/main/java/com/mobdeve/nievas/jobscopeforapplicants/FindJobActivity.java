package com.mobdeve.nievas.jobscopeforapplicants;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FindJobActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rvJobListings;
    private Button btnProfile;
    private Button btnLogout;
    private Button btnFindJobs;

    private SharedPreferences sharedpreferences; //for storing current logged user

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_job);

        rvJobListings = findViewById(R.id.rvJobListings);
        btnProfile = findViewById(R.id.btnProfile);
        btnLogout = findViewById(R.id.btnLogout);
        btnFindJobs = findViewById(R.id.btnFindJobs);

        btnProfile.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
        btnFindJobs.setOnClickListener(this);


    }



    @Override
    public void onClick(View v) {

        switch(v.getId()) {


            case R.id.btnProfile: {

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
