package com.gsatechworld.linnaas;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gsatechworld.linnaas.utils.homepage.UpdateReadResp;
import com.gsatechworld.linnaas.viewmodels.RecitationViewModel;
import com.gsatechworld.linnaas.views.base.BaseActivity;
import com.gsatechworld.linnaas.views.ui.activity.HomeActivity;

import java.util.Timer;

import javax.inject.Inject;

import dmax.dialog.SpotsDialog;


public class RecitationActivity extends BaseActivity<RecitationViewModel> {

    TextView start,stop,resume,hr_txt,min_txt,sec_txt,current_count_txt,total_count_txt,verse_name_txt;
    long elapsedTime;
    ImageView reset_icon,play_icon,stop_icon,fill_auto_count,start_auto_count,back_btn;
    int hr = 0,min = 0,sec = 0,count = 0,playVar = 0,stopVar = 0,current_count = 0,total_count = 0,filled_count = 0,progressStatus = 0,autoPlay = 0;

    LinearLayout tap_layout;
    Dialog fill_count_dialog;
    ProgressBar progressBar;
    Timer timer;
    int flag = 0;
    SharedPreferences preferences;
    private AlertDialog progressDialog;
    private Handler handler = new Handler();
    private String verse_id = "";
    private String verse_name = "";
    RecitationViewModel viewModel;

    @Inject
    ViewModelProvider.Factory factory;

    @Override
    public RecitationViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(RecitationViewModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recitation);

        verse_id += getIntent().getStringExtra("verse_id");
        verse_name += getIntent().getStringExtra("verse_name");

        hr_txt = (TextView)findViewById(R.id.hr_txt);
        min_txt = (TextView)findViewById(R.id.min_txt);
        sec_txt = (TextView)findViewById(R.id.sec_txt);
        reset_icon = (ImageView)findViewById(R.id.reset_icon);
        play_icon = (ImageView)findViewById(R.id.play_icon);
        stop_icon = (ImageView)findViewById(R.id.stop_icon);
        fill_auto_count = (ImageView)findViewById(R.id.fill_auto_count);
        start_auto_count = (ImageView)findViewById(R.id.start_auto_count);
        tap_layout = (LinearLayout)findViewById(R.id.tap_layout);
        current_count_txt = (TextView)findViewById(R.id.current_count);
        total_count_txt = (TextView)findViewById(R.id.total_count);
        progressBar = (ProgressBar)findViewById(R.id.circularProgressbar);
        verse_name_txt = (TextView)findViewById(R.id.verse_name_txt);
        back_btn = (ImageView)findViewById(R.id.back_iv);
        verse_name_txt.setText(verse_name);

