package com.example.abdulwahab.mmt.FragmentMenu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class BarKematian extends Fragment {
    TextView ibu,bayi;
    BarChart chart;
    String respon;
    String des1,des2,des3,des4,des5;
   int a1;
    int a2;
    int b1;
    int b2;
    int c1;
    int c2;
    int d1;
    int d2;
    int e1;
    int e2;

    public BarKematian() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_kasus_kematian,container,false);//layout fragmen harus menggunakan inflater
        rootView.setTag(TAG);
        getbar();
        getdata();
        ibu = (TextView) rootView.findViewById(R.id.kematianibu);
        bayi = (TextView) rootView.findViewById(R.id.kematiananak);
        chart = (BarChart) rootView.findViewById(R.id.grafikkematian);
        com.github.mikephil.charting.data.BarData data = new com.github.mikephil.charting.data.BarData(getXAxisValues(), getDataSet());
        chart.setData(data);
        chart.animateXY(1000,1000);
        chart.invalidate();
        return rootView;

    }


    private ArrayList<IBarDataSet> getDataSet() {

        ArrayList<IBarDataSet> dataSets = null;
        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(0+a1, 0);
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(0+b1, 1);
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(0+c1, 2);
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(0+d1, 3);
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(0+e1, 4);
        valueSet1.add(v1e5);
        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v2e1 = new BarEntry(0+a2, 0);
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(0+b2, 1);
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(0+c2, 2);
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(0+d2, 3);
        valueSet2.add(v2e4);
        BarEntry v2e5 = new BarEntry(0+e2, 4);
        valueSet2.add(v2e5);
        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Kematian Ibu");
        barDataSet1.setColor(Color.rgb(255, 20, 147));
        barDataSet1.setValueTextSize(8);
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Kematian Bayi");
        //barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet2.setColor(Color.rgb(255, 105, 180));
        barDataSet2.setValueTextSize(8);
        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        return dataSets;
    }
    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add(""+des1);
        xAxis.add(""+des2);
        xAxis.add(""+des3);
        xAxis.add(""+des4);
        xAxis.add(""+des5);
        return xAxis;
    }
        private void getbar()
    {

        //njaluk data sing php
        final StringRequest ibu20 = new StringRequest(Request.Method.POST, Config.server+"kematian.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                respon = response.toString();//respon masih string 6_5_6_6_6_6_3_5_4_3_3_3_3_3_1_3_3_3_3_3_3_3_3_3
                String teks = respon ;
                String[]tempPesanMasuk = teks.split("_");
                ArrayList<String> teks_lengkap= new ArrayList<String>();
if(tempPesanMasuk.length<15){}
                else {
    for (int i = 0; i < tempPesanMasuk.length; i++) {//metode buat misah
        teks_lengkap.add(tempPesanMasuk[i].trim());
    }
    des1 = tempPesanMasuk[0];
    a1 = Integer.valueOf(tempPesanMasuk[1]);
    a2 = Integer.valueOf(tempPesanMasuk[2]);
    des2 = tempPesanMasuk[3];
    b1 = Integer.valueOf(tempPesanMasuk[4]);
    b2 = Integer.valueOf(tempPesanMasuk[5]);
    des3 = tempPesanMasuk[6];
    c1 = Integer.valueOf(tempPesanMasuk[7]);
    c2 = Integer.valueOf(tempPesanMasuk[8]);
    des4 = tempPesanMasuk[9];
    d1 = Integer.valueOf(tempPesanMasuk[10]);
    d2 = Integer.valueOf(tempPesanMasuk[11]);
    des5 = tempPesanMasuk[12];
    e1 = Integer.valueOf(tempPesanMasuk[13]);
    e2 = Integer.valueOf(tempPesanMasuk[14]);
}
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
        ibu20.setShouldCache(false);// no caching url...
        ibu20.setRetryPolicy(
                new DefaultRetryPolicy(
                        20000,//time to wait for it in this case 20s
                        20,//tryies in case of error
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(ibu20);

    }



    private void getdata()
    {

        //njaluk data sing php
        final StringRequest ib = new StringRequest(Request.Method.POST, Config.server+"totalmeninggal.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                respon = response.toString();//respon masih string 6_5_6_6_6_6_3_5_4_3_3_3_3_3_1_3_3_3_3_3_3_3_3_3
                String teks = respon ;
                String[] tempPesanMasuk = teks.split("_");
                ArrayList<String> teks_lengkap= new ArrayList<String>();

                for(int i=0; i < tempPesanMasuk.length ; i++) {//metode buat misah
                    teks_lengkap.add(tempPesanMasuk[i].trim());
                }
                ibu.setText("  "+tempPesanMasuk[0]+" ibu");//masukkan data berdasarkan string arrai
                bayi	.setText("  "+tempPesanMasuk[1]+" bayi");


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

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(ib);

    }


}
