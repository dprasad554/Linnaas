package com.gsatechworld.linnaas.views.ui.activity;

import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.gsatechworld.linnaas.R;
import com.gsatechworld.linnaas.databinding.ActivitySearchBinding;
import com.gsatechworld.linnaas.utils.search.SearchResp;
import com.gsatechworld.linnaas.utils.search.SearchResult;
import com.gsatechworld.linnaas.viewmodels.SearchModel;
import com.gsatechworld.linnaas.views.adapter.SearchAdapter;
import com.gsatechworld.linnaas.views.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dmax.dialog.SpotsDialog;

import static dagger.android.AndroidInjection.inject;

public class SearchActivity extends BaseActivity<SearchModel> {

    ActivitySearchBinding binding;
    SearchModel viewModel;
    SearchView searchView;
    List<SearchResult> resultList = new ArrayList<>();
    SearchAdapter searchAdapter;
    private AlertDialog progressDialog;
    SharedPreferences preferences;

    @Inject
    ViewModelProvider.Factory factory;

    @Override
    public SearchModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(SearchModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(this);

        binding = DataBindingUtil.setContentView(SearchActivity.this, R.layout.activity_search_);

        progressDialog = new SpotsDialog(this, R.style.Custom);

        searchView = binding.searchText;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.searchRv.setLayoutManager(linearLayoutManager);
        searchAdapter = new SearchAdapter(this,resultList);
        binding.searchRv.setAdapter(searchAdapter);

        binding.backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchActivity.this, HomeActivity.class));
                SearchActivity.this.finish();
            }
        });

        binding.searchText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getSearch(newText);
                return false;
            }
        });
    }

    private void getSearch(String query){
        preferences = getSharedPreferences("logindata",0);
        String lang = preferences.getString("lang","en");
        if(lang.equals("en")){
            lang = "eng";
        }
        else {
            lang = "arb";
        }
        viewModel.getSearchResult(new SearchResp(query,lang)).observe(this, new Observer<SearchResp>() {
            @Override
            public void onChanged(SearchResp searchResp) {

                 resultList = searchResp.getResult();
                 searchAdapter.setData(resultList);

            }
        });
    }
}