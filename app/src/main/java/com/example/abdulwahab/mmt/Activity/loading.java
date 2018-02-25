package com.example.abdulwahab.mmt.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
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

public class loading extends AppCompatActivity {

    private static int splashInterval = 1500;//set delay

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pesan();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_loading);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent i = new Intent(loading.this, MenuAwal.class);
                startActivity(i);
                finish();
            }
        }, splashInterval);
    }
    private void pesan(){

        //njaluk data sing php
        final StringRequest ib = new StringRequest(Request.Method.POST, Config.server+"pesan.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(loading.this,""+response.toString(), Toast.LENGTH_SHORT).show();

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                return params;
            }
        };
        ib.setShouldCache(false);// no caching url...
        ib.setRetryPolicy(
                new DefaultRetryPolicy(
                        20000,//time to wait for it in this case 20s
                        20,//tryies in case of error
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );

        ib.setShouldCache(false);// no caching url...
        ib.setRetryPolicy(
                new DefaultRetryPolicy(
                        20000,//time to wait for it in this case 20s
                        20,//tryies in case of error
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(ib);

    }

}
