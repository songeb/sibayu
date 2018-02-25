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

public class IbuHamil extends android.support.v4.app.Fragment implements View.OnClickListener {
    TextView ibuhamil,ibuberesiko,anc,total,bbtb,tensi,lila,tfu,presentasi,imunisasi,tablet,testlab,temwi,talaksus;
    Spinner spinerdesa;
    String respon;
    Button tampil;
    public IbuHamil() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_ibu_hamil, container, false);
        rootView.setTag(TAG);
        ibuhamil =(TextView) rootView.findViewById(R.id.jml_ibu_hamil);
        ibuberesiko =(TextView) rootView.findViewById(R.id.jmlberesiko);
        anc =(TextView) rootView.findViewById(R.id.ancberkualitas);
        total =(TextView) rootView.findViewById(R.id.total);
        bbtb =(TextView) rootView.findViewById(R.id.bbtb);
        tensi =(TextView) rootView.findViewById(R.id.tensi);
        lila =(TextView) rootView.findViewById(R.id.lila);
        tfu =(TextView) rootView.findViewById(R.id.tfu);
        presentasi =(TextView) rootView.findViewById(R.id.presentasi);
        imunisasi =(TextView) rootView.findViewById(R.id.imunisasi);
        tablet=(TextView) rootView.findViewById(R.id.tablet);
        testlab =(TextView) rootView.findViewById(R.id.teslab);
        temwi =(TextView) rootView.findViewById(R.id.temwi);
        talaksus =(TextView) rootView.findViewById(R.id.talaksus);
        spinerdesa = (Spinner) rootView.findViewById((R.id.spinerdata));
        tampil =(Button) rootView.findViewById(R.id.tampil);
        tampil.setOnClickListener(this);
        return rootView;
    }

    private void getdata (){

        //njaluk data sing php
        StringRequest ibu = new StringRequest(Request.Method.POST, Config.server+"ancdasborkepala.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                respon = response.toString();//respon masih string 6_5_6_6_6_6_3_5_4_3_3_3_3_3_1_3_3_3_3_3_3_3_3_3
                String teks = respon ;
                String[] tempPesanMasuk = teks.split("_");
                ArrayList<String> teks_lengkap= new ArrayList<String>();

                for(int i=0; i < tempPesanMasuk.length ; i++) {//metode buat misah
                    teks_lengkap.add(tempPesanMasuk[i].trim());
                }
                ibuhamil.setText("  "+tempPesanMasuk[0]);//masukkan data berdasarkan string arrai
                ibuberesiko	.setText("  "+tempPesanMasuk[1]);
                anc	.setText("  "+tempPesanMasuk[2]);
                total.setText("  "+tempPesanMasuk[3]);
                bbtb.setText("  "+tempPesanMasuk[4]);
                tensi.setText("  "+tempPesanMasuk[5]);
                lila.setText("  "+tempPesanMasuk[6]);
                tfu.setText("  "+tempPesanMasuk[7]);
                presentasi.setText("  "+tempPesanMasuk[8]);
                imunisasi.setText("  "+tempPesanMasuk[9]);
                tablet.setText("  "+tempPesanMasuk[10]);
                testlab.setText("  "+tempPesanMasuk[11]);
                temwi.setText("  "+tempPesanMasuk[12]);
                talaksus.setText("  "+tempPesanMasuk[13]);

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


    @Override
    public void onClick(View v) {
        if (spinerdesa.getSelectedItem().toString().equalsIgnoreCase("Pilih Desa")) {
            Snackbar.make(spinerdesa, "Silahkan pilih desa", Snackbar.LENGTH_LONG).show();

        }
        else {
            Toast.makeText(getContext(), "Data Desa " + spinerdesa.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            getdata();
        }
    }
}
