package com.mobdeve.nievas.jobscopeforapplicants;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyProfileActivity extends AppCompatActivity implements MyApplicationsAdapter.OnOrderListener{

    private TextView tvName;
    private TextView   tvUsername ;
    private TextView tvContact;
    private TextView tvEmail   ;
    private TextView  tvEducation  ;
    private TextView  tvExperience  ;
    private TextView  tvSkills;
    private RecyclerView rvMyApplications;
    private MyApplicationsAdapter myApplicationsAdapter;

    private SharedPreferences sharedPreferences;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000"; //localhost of computer or emulator idk


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        tvName = findViewById(R.id.tvName);
        tvUsername = findViewById(R.id.tvUsername);
        tvContact = findViewById(R.id.tvContact);
        tvEmail = findViewById(R.id.tvEmail);
        tvEducation = findViewById(R.id.tvEducation);
        tvExperience = findViewById(R.id.tvExperience);
        tvSkills = findViewById(R.id.tvSkills);

        rvMyApplications = findViewById(R.id.rvMyApplications);

        // for connecting to mongodb/js server
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        sharedPreferences = getSharedPreferences("SavedPreferences", Context.MODE_PRIVATE);

        // serves as a bridge for data from UI to server
        HashMap<String, String> map = new HashMap<>();
        map.put("applicant", sharedPreferences.getString("LOGGED_USER_KEY", ""));

        Call<MyProfileResult> call = retrofitInterface.executeGetMyProfileApplicant(map);
        call.enqueue(new Callback<MyProfileResult>() {
            @Override
            public void onResponse(Call<MyProfileResult> call, Response<MyProfileResult> response) {

                MyProfileResult myProfileResult = response.body();
                ArrayList<Applications> arrMyApplications = new ArrayList<>();
                arrMyApplications = myProfileResult.getArrApplications();


                // if successful
                if (response.code() == 200) {


                    tvName.setText("Name: " + myProfileResult.getName());
                    tvUsername.setText("Username: " + myProfileResult.getUsername());
                    tvContact.setText("Contact: " + myProfileResult.getContact());
                    tvEmail.setText("Email: " + myProfileResult.getEmail());
                    tvEducation.setText("Education: " + myProfileResult.getEducation());
                    tvExperience.setText("Experience: " + myProfileResult.getExperience());
                    tvSkills.setText("Skills: " + myProfileResult.getSkill());


                    Log.d("GETMYPROFILE", String.valueOf(arrMyApplications.get(0).getEducation()));

//                    // lists the jobs in recyclerview
                    if (arrMyApplications.size() != 0) {
                        myApplicationsAdapter = new MyApplicationsAdapter(MyProfileActivity.this, arrMyApplications);
                        rvMyApplications.setAdapter(myApplicationsAdapter);
                        rvMyApplications.setLayoutManager(new LinearLayoutManager(MyProfileActivity.this));
                    }



                }else{
                    Toast.makeText(MyProfileActivity.this, "error!", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<MyProfileResult> call, Throwable t) {
                //shows the error
                Toast.makeText(MyProfileActivity.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();

                Log.d("GETMYPROFILE", t.getMessage());
            }
        });




    }


    @Override
    public void onOrderClick(int position) {
        Toast.makeText(MyProfileActivity.this, "CLICKED",
                Toast.LENGTH_LONG).show();

    }


}
