package com.example.abdulwahab.mmt.Tab;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.example.abdulwahab.mmt.FragmentMenu.Kasus;
import com.example.abdulwahab.mmt.FragmentMenu.IbuHamil;
import com.example.abdulwahab.mmt.FragmentMenu.Kematian;
import com.example.abdulwahab.mmt.R;

public class TabsKepala extends FragmentPagerAdapter {
    private Context context;
    public String[] titles = {" Ibu Hamil"," Kematian"," Kasus"};
    public int[] icons = new int[]
            {R.drawable.persalinan,
            R.drawable.die,
            R.drawable.kasus};
    private int heightIcon;

    public TabsKepala(FragmentManager fm, Context c) {
        super(fm);
        context = c;
        double scale = c.getResources().getDisplayMetrics().density;
        heightIcon = (int) (24 * scale + 0.5f);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment frag =null;
        if (position==0){
            frag=new IbuHamil();
        }else if (position==1){
            frag=new Kematian();
        }else if (position==2){
            frag=new Kasus();
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
