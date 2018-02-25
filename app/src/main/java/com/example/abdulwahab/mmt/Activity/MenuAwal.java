package com.example.abdulwahab.mmt.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.abdulwahab.mmt.R;
import com.example.abdulwahab.mmt.Tab.SlidingTabLayout;
import com.example.abdulwahab.mmt.Tab.TabsUtama;
public class MenuAwal extends AppCompatActivity {
    String name;

    Toolbar toolbar;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_awal);
        toolBar();
        slidingTabViewPager();

    }
    private void slidingTabViewPager() {
        viewPager = (ViewPager) findViewById(R.id.menu_vpTab);
        viewPager.setAdapter(new TabsUtama(getSupportFragmentManager(), getApplicationContext()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        toolbar.setTitle(" Grafik Kematian");
                        break;
                    case 1:
                        toolbar.setTitle(" Data Kasus");
                        break;
                    case 2:
                        toolbar.setTitle(" Grafik Resiko Tertinggi");
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        menu.findItem(R.id.btn_login).setTitle(name);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.masuk) {
            Intent a = (new Intent(MenuAwal.this, LoginActivity.class));
            startActivity(a);
            return true;
        }
        else if (id == R.id.keluar) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void toolBar() {
        toolbar = (Toolbar) findViewById(R.id.menu_toolbar_utama);
        toolbar.setLogo(R.drawable.poin);
        toolbar.setTitle(" Grafik Kematian");
        toolbar.setTitleTextColor(getResources().getColor(R.color.putih));
        setSupportActionBar(toolbar);
    }




}
