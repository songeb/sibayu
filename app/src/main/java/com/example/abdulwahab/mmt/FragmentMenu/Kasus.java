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

public class Kasus extends android.support.v4.app.Fragment implements View.OnClickListener {
    TextView result;
    Button lihat;

    // untuk mengaktifkan RequestQueue tambahkan
    // compile 'com.mcxiaoke.volley:library:1.0.18' pada build.gradle(Module:app) nya
    RequestQueue requestQueue;
    String respon;
    TextView ibukurang20,ibulebihsama35,hamilkurang2tahun,kek1,anemia1,tbkurang145,lainpanggul,hipertensi1,kronis1,hamilburuk
            ,persalinankomplikasi,nifaskomplikasi,penyakitkeluarga,ketuban1,pendarahanpervagina,hdk1,ancamprematur,infeksiberat
            ,distosia1,infekscomfas,kelainanjanin, hamilganda,kelainanbesarjanin,kelainanletak,persalinan,sebab,jumlahibu;
Spinner spinerdatakasus;
    public Kasus() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_kasus, container, false);
        rootView.setTag(TAG);
        lihat =(Button) rootView.findViewById(R.id.lihat);
        lihat.setOnClickListener(this);
        jumlahibu =(TextView) rootView.findViewById(R.id.jml_ibu_hamil);
        ibukurang20	= (TextView) rootView.findViewById(R.id.text_k1);
        ibulebihsama35	= (TextView) rootView.findViewById(R.id.text_k2);
        hamilkurang2tahun	= (TextView) rootView.findViewById(R.id.text_k3);
        kek1	= (TextView) rootView.findViewById(R.id.text_k4);
        anemia1	= (TextView) rootView.findViewById(R.id.text_k5);
        tbkurang145	= (TextView) rootView.findViewById(R.id.text_k6);
        lainpanggul	= (TextView) rootView.findViewById(R.id.textk7);
        hipertensi1	= (TextView) rootView.findViewById(R.id.text_k8);
        kronis1	= (TextView) rootView.findViewById(R.id.textk9);
        hamilburuk	= (TextView) rootView.findViewById(R.id.text_k10);
        persalinankomplikasi	= (TextView) rootView.findViewById(R.id.text_k11);
        nifaskomplikasi	= (TextView) rootView.findViewById(R.id.text_k12);
        penyakitkeluarga	= (TextView) rootView.findViewById(R.id.text_k13);
        ketuban1	= (TextView) rootView.findViewById(R.id.text_k14);
        pendarahanpervagina	= (TextView) rootView.findViewById(R.id.text_k15);
        hdk1	= (TextView) rootView.findViewById(R.id.text_k16);
        ancamprematur	= (TextView) rootView.findViewById(R.id.text_k17);
        infeksiberat	= (TextView) rootView.findViewById(R.id.text_k18);
        distosia1	= (TextView) rootView.findViewById(R.id.text_k19);
        infekscomfas	= (TextView) rootView.findViewById(R.id.text_k20);
        kelainanjanin	= (TextView) rootView.findViewById(R.id.text_k21);
        hamilganda	= (TextView) rootView.findViewById(R.id.text_k22);
        kelainanbesarjanin	= (TextView) rootView.findViewById(R.id.text_k23);
        kelainanletak	= (TextView) rootView.findViewById(R.id.text_k24);
      //  persalinan	= (TextView) rootView.findViewById(R.id.tambah);
      //  sebab	= (TextView) rootView.findViewById(R.id.tambah);
        requestQueue = Volley.newRequestQueue(getContext());
        spinerdatakasus =(Spinner) rootView.findViewById(R.id.spinerdatakasus);
getData();
        return rootView;
    }
    private void getData(){

        //njaluk data sing php
        StringRequest ibu = new StringRequest(Request.Method.POST, Config.server+"grafikresikokepala.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

               respon = response.toString();//respon masih string 6_5_6_6_6_6_3_5_4_3_3_3_3_3_1_3_3_3_3_3_3_3_3_3
                String teks = respon ;
                String[] tempPesanMasuk = teks.split("_");
                ArrayList<String> teks_lengkap= new ArrayList<String>();

                for(int i=0; i < tempPesanMasuk.length ; i++) {//metode buat misah
                    teks_lengkap.add(tempPesanMasuk[i].trim());
                }
                jumlahibu.setText("  "+tempPesanMasuk[0]);//masukkan data berdasarkan string arrai
                ibukurang20	.setText("  "+tempPesanMasuk[1]);
                ibulebihsama35	.setText("  "+tempPesanMasuk[2]);
                hamilkurang2tahun	.setText("  "+tempPesanMasuk[3]);
                kek1	.setText("  "+tempPesanMasuk[4]);
                anemia1	.setText("  "+tempPesanMasuk[5]);
                tbkurang145	.setText("  "+tempPesanMasuk[6]);
                lainpanggul	.setText("  "+tempPesanMasuk[7]);
                hipertensi1	.setText("  "+tempPesanMasuk[8]);
                kronis1	.setText("  "+tempPesanMasuk[9]);
                hamilburuk	.setText("  "+tempPesanMasuk[10]);
                persalinankomplikasi	.setText("  "+tempPesanMasuk[11]);
                nifaskomplikasi	.setText("  "+tempPesanMasuk[12]);
                penyakitkeluarga	.setText("  "+tempPesanMasuk[13]);
                ketuban1	.setText("  "+tempPesanMasuk[14]);
                pendarahanpervagina	.setText("  "+tempPesanMasuk[15]);
                hdk1	.setText("  "+tempPesanMasuk[16]);
                ancamprematur	.setText("  "+tempPesanMasuk[17]);
                infeksiberat	.setText("  "+tempPesanMasuk[18]);
                distosia1	.setText("  "+tempPesanMasuk[19]);
                infekscomfas	.setText("  "+tempPesanMasuk[20]);
                kelainanjanin	.setText("  "+tempPesanMasuk[21]);
                hamilganda	.setText("  "+tempPesanMasuk[22]);
                kelainanbesarjanin	.setText("  "+tempPesanMasuk[23]);
                kelainanletak	.setText("  "+tempPesanMasuk[24]);

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
                params.put("desa", spinerdatakasus.getSelectedItem().toString());
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
        if (spinerdatakasus.getSelectedItem().toString().equalsIgnoreCase("Pilih Desa")) {
            Snackbar.make(spinerdatakasus, "Silahkan Pilih Desa", Snackbar.LENGTH_LONG).show();

        }
        else {
            Toast.makeText(getContext(), "Data Desa " + spinerdatakasus.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            getData();
        }
    }
}
