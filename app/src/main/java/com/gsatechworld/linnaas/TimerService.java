package com.gsatechworld.linnaas;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class TimerService extends Service {

    int hr = 0,min = 0,sec = 0,count = 0,playVar = 0,stopVar = 0,current_count = 0,total_count = 0,filled_count = 0,progressStatus = 0;
    Intent intent;
    public static String str_receiver = "timer.service";
    private Handler handler = new Handler();
    private Timer timer = null;
    long notify_interval = 1000;

    public TimerService(){

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        count = 1;
        timer = new Timer();
        timer.schedule(new TimerTaskToUpdateTime(), 5, notify_interval);
        intent = new Intent(str_receiver);
        timerRunner();
    }

    private void timerRunner(){

        while(count == 1) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            sec++;
            if (sec == 60) {
                sec = 0;
            }

            updateUI(sec, min);
        }

    }

    private class TimerTaskToUpdateTime extends TimerTask {

        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    timerRunner();
                }
            });
        }
    }
    private void updateUI(int sec,int min){

        Log.d("sec",String.valueOf(sec));
        intent.putExtra("sec",String.valueOf(sec));
        sendBroadcast(intent);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        count = 0;
    }

}
