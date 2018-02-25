package com.example.abdulwahab.mmt;

import android.widget.Toast;

import com.example.abdulwahab.mmt.FragmentMenu.BarKasus;

import java.util.Timer;
import java.util.TimerTask;

import static java.security.AccessController.getContext;

/**
 * Created by ABDUL WAHAB on 08-May-17.
 */

public class timer extends TimerTask {
    Timer timer;
    TimerTask timerTask;
    @Override
    public void run() {

    }
    void setup() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 0, 5000);
    }

}
