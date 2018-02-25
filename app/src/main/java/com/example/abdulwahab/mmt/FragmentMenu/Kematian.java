package com.example.abdulwahab.mmt.FragmentMenu;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class Kematian extends android.support.v4.app.Fragment implements View.OnClickListener {
TextView ibumati,bayimati,totalbayimati,totalibumati,ibu,bayi;
    Spinner spinerdesa;
    String respon;
    Button tampil;
    RequestQueue requestQueue;

    public Kematian() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_kematian, container, false);
        rootView.setTag(TAG);
        totalibumati = (TextView) rootView.findViewById(R.id.kematianibu);
        ibumati = (TextView) rootView.findViewById(R.id.kematianibudesa);
        totalbayimati = (TextView) rootView.findViewById(R.id.kematiananak);
        bayimati = (TextView) rootView.findViewById(R.id.kematianbayidesa);
        ibu = (TextView) rootView.findViewById(R.id.ibu);
        bayi = (TextView) rootView.findViewById(R.id.bayii);
        tampil = (Button) rootView.findViewById(R.id.tampil);
        tampil.setOnClickListener(this);
        requestQueue = Volley.newRequestQueue(getContext());
        spinerdesa =(Spinner) rootView.findViewById(R.id.spinercekkematian);
        getdatatotal();
        return rootView;
    }
    private void getdatadesa()
    {

        //njaluk data sing php
        StringRequest ibu = new StringRequest(Request.Method.POST, Config.server+"desameninggal.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                respon = response.toString();//respon masih string 6_5_6_6_6_6_3_5_4_3_3_3_3_3_1_3_3_3_3_3_3_3_3_3
                String teks = respon ;
                String[] tempPesanMasuk = teks.split("_");
                ArrayList <String> teks_lengkap= new ArrayList<String>();

                for(int i=0; i < tempPesanMasuk.length ; i++) {//metode buat misah
                    teks_lengkap.add(tempPesanMasuk[i].trim());
                }
              ibumati.setText("  "+tempPesanMasuk[0]);
              bayimati.setText("  "+tempPesanMasuk[1]);

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
                //Adding parameters to request
                params.put("desa", spinerdesa.getSelectedItem().toString());
                //returning parameter
                return params;
            }
        };

        ibu.setShouldCache(false);// no caching url...
        ibu.setRetryPolicy(
                new DefaultRetryPolicy(
                        20000,//time to wait for it in this case 20s
                        20,//tryies in case of error
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(ibu);

    }

    private void getdatatotal (){

        //njaluk data sing php
        StringRequest ibu = new StringRequest(Request.Method.POST, Config.server+"totalmeninggal.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                respon = response.toString();//respon masih string 6_5_6_6_6_6_3_5_4_3_3_3_3_3_1_3_3_3_3_3_3_3_3_3
                String teks = respon ;
                String[] tempPesanMasuk = teks.split("_");
                ArrayList<String> teks_lengkap= new ArrayList<String>();

                for(int i=0; i < tempPesanMasuk.length ; i++) {//metode buat misah
                    teks_lengkap.add(tempPesanMasuk[i].trim());
                }
                totalibumati.setText("  "+tempPesanMasuk[0]+" Ibu");
                totalbayimati.setText("  "+tempPesanMasuk[1]+" Bayi");

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


        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(ibu);

    }

    @Override
    public void onClick(View v) {
        if (spinerdesa.getSelectedItem().toString().equalsIgnoreCase("Pilih Desa")) {
            Snackbar.make(spinerdesa, "Silahkan Pilih Desa", Snackbar.LENGTH_LONG).show();

        }
        else {
            ibu.setText("Desa "+spinerdesa.getSelectedItem().toString()+" meninggal");
            bayi.setText("Desa "+spinerdesa.getSelectedItem().toString()+" meninggal");
            Toast.makeText(getContext(), "Data Desa " + spinerdesa.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            getdatadesa();
        }
    }
}
