package com.example.abdulwahab.mmt.FragmentMenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abdulwahab.mmt.R;

import static android.content.ContentValues.TAG;

public class FragmenPengingatBidan extends Fragment {

    public FragmenPengingatBidan() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_pengingat, container, false);
        rootView.setTag(TAG);

        return rootView;
    }

}

