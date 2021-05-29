package com.mobdeve.nievas.jobscopeforapplicants;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        sharedpreferences = getSharedPreferences("SavedPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        String currentUser = sharedpreferences.getString("LOGGED_USER_KEY", "");

//        Toast.makeText(this, currentUser, Toast.LENGTH_LONG).show();


        if(currentUser.equals("none") == true || currentUser.equals("") == true ){
            // goes to LoginActivity
            Intent intentLogin = new Intent(this,  LoginActivity.class);
            startActivity(intentLogin);
            finish();

        }else{
            // TODO codes for going to the applicants's homepage
            Intent intentApplicantsHomepage = new Intent(this,  FindJobActivity.class);
            startActivity(intentApplicantsHomepage);
            finish();
        }



    }



}
