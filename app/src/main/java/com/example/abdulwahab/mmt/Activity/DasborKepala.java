package com.example.abdulwahab.mmt.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.example.abdulwahab.mmt.Config;
import com.example.abdulwahab.mmt.R;
import com.example.abdulwahab.mmt.Tab.SlidingTabLayout;
import com.example.abdulwahab.mmt.Tab.TabsKepala;

public class DasborKepala extends AppCompatActivity {
    String name;
    Toolbar toolbar;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dasbor_kepala);
        toolBar();
        slidingTabViewPager();
    }
    private void slidingTabViewPager() {
        viewPager = (ViewPager) findViewById(R.id.menu_vpTab);
        viewPager.setAdapter(new TabsKepala(getSupportFragmentManager(), getApplicationContext()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        toolbar.setTitle(" Ibu Hamil");
                        break;
                    case 1:
                        toolbar.setTitle(" Kematian Ibu Dan Anak");//mati atau hidup
                        break;
                    case 2:
                        toolbar.setTitle(" Kasus Ibu Hamil");//data resiko
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.menu_stLayout);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setBackgroundColor(getResources().getColor(R.color.bgKedua));
        slidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.putih));
        slidingTabLayout.setCustomTabView(R.layout.tab_view, R.id.tab_tv_tab);
        slidingTabLayout.setViewPager(viewPager);
    }
    @Override//menu atas pojok
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_kepala, menu);
        menu.findItem(R.id.btn_login).setTitle(name);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.keluar: //logout kalo milih keluar
               logout();
                break;
            case R.id.depan: //logout kalo milih keluar
                Intent intent = new Intent(getBaseContext(), MenuAwal.class);
                startActivity(intent);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    private void logout(){//untuk logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);
                editor.putString(Config.EMAIL_SHARED_PREF, "");//email dikosongkan lagi
                editor.putString(Config.USER_SHARED_PREF, "");//email dikosongkan lagi
                editor.commit();

                Intent intent = new Intent(DasborKepala.this, MenuAwal.class);
                startActivity(intent);
                finish();
            }
        });
        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    private void toolBar() {
        toolbar = (Toolbar) findViewById(R.id.menu_toolbar_kepala);
        toolbar.setLogo(R.drawable.poin);
        toolbar.setTitle(" Ibu Hamil");
        toolbar.setTitleTextColor(getResources().getColor(R.color.putih));
        setSupportActionBar(toolbar);
    }
}
