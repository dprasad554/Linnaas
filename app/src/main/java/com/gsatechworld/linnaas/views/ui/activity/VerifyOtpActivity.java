package com.gsatechworld.linnaas.views.ui.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.gsatechworld.linnaas.R;
import com.gsatechworld.linnaas.databinding.ActivityVerifyOtpBinding;
import com.gsatechworld.linnaas.utils.login.LoginResponse;
import com.gsatechworld.linnaas.utils.login.LoginResultResp;
import com.gsatechworld.linnaas.utils.verifyOtp.VerifyOtpResp;
import com.gsatechworld.linnaas.utils.verifyOtp.VerifyOtpResult;
import com.gsatechworld.linnaas.viewmodels.VerifyOtpModel;
import com.gsatechworld.linnaas.views.base.BaseActivity;

import javax.inject.Inject;

import dmax.dialog.SpotsDialog;

import static dagger.android.AndroidInjection.inject;

public class VerifyOtpActivity extends BaseActivity<VerifyOtpModel> {

    ActivityVerifyOtpBinding binding;
    VerifyOtpModel viewModel;
    private AlertDialog progressDialog;
    SharedPreferences preferences;



    @Inject
    ViewModelProvider.Factory factory;

    @Override
    public VerifyOtpModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(VerifyOtpModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_verify_otp_);
        inject(this);
        progressDialog = new SpotsDialog(this, R.style.Custom);
        binding = DataBindingUtil.setContentView(VerifyOtpActivity.this, R.layout.activity_verify_otp_);
        preferences = getSharedPreferences("logindata",0);
        String code = preferences.getString("country_code","+91");
        String mobile = preferences.getString("mobile","9898989898");
        binding.otptxt1.setText("+" + code + "-" + mobile);
        binding.btVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.etOtp1.getText().toString().equals("")
                ){
                    validOtp();
                }
                else {
                    progressDialog.show();
                    doSubmit();
                }
            }
        });

    }

    private void validOtp(){
        Toast.makeText(this,"Please enter valid otp",Toast.LENGTH_LONG).show();
    }

    public void doSubmit() {
        preferences = getSharedPreferences("logindata",0);
        String mobile_no = preferences.getString("mobile","");

        viewModel.sendOtp(new VerifyOtpResp( mobile_no,"1111"))
                .observe(this, new Observer<VerifyOtpResult>() {
                    @Override
                    public void onChanged(VerifyOtpResult verifyOtpResult) {
                                progressDialog.dismiss();
                                if(verifyOtpResult != null && verifyOtpResult.getVerifyOtpResp().getSuccess().equals("true")){


                                    preferences = getSharedPreferences("logindata",0);
                                    String alreadyuser = preferences.getString("alreadyuser","0");
                                    SharedPreferences.Editor editor = preferences.edit();

                                    if(verifyOtpResult.getVerifyOtpResp().getData().size() == 0) {

                                        startActivity(new Intent(VerifyOtpActivity.this, ProfileActivity.class));
                                        finish();
                                    }
                                    else {

                                        editor.putString("id",verifyOtpResult.getVerifyOtpResp().getData().get(0).getId());
                                        editor.putString("firstname",verifyOtpResult.getVerifyOtpResp().getData().get(0).getFirst_name());
                                        editor.putString("lastname",verifyOtpResult.getVerifyOtpResp().getData().get(0).getLast());
                                        editor.apply();
                                        startActivity(new Intent(VerifyOtpActivity.this,HomeActivity.class));
                                        finish();
                                    }
                                }
                    }
                });

    }
}