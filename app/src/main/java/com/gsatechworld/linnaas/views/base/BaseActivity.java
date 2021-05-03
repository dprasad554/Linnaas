package com.gsatechworld.linnaas.views.base;


import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.linnaas.R;

import java.util.Locale;
import java.util.Objects;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity<T extends ViewModel> extends DaggerAppCompatActivity {
    private Locale locale;
    private T viewModel;
    public Dialog alertDialog;

    /**
     * @return view model instance
     */
    public abstract T getViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = viewModel == null ? getViewModel() : viewModel;
        alertDialog = new Dialog(this);
        /*locale = new Locale(getLanguageShortForm());
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());*/
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setToolBar(Toolbar toolbar, String name) {
        toolbar.setTitle(name);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.previous));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /*protected void changeLanguage() {
        String languageToLoad = getLanguageShortForm(); // your language
        locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }*/

    /*private String getLanguageShortForm() {
        PrefManager prefManager = new PrefManager(this);
        if (prefManager.getSelectedLanguage().equalsIgnoreCase("English")) {
            return "en";
        } else if (prefManager.getSelectedLanguage().equalsIgnoreCase("Tamil")) {
            return "tl";
        }  else if (prefManager.getSelectedLanguage().equalsIgnoreCase("Hindi")) {
            return "hi";
        }   else if (prefManager.getSelectedLanguage().equalsIgnoreCase("Telugu")) {
            return "tu";
        } else {
            return "ka";
        }
    }*/

    public void showAlertDialog(String title, String message, boolean isNegativeButtonAvailable, View.OnClickListener positiveClickListener,
                                View.OnClickListener negativeClickListener) {
        if (alertDialog != null) {
            if (alertDialog.isShowing()) {
                alertDialog.dismiss();
            }
        } else {
            alertDialog = new Dialog(this);
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        alertDialog.setCancelable(false);
        alertDialog.setContentView(R.layout.custom_alert_dialog_);

        TextView tvTitle = alertDialog.findViewById(R.id.tv_title);
        tvTitle.setText(title);

        TextView tvMessage = alertDialog.findViewById(R.id.tv_message);
        tvMessage.setText(message);

        LinearLayout llbutton = alertDialog.findViewById(R.id.ll_button);
        Button btnOkay = alertDialog.findViewById(R.id.btn_okay);
        if(isNegativeButtonAvailable) {
            llbutton.setVisibility(View.VISIBLE);
            btnOkay.setVisibility(View.GONE);
            Button btnYes = alertDialog.findViewById(R.id.btn_yes);
            Button btnNo = alertDialog.findViewById(R.id.btn_no);
            btnYes.setOnClickListener(positiveClickListener);
            btnNo.setOnClickListener(negativeClickListener);
        } else {
            llbutton.setVisibility(View.GONE);
            btnOkay.setVisibility(View.VISIBLE);
            btnOkay.setOnClickListener(positiveClickListener);
        }

        alertDialog.show();
    }

}
