package com.mobdeve.nievas.jobscopeforapplicants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static MongoClient mongoClient;
    public static DB database; // creates db inside mongoclient
    public static DBCollection userCollection; //collection inside DB

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnRegister;

    private SharedPreferences sharedpreferences; //for storing current logged user

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000"; //localhost of computer or emulator idk

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // for connecting to mongodb/js server
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        // finds the views
        this.btnRegister = findViewById(R.id.btnRegister);
        this.btnLogin = findViewById(R.id.btnLogin);


        // sets onclick listeners
        this.btnRegister.setOnClickListener(this);
        this.btnLogin.setOnClickListener(this);

        this.etUsername = findViewById(R.id.etUsername);
        this.etPassword = findViewById(R.id.etPassword);









    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.btnLogin:{
                /* TODO code for credential validation
                   use MongoDB database
                 */
//                    handleLoginDialog();

                // serves as a bridge for data from UI to server
                HashMap<String, String> map = new HashMap<>();
                map.put("username", etUsername.getText().toString());
                map.put("password", etPassword.getText().toString());

                Call<LoginResult> call = retrofitInterface.executeLogin(map);

                // calls an http request
                call.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                        if(response.code() == 200){

                            LoginResult result = response.body(); // the result from server

                            sharedpreferences = getSharedPreferences("SavedPreferences", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            // stores username to LOGGED_USER_KEY to know who is currently logged ins
                            editor.putString("LOGGED_USER_KEY", result.getUsername());
                            editor.commit();

                            Toast.makeText(LoginActivity.this, "Credentials Match", Toast.LENGTH_LONG).show();
                            Intent intentApplicationsTrackerActivity = new Intent(LoginActivity.this,  FindJobActivity.class);
                            startActivity(intentApplicationsTrackerActivity);

                        } else if(response.code() == 404){
                            Toast.makeText(LoginActivity.this, "Wrong credentials", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        //shows the error
                        Toast.makeText(LoginActivity.this, t.getMessage() ,
                                Toast.LENGTH_LONG).show();
                    }
                });



            } break;

            case R.id.btnRegister: {

                Log.d("LOGIN", "Register button clicked");

                // goes to RegisterActivity
                Intent intentRegister = new Intent(this,  RegisterActivity.class);
                startActivity(intentRegister);
                finish(); //close LoginActivity because there's a button for navigating back to LoginActivity na

            } break;



        }

    }



}