        progressDialog = new SpotsDialog(RecitationActivity.this, R.style.Custom);
        fill_count_dialog = new Dialog(this);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RecitationActivity.this,HomeActivity.class));
                finish();
            }
        });
        play_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(playVar == 0){
                    play_icon.setImageDrawable(getResources().getDrawable(R.drawable.play));
                    playVar = 1;
                    count = 1;
                    startTimer();
                }
                else{
                    play_icon.setImageDrawable(getResources().getDrawable(R.drawable.pause));
                    playVar = 0;
                    count = 0;
                    cancelTimer();
                }
            }
        });
        stop_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stopVar == 0){
                    stopVar = 1;
                    playVar = 0;
                    play_icon.setImageDrawable(getResources().getDrawable(R.drawable.pause));
                    stop_icon.setImageDrawable(getResources().getDrawable(R.drawable.save));
                    cancelTimer();
                }
                else
                    {
                    stopVar = 0;
                    Update("1",total_count_txt.getText().toString(),verse_id);
                    stop_icon.setImageDrawable(getResources().getDrawable(R.drawable.stop_1));
                }
            }
        });
        tap_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(playVar == 1){

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while(progressStatus <= 10) {

                                progressBar.setProgress(progressStatus);
                                progressStatus += 2;
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }
                            progressBar.setProgress(0);
                            progressStatus = 0;
                        }

                    }).start();

                    current_count++;
                    total_count++;
                    current_count_txt.setText(String.valueOf(current_count));
                    total_count_txt.setText(String.valueOf(total_count));
                }
            }
        });

        fill_auto_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(playVar == 1) {

                    fill_count_dialog.setContentView(R.layout.fill_auto_count_dialog);
                    fill_count_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    fill_count_dialog.setCancelable(false);
                    fill_count_dialog.setCanceledOnTouchOutside(false);

                    fill_count_dialog.show();
                    final CardView ok_card = fill_count_dialog.findViewById(R.id.cv_update);
                    final EditText et_count = fill_count_dialog.findViewById(R.id.et_count);

                    ok_card.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            filled_count = Integer.parseInt(et_count.getText().toString());
                            fill_count_dialog.dismiss();
                        }
                    });
                }
            }
        });

        start_auto_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(playVar == 1) {

                    start_auto_count.setImageDrawable(getResources().getDrawable(R.drawable.autopause));
                    if(autoPlay == 0) {
                        autoPlay = 1;
                    }
                    else if(autoPlay == 1){
                        autoPlay = 0;
                    }
                    flag  = Integer.valueOf(current_count_txt.getText().toString());

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (filled_count > flag) {
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {

                                        while (progressStatus <= 10) {
                                            progressBar.setProgress(progressStatus);
                                            progressStatus += 2;
                                            try {
                                                Thread.sleep(100);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        progressBar.setProgress(0);
                                        progressStatus = 0;
                                    }

                                }).start();

                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                flag++;
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {

                                        current_count++;
                                        total_count++;
                                        current_count_txt.setText(String.valueOf(current_count));
                                        total_count_txt.setText(String.valueOf(total_count));
                                    }
                                });
                                if(playVar == 0){

                                    autoPlay = 0;
                                    break;
                                }
                                if(autoPlay == 0){

                                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                                        @Override
                                        public void run() {

                                            start_auto_count.setImageDrawable(getResources().getDrawable(R.drawable.autopause));
                                        }
                                    });
                                    break;
                                }
                            }

                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {

                                    start_auto_count.setImageDrawable(getResources().getDrawable(R.drawable.autoplay_1));
                                }
                            });

                        }
                    }).start();
                }
            }

        });

        reset_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hr_txt.setText("00");
                min_txt.setText("00");
                sec_txt.setText("00");
                hr = 0;
                min = 0;
                sec = 0;
                current_count = 0;
                current_count_txt.setText(String.valueOf(current_count));
            }
        });

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            sec++;
            if(sec >= 60){
                min++;
                sec = 0;
            }
            if(min >= 60){
                hr++;
                min = 0;
            }

            if(sec < 10){
                sec_txt.setText("0" + String.valueOf(sec));
            }
            else {
                sec_txt.setText(String.valueOf(sec));
            }
            if(min < 10) {

                min_txt.setText("0" + String.valueOf(min));
            }
            else {
                min_txt.setText(String.valueOf(min));
            }
            if(hr < 10){
                hr_txt.setText("0" + String.valueOf(hr));
            }
            else {
                hr_txt.setText(String.valueOf(hr));
            }

            startTimer();

        }
    };

    public void startTimer(){

        handler.postDelayed(runnable,1000);
    }

    public void cancelTimer(){

        handler.removeCallbacks(runnable);
    }

    private void Update(String read_type,String read_count,String verse_id){
        progressDialog.show();
        preferences = getSharedPreferences("logindata",0);
        String id = preferences.getString("id","1");
        viewModel.updateReadCount(new UpdateReadResp(id,verse_id,read_type,read_count)).observe(this,
                new Observer<UpdateReadResp>() {
                    @Override
                    public void onChanged(UpdateReadResp updateReadResp) {

                        progressDialog.dismiss();
                        if(updateReadResp != null) {

                            SharedPreferences preferences = getSharedPreferences("logindata", 0);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("wallet_pts", updateReadResp.getWallet_pts());
                            editor.apply();

                            startActivity(new Intent(RecitationActivity.this, HomeActivity.class));
                            finish();
                        }

                    }
                }
        );
    }

}