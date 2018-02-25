package com.example.abdulwahab.mmt.Tab;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.example.abdulwahab.mmt.FragmentMenu.FragmenDataIbuHamil;
import com.example.abdulwahab.mmt.FragmentMenu.FragmenPengingatBidan;
import com.example.abdulwahab.mmt.FragmentMenu.FragmenStatusKematian;
import com.example.abdulwahab.mmt.R;


public class TabsBidan extends FragmentPagerAdapter {
    private Context context;
    public String[] titles = {" Data Ibu Hamil"," Pengingat","Status Kehamilan"};
    public int[] icons = new int[]{
            R.drawable.list,
            R.drawable.notifi,
            R.drawable.persalinan};
    private int heightIcon;

    public TabsBidan(FragmentManager fm, Context c) {
        super(fm);
        context = c;
        double scale = c.getResources().getDisplayMetrics().density;
        heightIcon = (int) (24 * scale + 0.5f);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment frag =null;
        if (position==0){
            frag=new FragmenDataIbuHamil();
        }else if (position==1){
            frag=new FragmenPengingatBidan();
        }
        else if (position==2){
            frag=new FragmenStatusKematian();
        }
        return frag;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Drawable d =context.getResources().getDrawable(icons[position]);
        d.setBounds(0,0,heightIcon,heightIcon);
        ImageSpan is = new ImageSpan(d);
        SpannableString sp =new SpannableString(" ");
        sp.setSpan(is,0,sp.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Return title[position]
        return (sp);
    }
}
