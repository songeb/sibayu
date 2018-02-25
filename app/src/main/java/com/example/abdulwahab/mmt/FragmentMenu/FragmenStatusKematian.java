package com.example.abdulwahab.mmt.FragmentMenu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import static android.content.ContentValues.TAG;

public class FragmenStatusKematian extends Fragment implements View.OnClickListener{
Button simpan;
    EditText nama,suami,sebab;
    Spinner jeniskematian;


    public FragmenStatusKematian() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.status_kelahiran,container,false);
        rootView.setTag(TAG);
        simpan =(Button) rootView.findViewById(R.id.simmpandata);
        simpan.setOnClickListener(this);
        nama = (EditText)rootView.findViewById(R.id.namabumil);
        suami = (EditText)rootView.findViewById(R.id.namasuamibumil);
        sebab = (EditText)rootView.findViewById(R.id.sebab);
        jeniskematian = (Spinner)rootView.findViewById(R.id.spinercekkematian);

        return rootView;
    }
    private void data()
    {
        SharedPreferences sharedPreferences =getContext().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        final String namabides = sharedPreferences.getString(Config.NAMA_SHARED_PREF,"");
        StringRequest request = new StringRequest(Request.Method.POST, Config.server+"inputkelahiran.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(), ""+response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            //ngirim dari dan ke php. fild pertama untukdikirim, kedua objek internal android
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("nama", nama.getText().toString());
                parameters.put("suami", suami.getText().toString());//yang dipetik itu variabel external yang dikirim ke php
                parameters.put("jenis", jeniskematian.getSelectedItem().toString());
                parameters.put("bidan", namabides.toString());
                parameters.put("sebab", sebab.getText().toString());
                return parameters;
            }
        };
        request.setShouldCache(false);// no caching url...
        request.setRetryPolicy(
                new DefaultRetryPolicy(
                        20000,//time to wait for it in this case 20s
                        20,//tryies in case of error
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.simmpandata:
                if (nama.length() == 0) {
                nama.setError("Ketik Nama Ibu Hamil");
            }
            else  if (suami.length() == 0) {
                suami.setError("Ketik Nama Suami");
            }
            else  if (sebab.length() == 0) {
                    sebab.setError("masukkan sebab");
                }
            else if (jeniskematian.getSelectedItem().toString().equalsIgnoreCase("Siapa Yang Meninggal?")) {
                Snackbar.make(jeniskematian, "Pilih Siapa Yang Meninggal", Snackbar.LENGTH_LONG).show();

            }
                else {
                    Toast.makeText(getContext(), "Menyimpan....", Toast.LENGTH_SHORT).show();
                    data();
                    break;
                }
        }
    }
}
