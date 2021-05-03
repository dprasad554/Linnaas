package com.gsatechworld.linnaas.views.ui.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gsatechworld.linnaas.R;
import com.gsatechworld.linnaas.databinding.ActivityWalletBinding;
import com.gsatechworld.linnaas.utils.wallet.WalletResult;
import com.gsatechworld.linnaas.utils.wallet.Walletresp;
import com.gsatechworld.linnaas.viewmodels.WalletModel;
import com.gsatechworld.linnaas.views.adapter.WalletAdapter;
import com.gsatechworld.linnaas.views.base.BaseActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import dmax.dialog.SpotsDialog;

import static dagger.android.AndroidInjection.inject;

public class WalletActivity extends BaseActivity<WalletModel> {

    ActivityWalletBinding binding;
    WalletModel viewModel;
    List<WalletResult> resultList = new ArrayList<>();
    WalletAdapter walletAdapter;
    private AlertDialog progressDialog;
    SharedPreferences preferences;
    @Inject
    ViewModelProvider.Factory factory;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    public WalletModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(WalletModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(this);
        progressDialog = new SpotsDialog(this, R.style.Custom);
        progressDialog.show();
        binding = DataBindingUtil.setContentView(WalletActivity.this, R.layout.activity_wallet_);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.walletRv.setLayoutManager(linearLayoutManager);
        walletAdapter = new WalletAdapter(this,resultList);
        binding.walletRv.setAdapter(walletAdapter);

        binding.backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WalletActivity.this, HomeActivity.class));
                WalletActivity.this.finish();
            }
        });
        getWalletHistory();

    }

    private void getWalletHistory(){
        preferences = getSharedPreferences("logindata",0);
        String id = preferences.getString("id","1");
        viewModel.getWalletHistory(new Walletresp(id)).observe(this, new Observer<Walletresp>() {
            @Override
            public void onChanged(Walletresp walletresp) {
                progressDialog.dismiss();
                resultList = walletresp.getResultList();
                binding.walletPts.setText(walletresp.getTotal_wallet_points());
                preferences = getSharedPreferences("logindata",0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("wallet_pts",walletresp.getTotal_wallet_points());
                editor.apply();
                if(resultList != null) {
                    walletAdapter.setData(resultList);
                }
            }
        });
    }
    public void showDate(){
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        Toast.makeText(WalletActivity.this, dayOfMonth + "-" + (monthOfYear + 1) + "-" + year, Toast.LENGTH_SHORT).show();

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();

    }
}