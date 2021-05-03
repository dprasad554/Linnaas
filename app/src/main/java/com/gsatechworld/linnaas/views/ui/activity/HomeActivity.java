package com.gsatechworld.linnaas.views.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.gsatechworld.linnaas.R;
import com.gsatechworld.linnaas.databinding.ActivityHomeBinding;
import com.gsatechworld.linnaas.utils.history.HistoryResp;
import com.gsatechworld.linnaas.utils.history.HistoryResult;
import com.gsatechworld.linnaas.utils.homepage.HomeData;
import com.gsatechworld.linnaas.utils.homepage.HomeResp;
import com.gsatechworld.linnaas.utils.profile.ProfileDetailResp;
import com.gsatechworld.linnaas.viewmodels.HomeViewModel;
import com.gsatechworld.linnaas.views.adapter.TabeeshAdapter;
import com.gsatechworld.linnaas.views.base.BaseActivity;
import com.gsatechworld.linnaas.views.ui.fragments.HistoryFragment;
import com.gsatechworld.linnaas.views.ui.fragments.HomeFragment;
import com.gsatechworld.linnaas.views.ui.fragments.ProfileFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.ibrahimsn.lib.OnItemSelectedListener;

import static dagger.android.AndroidInjection.inject;

public class HomeActivity extends BaseActivity<ViewModel> {

    ActivityHomeBinding binding;
    HomeViewModel viewModel;
    List<String> listData = new ArrayList<>();
    private List<HistoryResult> resultList = new ArrayList<>();
    ProgressDialog progressDialog;

    private int mCurNavigationId;

    @Inject
    ViewModelProvider.Factory factory;

    @Override
    public ViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(this);

        binding = DataBindingUtil.setContentView(HomeActivity.this, R.layout.activity_home_);

        mCurNavigationId = 0;
        Fragment fragment = null;
        fragment = new HomeFragment();
        loadFragment(fragment);

        binding.bottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {
                Fragment fragment = null;
                switch (i) {
                    case 0:
                        mCurNavigationId = 0;
                        fragment = new HomeFragment();
                        break;
                    case 1:
                        mCurNavigationId = 1;
                        fragment = new HistoryFragment();
                        break;
                    case 2:
                        mCurNavigationId = 2;
                        fragment = new ProfileFragment();
                        break;
                }
                return loadFragment(fragment);
            }
        });

        binding.walletImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, WalletActivity.class));
            }
        });

        binding.searchIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, SearchActivity.class));
            }
        });
    }

    private boolean loadFragment(Fragment fragment) {

        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
    @Override
    public void onBackPressed() {
        if (mCurNavigationId == 1 || mCurNavigationId == 2) {
            mCurNavigationId = 0;
            //loadFragment(new HomeFragment());
            startActivity(new Intent(HomeActivity.this, HomeActivity.class));
            finish();
        } else {

                super.onBackPressed();
            }

    }

}