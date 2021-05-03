package com.gsatechworld.linnaas.views.ui.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.gsatechworld.linnaas.R;
import com.gsatechworld.linnaas.databinding.FragmentProfileBinding;
import com.gsatechworld.linnaas.utils.profile.ProfileDetailResp;
import com.gsatechworld.linnaas.views.base.BaseFragment;
import com.gsatechworld.linnaas.views.ui.activity.HomeActivity;
import com.gsatechworld.linnaas.views.ui.activity.LoginActivity;
import com.gsatechworld.linnaas.views.ui.activity.WalletActivity;

import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;

//import static android.databinding.DataBindingUtil.inflate;
import dmax.dialog.SpotsDialog;

import static androidx.databinding.DataBindingUtil.inflate;

public class ProfileFragment extends BaseFragment<ProfileFragmentViewModel> {

    //String first_name,last_name;
    Dialog popupP;
    SharedPreferences preferences;
    private AlertDialog progressDialog;

    @Inject
    @Named("ProfileFragment")
    ViewModelProvider.Factory factory;

    private ProfileFragmentViewModel viewModel;
    private FragmentProfileBinding binding;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getProfile();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = inflate(inflater, R.layout.fragment_profile_, container, false);

        progressDialog = new SpotsDialog(getContext(), R.style.Custom);
        progressDialog.show();
        preferences = getContext().getSharedPreferences("logindata",0);

        String first_name = preferences.getString("firstname","User");
        String last_name = preferences.getString("lastname","Name");
        String full_name;
        //if(preferences.getString("lang","ar").equals("ar")) {
        //    full_name = convertNumberEnglishToArabic(first_name + last_name);
        //}
        //else{
        full_name = first_name + " " + last_name;
        //}

        binding.fullName.setText(full_name);
        binding.cvWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), WalletActivity.class));

            }
        });

        binding.cvTabeesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), HomeActivity.class));
            }
        });

        binding.changeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InitializePopup();
            }
        });
        binding.logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                preferences = getContext().getSharedPreferences("logindata",0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("mobile","");
                editor.putString("alreadyuser","1");
                editor.putString("wallet_pts","0");
                editor.putString("total_reads","0");
                editor.remove("audiopermission");
                editor.apply();
                progressDialog.dismiss();
                getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });
        binding.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Use this link \n" +
                        "https://play.google.com/store/apps/details?id=com.gsatechworld.linnaas \n" +
                        "to share this on playstore"
                        ;
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share Using");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
        return binding.getRoot();
    }
    private void getProfile(){
        preferences = getActivity().getSharedPreferences("logindata",0);
        String id = preferences.getString("id","1");
        viewModel.getProfile(new ProfileDetailResp(id)).observe(this, new Observer<ProfileDetailResp>() {
            @Override
            public void onChanged(ProfileDetailResp profileDetailResp) {

                progressDialog.dismiss();
                preferences = getActivity().getSharedPreferences("logindata",0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("wallet_pts",profileDetailResp.getWallet_pts());
                editor.putString("total_reads",profileDetailResp.getTotal_reads());
                editor.apply();
                binding.walletPts.setText(profileDetailResp.getWallet_pts());
                binding.tabeeshPts.setText(profileDetailResp.getTotal_reads());

            }
        });
    }

    public static String convertNumberEnglishToArabic(String input) {

        String value = "";

        for (char character : input.toCharArray()) {

            String str = "";
            int ascii = (int) character;

            if (ascii >= 97 && ascii <= 122) {
                //english number
                int valueOld = ascii + 1584;
                char valueChar = (char) valueOld;
                str = String.valueOf(valueChar);
            } else {
                //default
                str = String.valueOf(character);
            }

            value += str;
        }
        return value;
    }
    private void InitializePopup() {
        popupP = new Dialog(getActivity());
        popupP.requestWindowFeature(Window.FEATURE_NO_TITLE);
        popupP.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        popupP.setContentView(R.layout.pop_up_choose_language);
        popupP.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupP.setCancelable(true);
        popupP.setCanceledOnTouchOutside(true);
        popupP.show();
        final CardView cvEnglish = popupP.findViewById(R.id.cv_english);
        final CardView cvArabic = popupP.findViewById(R.id.cv_arabic);

        int width = getActivity().getResources().getDisplayMetrics().widthPixels - 100;
        int height = getActivity().getResources().getDisplayMetrics().heightPixels - 250;
        popupP.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupP.getWindow().setGravity(Gravity.CENTER);

        cvEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  popupP.dismiss();
                  preferences = getContext().getSharedPreferences("logindata",0);
                  SharedPreferences.Editor editor = preferences.edit();
                  editor.putString("lang","en");
                  editor.apply();
                Locale locale = new Locale("en");
                Locale.setDefault(locale);
                Configuration configuration = new Configuration();
                configuration.locale = locale;
                getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());
                startActivity(new Intent(getActivity(),HomeActivity.class));
                getActivity().finish();
            }
        });
        cvArabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  popupP.dismiss();
                preferences = getContext().getSharedPreferences("logindata",0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("lang","ar");
                editor.apply();
                Locale locale = new Locale("ar");
                Locale.setDefault(locale);
                Configuration configuration = new Configuration();
                configuration.locale = locale;
                getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());
                startActivity(new Intent(getActivity(),HomeActivity.class));
                getActivity().finish();
            }
        });

    }
    @Override
    public ProfileFragmentViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(ProfileFragmentViewModel.class);
        return viewModel;
    }
}