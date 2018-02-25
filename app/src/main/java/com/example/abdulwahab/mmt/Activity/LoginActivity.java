package com.example.abdulwahab.mmt.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.abdulwahab.mmt.Config;
import com.example.abdulwahab.mmt.R;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    //Defcomng views
    private EditText editTextEmail;
    private EditText editTextPassword;
    private AppCompatButton buttonLogin;
    ProgressDialog progressDialog;
    private boolean loggedIn = false;// ngecek uda login belum , nilai awal false
    TextView web;
    TextView pengembang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Initializing views
        editTextEmail = (EditText) findViewById(R.id.login_edUser);
        editTextPassword = (EditText) findViewById(R.id.login_edPass);
        buttonLogin = (AppCompatButton) findViewById(R.id.login_btnLogin);
        buttonLogin.setOnClickListener(this);
        web =(TextView)findViewById(R.id.web);
        web.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent in=new Intent(Intent.ACTION_VIEW,Uri.parse("http://192.168.43.212"));
                startActivity(in);
            }

        });
        pengembang =(TextView)findViewById(R.id.tentang);
        pengembang.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String[] TO = {"abdulwahab@student.polindra.ac.id"};
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, TO);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Kritik dan saran");
                intent.putExtra(Intent.EXTRA_TEXT, "Assalamualaikum...");

                startActivity(Intent.createChooser(intent, "Send Email"));

            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
        final SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);
        String datauser = sharedPreferences.getString(Config.EMAIL_SHARED_PREF, "");
        String bidan = sharedPreferences.getString(Config.BIDAN_SHARED_PREF, "");
        if (loggedIn) {
            //If we will get true
            if (datauser.equals("kepala")) {
                Intent intent = new Intent(LoginActivity.this, DasborKepala.class);
                startActivity(intent);
                finish();
            }
            else if (datauser.equals(bidan)) {
                Intent intent = new Intent(LoginActivity.this, DasborBidan.class);
                startActivity(intent);
                finish();
            }
            else if (datauser.equals("puskesmas")) {
                Intent intent = new Intent(LoginActivity.this, DasborKepala.class);
                startActivity(intent);
                finish();
            }

        }
    }
    private void login(){
        //Getting values from edit texts
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        //njaluk data sing php
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.LOGIN_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server
                        Toast.makeText(getBaseContext(),response.toString(),Toast.LENGTH_SHORT).show();
                         if(response.equalsIgnoreCase(Config.LOGIN_DINKES)){
                            //Creating a shared preference
                            SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                            //Creating editor to store values to shared preferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            //Adding values to editor[
                            editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                            editor.putString(Config.EMAIL_SHARED_PREF, email);

                            //Saving values to editor
                            editor.commit();



                            Intent intent = new Intent(LoginActivity.this, DasborKepala.class);
                            startActivity(intent);
                            finish();

                        }
                        else if(response.equalsIgnoreCase(editTextEmail.getText().toString())){
                            //Creating a shared preference
                            SharedPreferences sharedPreferences = LoginActivity.
                                    this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            //Adding values to editor

                            editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                            editor.putString(Config.BIDAN_SHARED_PREF, email);editor.putString(Config.NAMA_SHARED_PREF, editTextEmail.getText().toString());
                            editor.putString(Config.EMAIL_SHARED_PREF, email);


                            editor.commit();
                                Intent intent = new Intent(LoginActivity.this, DasborBidan.class);
                                startActivity(intent);
                                finish();

                        }


                        else{
                            //If the server response is not success
                            //Displaying an error message on toast
                            Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }){
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Config.KEY_EMAIL, email);
                params.put(Config.KEY_PASSWORD, password);

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    void progres() {
        if (progressDialog == null)

        {
            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setMessage("Harap Tunggu");
            progressDialog.show();
        }
    }
        @Override
    //kalo diteken login
    public void onClick(View v) {
            if(editTextEmail.length() == 0 || editTextPassword.length() == 0) {
                if(editTextEmail.length() == 0 && editTextPassword.length() == 0){
                    editTextEmail.setError("Masukkan Nama");
                    editTextPassword.setError("Masukkan Password");

                }
                else if (editTextEmail.length() == 0) {
                    editTextEmail.setError("Masukkan Nama");
                } else if (editTextPassword.length() == 0) {
                    editTextPassword.setError("Masukkan Password");
                }
            }
            else {
                progres();
                login();
            }

    }
}