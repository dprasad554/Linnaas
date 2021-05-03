package com.gsatechworld.linnaas.views.ui.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.linnaas.R;
import com.gsatechworld.linnaas.databinding.FragmentHistoryBinding;
import com.gsatechworld.linnaas.utils.history.HistoryResp;
import com.gsatechworld.linnaas.utils.history.HistoryResult;
import com.gsatechworld.linnaas.views.adapter.HistoryAdapter;
import com.gsatechworld.linnaas.views.base.BaseFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

//import static android.databinding.DataBindingUtil.inflate;
import dmax.dialog.SpotsDialog;

import static androidx.databinding.DataBindingUtil.inflate;

public class HistoryFragment extends BaseFragment<HistoryFragmentViewModel> {

    @Inject
    @Named("HistoryFragment")
    ViewModelProvider.Factory factory;

    private HistoryFragmentViewModel viewModel;
    private FragmentHistoryBinding binding;
    private AlertDialog progressDialog;
    HistoryAdapter historyAdapter;
    RecyclerView tb_history_rv;
    LinearLayout tabeesh_filt;
    private int mYear, mMonth, mDay;
    private List<HistoryResult> resultList = new ArrayList<>();
    SharedPreferences preferences;
    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getHistory();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = inflate(inflater, R.layout.fragment_history_, container, false);

        progressDialog = new SpotsDialog(getContext(), R.style.Custom);
        progressDialog.show();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        binding.tbHistoryRv.setLayoutManager(linearLayoutManager);
        historyAdapter = new HistoryAdapter(getActivity());
        binding.tbHistoryRv.setAdapter(historyAdapter);



        return binding.getRoot();
    }

    private List<HistoryResult> getHistory(){
        preferences = getActivity().getSharedPreferences("logindata",0);
        String id = preferences.getString("id","1");
        viewModel.getHistory(new HistoryResp(id)).observe(this, new Observer<HistoryResp>() {
            @Override
            public void onChanged(HistoryResp historyResp) {

                progressDialog.dismiss();
                resultList = historyResp.getResults();
                preferences = getActivity().getSharedPreferences("logindata",0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("wallet_pts",historyResp.getTotal_wallet_points());
                editor.putString("total_reads",historyResp.getTotal_reads());
                editor.apply();
                binding.tabeeshPts.setText(historyResp.getTotal_reads());
                if(resultList != null) {
                    historyAdapter.setData(resultList);
                }
            }
        });
        return resultList;
    }

    public void showDate(){
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        Toast.makeText(getActivity(), dayOfMonth + "-" + (monthOfYear + 1) + "-" + year, Toast.LENGTH_SHORT).show();

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();

    }

    @Override
    public HistoryFragmentViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(HistoryFragmentViewModel.class);
        return viewModel;
    }
}