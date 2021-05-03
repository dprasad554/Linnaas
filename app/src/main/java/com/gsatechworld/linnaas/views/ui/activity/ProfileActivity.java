package com.gsatechworld.linnaas.views.ui.activity;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gsatechworld.linnaas.R;
import com.gsatechworld.linnaas.databinding.ActivityProfileBinding;
import com.gsatechworld.linnaas.utils.profile.ProfileResp;
import com.gsatechworld.linnaas.utils.profile.ProfileResult;
import com.gsatechworld.linnaas.viewmodels.ProfileViewModel;
import com.gsatechworld.linnaas.views.base.BaseActivity;

import javax.inject.Inject;

import dmax.dialog.SpotsDialog;

import static dagger.android.AndroidInjection.inject;

public class ProfileActivity extends BaseActivity<ProfileViewModel> {

    ActivityProfileBinding binding;
    ProfileViewModel viewModel;
    private AlertDialog progressDialog;
    SharedPreferences preferences;
    String mobile;
    @Inject
    ViewModelProvider.Factory factory;

    @Override
    public ProfileViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(ProfileViewModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inject(this);
        progressDialog = new SpotsDialog(this, R.style.Custom);
        binding = DataBindingUtil.setContentView(ProfileActivity.this, R.layout.activity_profile_);

        preferences = getSharedPreferences("logindata",0);
        mobile = preferences.getString("mobile","9898989898");
        binding.mobileNumber.setText(mobile);
        binding.btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.firstName.getText().toString().equals("") ||
                   binding.lastName.getText().toString().equals("")
                ){
                    validBlank();
                }
                else {
                    progressDialog.show();
                    updateProfile();
                }
            }
        });
    }

    private void validBlank(){
        Toast.makeText(this,"Please fill data",Toast.LENGTH_LONG).show();
    }
    public void updateProfile(){
        viewModel.updateProfile(new ProfileResp(binding.firstName.getText().toString(),
                binding.lastName.getText().toString(),
                binding.mobileNumber.getText().toString(),
                binding.emailId.getText().toString(),
                "male"
                )).observe(this, new Observer<ProfileResult>() {
            @Override
            public void onChanged(ProfileResult profileResult) {
                progressDialog.dismiss();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("id",profileResult.getProfileResp().getData().getId());
                editor.putString("firstname",profileResult.getProfileResp().getData().getFirst_name());
                editor.putString("lastname",profileResult.getProfileResp().getData().getLast());
                editor.apply();
                startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
                ProfileActivity.this.finish();

            }
        });

    }
}