package com.example.abdulwahab.mmt.FragmentMenu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
import com.example.abdulwahab.mmt.Activity.PeriksaIbuHamil;
import com.example.abdulwahab.mmt.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class FragmenDataIbuHamil extends Fragment implements View.OnClickListener {
    TextView jumlah,yangditangani;
    String respon;
    ImageButton periksa;
    //    private String url = "http://192.168.43.211/showEvent.php";
    public FragmenDataIbuHamil() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dataibuhamil,container,false);
        rootView.setTag(TAG);
        jumlah =(TextView) rootView.findViewById(R.id.jml_ibu_hamil);
        yangditangani =(TextView) rootView.findViewById(R.id.jml_yang_ditangani);
        periksa = (ImageButton) rootView.findViewById(R.id.tambah);
        periksa.setOnClickListener(this);
        getdata();
        return rootView;
    }

private void getdata(){
    SharedPreferences sharedPreferences =getContext().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
    final String namabides = sharedPreferences.getString(Config.NAMA_SHARED_PREF,"");
    //njaluk data sing php
    StringRequest ibu = new StringRequest(Request.Method.POST, Config.server+"yangditanganibidan.php", new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            respon = response.toString();//respon masih string 6_5_6_6_6_6_3_5_4_3_3_3_3_3_1_3_3_3_3_3_3_3_3_3
            String teks = respon ;
            String[] tempPesanMasuk = teks.split("_");
            ArrayList<String> teks_lengkap= new ArrayList<String>();

            for(int i=0; i < tempPesanMasuk.length ; i++) {//metode buat misah
                teks_lengkap.add(tempPesanMasuk[i].trim());
            }
            jumlah.setText("  "+tempPesanMasuk[0]);//masukkan data berdasarkan string arrai
            yangditangani	.setText("  "+tempPesanMasuk[1]);

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
            params.put("bidan", namabides.toString());
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
    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tambah:
                Intent i = (new Intent(getContext(), PeriksaIbuHamil.class));
                startActivity(i);
                Toast.makeText(getContext(),"Menambahkan Data ibu hamil", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}


