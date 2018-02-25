package com.example.abdulwahab.mmt.Activity;

        import android.app.DatePickerDialog;
        import android.app.Dialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.SharedPreferences;
        import android.support.design.widget.Snackbar;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.Toolbar;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.DatePicker;
        import android.widget.Spinner;
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

        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.HashMap;
        import java.util.Map;

public class PeriksaIbuHamil extends AppCompatActivity {
    RequestQueue requestQueue; //buat crud
    String bidan;
    //nilai awal 10T TIDAK
    String BBTB="tidak",TENSI_DARAH="tidak" ,LILA="tidak", TINGGI_FUNDUS_UTERI="tidak", PRESENTASI="tidak",
            IMUNISASI="tidak", TABLET_FE="tidak", TEST_LAB="tidak", TEMU_WICARA="tidak", TATA_LAKSANA_KASUS ="tidak";
    //nilai awal komplikasi tidakv

    String umur20="tidak",umur35="tidak",jarakkehamilan="tidak",KEK="tidak",anemia="tidak",TB145="tidak",kelainanbentuk="tidak",hipertensi="tidak",penyakitkronis="tidak",
            kehamilanburuk="tidak",persalinankomplikasi="tidak",nifaskomplikasi="tidak",riwayatkeluarga="tidak",ketubanpecah="tidak",perdarahanpervaginam="tidak",
            HDK="tidak",ancamanprematur="tidak",infeksiberat="tidak",distosia="tidak",infekscomfas="tidak",
    kelainantumbuhjanin="tidak",kehamilanganda="tidak",kelainanjanin="tidak",kelainanletak="tidak";
String String;
    private TextView nama,suami,umur,gpa,dateView,persalinan,v10t,v10komp;
    private Spinner jaminan,desa;
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    Toolbar toolBar;
    StringBuilder s = new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periksa_ibu_hamil);
        bar();
        nama = (TextView) findViewById(R.id.namabumil);
        suami = (TextView) findViewById(R.id.namasuami);
        umur = (TextView) findViewById(R.id.umurbumil);
        desa = (Spinner) findViewById(R.id.spinnerpilihdesa);
        gpa = (TextView) findViewById(R.id.gpabumil);
        dateView = (TextView) findViewById(R.id.viewtanggal);
        persalinan = (TextView) findViewById(R.id.viewhp);
        jaminan = (Spinner) findViewById(R.id.spinnerjaminan);
        v10t = (TextView) findViewById(R.id.view10t);
        v10komp = (TextView) findViewById(R.id.viewkomplikasi);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);
        requestQueue = Volley.newRequestQueue(getApplicationContext());//buat crud
    }


    public void data() {
        SharedPreferences sharedPreferences =this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        final String namabides = sharedPreferences.getString(Config.NAMA_SHARED_PREF,"");
        StringRequest request = new StringRequest(Request.Method.POST, Config.server+"databumil.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(PeriksaIbuHamil.this, ""+response.toString(), Toast.LENGTH_SHORT).show();


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
                parameters.put("bidan", namabides.toString());
                parameters.put("nama", nama.getText().toString());//yang dipetik itu variabel external yang dikirim ke php
                parameters.put("suami", suami.getText().toString());
                parameters.put("umur", umur.getText().toString());
                parameters.put("desa", desa.getSelectedItem().toString());
                parameters.put("gpa", gpa.getText().toString());
                parameters.put("hpht", dateView.getText().toString());
                parameters.put("tp", persalinan.getText().toString());
                parameters.put("jaminan", jaminan.getSelectedItem().toString());
                //periksa 10T
                parameters.put("bbtb", BBTB.toString());
                parameters.put("tensi", TENSI_DARAH.toString());
                parameters.put("lila", LILA.toString());
                parameters.put("tfu", TINGGI_FUNDUS_UTERI.toString());
                parameters.put("presentasi", PRESENTASI.toString());
                parameters.put("imunisasi", IMUNISASI.toString());
                parameters.put("tablet", TABLET_FE.toString());
                parameters.put("testlab", TEST_LAB.toString());
                parameters.put("temuwicara", TEMU_WICARA.toString());
                parameters.put("tatalaksana", TATA_LAKSANA_KASUS.toString());
                //periksa komplikasi
                parameters.put("umur20",umur20.toString());
                parameters.put("umur35",umur35.toString());
                parameters.put("jarakkehamilan",jarakkehamilan.toString());
                parameters.put("KEK",KEK.toString());
                parameters.put("anemia",anemia.toString());
                parameters.put("TB145",TB145.toString());
                parameters.put("kelainanbentuk",kelainanbentuk.toString());
                parameters.put("hipertensi",hipertensi.toString());
                parameters.put("penyakitkronis",penyakitkronis.toString());
                parameters.put("kehamilanburuk",kehamilanburuk.toString());
                parameters.put("persalinankomplikasi",persalinankomplikasi.toString());
                parameters.put("nifaskomplikasi",nifaskomplikasi.toString());
                parameters.put("riwayatkeluarga",riwayatkeluarga.toString());
                parameters.put("ketubanpecah",ketubanpecah.toString());
                parameters.put("perdarahanpervaginam",perdarahanpervaginam.toString());
                parameters.put("HDK",HDK.toString());
                parameters.put("ancamanprematur",ancamanprematur.toString());
                parameters.put("infeksiberat",infeksiberat.toString());
                parameters.put("distosia",distosia.toString());
                parameters.put("infekscomfas",infekscomfas.toString());
                parameters.put("kelainantumbuhjanin",kelainantumbuhjanin.toString());
                parameters.put("kehamilanganda",kehamilanganda.toString());
                parameters.put("kelainanjanin",kelainanjanin.toString());
                parameters.put("kelainanletak",kelainanletak.toString());
                return parameters;
            }
        };
        requestQueue.add(request);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_oke, menu);//menu atas
        return true;

    }
    //untuk set tanggal
    @SuppressWarnings("deprecation")

    public void setDate(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2 + 1, arg3);
            setpersalinan(arg1, arg2 + 1, arg3);

        }
    };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("-")
                .append(month).append("-").append(year));
    }
    public void setpersalinan(int year, int month, int day) {

        if (month == 1 || month == 2 || month == 3) {
            int newday = day + 7;
            int newmonth = month + 9;
            if (month==1&&newday>31){
                int tgl=newday-31;
                int bln=newmonth+1;
                persalinan.setText(new StringBuilder().append(tgl).append("-")
                        .append(bln).append("-").append(year));
            }
            else if(month==2&&newday>28){
                int tgl=newday-28;
                int bln=newmonth+1;
                persalinan.setText(new StringBuilder().append(tgl).append("-")
                        .append(bln).append("-").append(year));
            }
            else if(month==3&&newday>31) {
                int tgl = newday - 31;
                int bln = 1;
                int thn= year  + 1;
                persalinan.setText(new StringBuilder().append(tgl).append("-")
                        .append(bln).append("-").append(thn));
            }
            else if(newday<=31){
                int tgl=newday;
                int bln=newmonth;
                persalinan.setText(new StringBuilder().append(tgl).append("-")
                        .append(bln).append("-").append(year));
            }

        }
        else {
            int newday = day + 7;
            int newmonth = month - 3;
            int newyear = year + 1;
            if(newday<=30) {
                int tgl = newday;
                int bln = newmonth;
                persalinan.setText(new StringBuilder().append(tgl).append("-")
                        .append(bln).append("-").append(newyear));
            }
           else if (month==5||month==7||month==8||month==10||month==12
                    && newday>32){
                int tgl=newday-30;
                int bln=newmonth+1;
                persalinan.setText(new StringBuilder().append(tgl).append("-")
                        .append(bln).append("-").append(newyear));
            }
            else if(month==4||month==6||month==9||month==11
                    && newday>31){
                int tgl=newday-30;
                int bln=newmonth+1;
                persalinan.setText(new StringBuilder().append(tgl).append("-")
                        .append(bln).append("-").append(newyear));


            }

        }

    }

    //ontouch 10 T
    public void cek10t(View v) {
        tidak10t();
        android.app.AlertDialog dialog;
        final String[] items = {"BB/TB", "TENSI DARAH","LILA", "TINGGI FUNDUS UTERI", "PRESENTASI",
                "IMUNISASI", "TABLET FE", "TEST LAB", "TEMU WICARA", "TATA LAKSANA KASUS"};
        final ArrayList seletedItems = new ArrayList();
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(PeriksaIbuHamil.this);
        builder.setTitle("Ceklis Jika Diperiksa");
        builder.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
            public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                if (isChecked) {
                    if (items[indexSelected].equals("BB/TB")) {BBTB = "periksa";}
                    if (items[indexSelected].equals("TENSI DARAH")) {TENSI_DARAH = "periksa";}
                    if (items[indexSelected].equals("LILA")) {LILA = "periksa";}
                    if (items[indexSelected].equals("TINGGI FUNDUS UTERI")) {TINGGI_FUNDUS_UTERI = "periksa";}
                    if (items[indexSelected].equals("PRESENTASI")) {PRESENTASI = "periksa";}
                    if (items[indexSelected].equals("IMUNISASI")) {IMUNISASI = "periksa";}
                    if (items[indexSelected].equals("TABLET FE")) {TABLET_FE = "periksa";}
                    if (items[indexSelected].equals("TEST LAB")) {TEST_LAB = "periksa";}
                    if (items[indexSelected].equals("TEMU WICARA")) {TEMU_WICARA = "periksa";}
                    if (items[indexSelected].equals("TATA LAKSANA KASUS")) {TATA_LAKSANA_KASUS = "periksa";}
                }
                else if (seletedItems.contains(indexSelected)) {
                    // Else, if the item is already in the array, remove it
                    // write your code when user
                    // Uchecked the checkbox
                    // seletedItems.remove(items[indexSelected]);//kalo data mau integer
                    seletedItems.remove(Integer.valueOf(indexSelected));
                }
            }
        })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                            for (int x = 0; x < seletedItems.size(); x++) {
                                s.append(seletedItems.get(x).toString() + "  ");
                            }
                        v10t.setText(s.toString());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                tidak10t();
                v10t.setText("");
                    }
                });

        dialog = builder.create();
        dialog.show();
    }

    //ontouch komplikasi
    public void komplikasi(View v) {
        tidakkomplikasi();
        android.app.AlertDialog dialog;
        final CharSequence[] items = {"Umur ibu < 20", "Umur ibu >=35", "Jarak kehamilan < 2 tahun", "KEK", "Anemia Hb < 11g/dl",
                "TB < 145 Cm", "Kelainan bentuk panggul", "Riwayat hipertensi", "Sedang/pernah penyakit kronis", "Riwayat kehamilan buruk",
                "Riwayat persalinan dengan komoplikasi", "Riwayat nifas komplikasi", "Penyakit riwayat keluarga", "Ketuban pecah dcom", "Perdarahan pervaginam",
                "HDK", "Ancaman prematur", "Infeksi berat dalam hamil", "Distosia", "Infeksi masa nifas", "Kelainan tumbuh janin", "Kehamilan ganda",
                "Kelainan besar janin", "Kelainan letak"};
        final ArrayList seletedItems = new ArrayList();
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(PeriksaIbuHamil.this);
        builder.setTitle("Ceklis Resiko & komplikasi");
        builder.setMultiChoiceItems(items, null,
                new DialogInterface.OnMultiChoiceClickListener() {
                    // indexSelected contains the index of item (of which checkbox checked)
                    @Override
                    public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {

                        if (isChecked) {
                            if (items[indexSelected].equals("Umur ibu < 20")) {	umur20	 = "ya";}
                            if (items[indexSelected].equals("Umur ibu >=35")) {	umur35	 = "ya";}
                            if (items[indexSelected].equals("Jarak kehamilan < 2 tahun")) {	jarakkehamilan	 = "ya";}
                            if (items[indexSelected].equals("KEK")) {	KEK	 = "ya";}
                            if (items[indexSelected].equals("Anemia Hb < 11g/dl")) {	anemia	 = "ya";}
                            if (items[indexSelected].equals("TB < 145 Cm")) {	TB145	 = "ya";}
                            if (items[indexSelected].equals("Kelainan bentuk panggul")) {	kelainanbentuk	 = "ya";}
                            if (items[indexSelected].equals("Riwayat hipertensi")) {	hipertensi	 = "ya";}
                            if (items[indexSelected].equals("Sedang/pernah penyakit kronis")) {	penyakitkronis	 = "ya";}
                            if (items[indexSelected].equals("Riwayat kehamilan buruk")) {	kehamilanburuk	 = "ya";}
                            if (items[indexSelected].equals("Riwayat persalinan dengan komoplikasi")) {	persalinankomplikasi	 = "ya";}
                            if (items[indexSelected].equals("Riwayat nifas komplikasi")) {	nifaskomplikasi	 = "ya";}
                            if (items[indexSelected].equals("Penyakit riwayat keluarga")) {	riwayatkeluarga	 = "ya";}
                            if (items[indexSelected].equals("Ketuban pecah dcom")) {	ketubanpecah	 = "ya";}
                            if (items[indexSelected].equals("Perdarahan pervaginam")) {	perdarahanpervaginam	 = "ya";}
                            if (items[indexSelected].equals("HDK")) {	HDK	 = "ya";}
                            if (items[indexSelected].equals("Ancaman prematur")) {	ancamanprematur	 = "ya";}
                            if (items[indexSelected].equals("Infeksi berat dalam hamil")) {	infeksiberat	 = "ya";}
                            if (items[indexSelected].equals("Distosia")) {	distosia	 = "ya";}
                            if (items[indexSelected].equals("Infeksi masa nifas")) {	infekscomfas	 = "ya";}
                            if (items[indexSelected].equals("Kelainan tumbuh janin")) {	kelainantumbuhjanin	 = "ya";}
                            if (items[indexSelected].equals("Kehamilan ganda")) {	kehamilanganda	 = "ya";}
                            if (items[indexSelected].equals("Kelainan besar janin")) {	kelainanjanin	 = "ya";}
                            if (items[indexSelected].equals("Kelainan letak")) {	kelainanletak	 = "ya";}

                        } else if (seletedItems.contains(indexSelected)) {

                        }
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //  Your code when user clicked on OK
                        //  You can write the code  to save the selected item here

                        for (int x = 0; x < seletedItems.size(); x++) {
                            s.append(seletedItems.get(x).toString() + "  ");
                        }
                        v10komp.setText(s.toString());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    tidakkomplikasi();
                    v10komp.setText("");
                    }
                });

        dialog = builder.create();
        dialog.show();
    }

    private void bar() {
        toolBar = (Toolbar) findViewById(R.id.toolbarok);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuOk:
                if (nama.length() == 0) {
                    nama.setError("Nama tidak boleh kosong");
                }
               else if (suami.length() == 0) {
                    suami.setError("Masukkan nama suami");
                }
              else  if (umur.length() == 0) {
                    umur.setError("Umur tidak boleh kosong");
                }
              else  if (gpa.length() == 0) {
                    gpa.setError("gpa tidak boleh kosong");
                }
               else if (jaminan.getSelectedItem().toString().equalsIgnoreCase("Pilih Jaminan")) {
                    Snackbar.make(jaminan, "Silahkan Pilih Jaminan", Snackbar.LENGTH_LONG).show();

                }
                else if (desa.getSelectedItem().toString().equalsIgnoreCase("Pilih Desa")) {
                    Snackbar.make(desa, "Silahkan Pilih Desa", Snackbar.LENGTH_LONG).show();

                }
               else if(nama.length() == 0 && suami.length() == 0&&umur.length() == 0&&gpa.length() == 0){
                    nama.setError("Nama tidak boleh kosong");
                    suami.setError("Masukkan nama suami");
                    umur.setError("UMUR tidak boleh kosong");
                    gpa.setError("gpa tidak boleh kosong");
                }
                else {
                    Toast.makeText(this, "Menyimpan....", Toast.LENGTH_SHORT).show();

                    data();

                    finish();
                }
                    break;

                case android.R.id.home:
                    Toast.makeText(this, "Kembali", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
            }
                return super.onOptionsItemSelected(item);
        }

    private void   tidak10t(){
        BBTB="tidak";TENSI_DARAH="tidak";LILA="tidak" ;TINGGI_FUNDUS_UTERI="tidak"; PRESENTASI="tidak";
        IMUNISASI="tidak"; TABLET_FE="tidak"; TEST_LAB="tidak"; TEMU_WICARA="tidak";
        TATA_LAKSANA_KASUS ="tidak";}

private void tidakkomplikasi(){
    umur20="tidak";umur35="tidak";jarakkehamilan="tidak";KEK="tidak";anemia="tidak";TB145="tidak";kelainanbentuk="tidak";hipertensi="tidak";penyakitkronis="tidak";
    kehamilanburuk="tidak";persalinankomplikasi="tidak";nifaskomplikasi="tidak";riwayatkeluarga="tidak";ketubanpecah="tidak";perdarahanpervaginam="tidak";
    HDK="tidak";ancamanprematur="tidak";infeksiberat="tidak";distosia="tidak";infekscomfas="tidak";
    kelainantumbuhjanin="tidak";kehamilanganda="tidak";kelainanjanin="tidak";kelainanletak="tidak";
}
}