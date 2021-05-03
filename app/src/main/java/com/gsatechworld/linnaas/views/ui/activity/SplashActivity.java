package com.gsatechworld.linnaas.views.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gsatechworld.linnaas.R;

import java.util.Locale;

public class SplashActivity extends AppCompatActivity {

    Handler mHandlerTime;
    Runnable mRunnableTimeOut;
    SharedPreferences preferences;

    private int SPLASH_TIME_OUT = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setfullscreen();
        splashScreenCall();
    }

    private void setfullscreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    private void splashScreenCall() {
        mHandlerTime = new Handler();
        mRunnableTimeOut = new Runnable() {
            @Override
            public void run() {
                preferences = getSharedPreferences("logindata",0);

                String mobile = preferences.getString("mobile","");
                String lang = preferences.getString("lang","en");

                    Locale locale = new Locale(lang);
                    Locale.setDefault(locale);
                    Configuration configuration = new Configuration();
                    configuration.locale = locale;
                    getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());

                if(mobile.equals("")) {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    finish();
                }
                else{
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("audiopermission","0");
                    editor.apply();
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    finish();
                }

            }
        };
    }

    protected void onPause() {
        if (this.mHandlerTime != null) {
            this.mHandlerTime.removeCallbacks(this.mRunnableTimeOut);
        }
        super.onPause();
    }

    protected void onResume() {
        if (this.mHandlerTime != null) {
            this.mHandlerTime.postDelayed(this.mRunnableTimeOut, (long) this.SPLASH_TIME_OUT);
        }
        super.onResume();
    }
}