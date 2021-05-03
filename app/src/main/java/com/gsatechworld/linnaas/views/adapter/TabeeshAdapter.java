package com.gsatechworld.linnaas.views.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.linnaas.R;

import java.util.ArrayList;

public class TabeeshAdapter extends RecyclerView.Adapter implements RecognitionListener {

    Context context;
    Dialog recordPopup;
    private SpeechRecognizer speechRecognizer;
    Dialog successPopup;
    String finalOutput = "";
    TextView pharseT;

    public TabeeshAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.verse_list_, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InitializeRecordPopup();
                initializeRecordPopup("Hello");
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }



    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bindView(int position) {
        }

        @Override
        public void onClick(View view) {
        }
    }

    private void InitializeRecordPopup() {
        recordPopup = new Dialog(context);
        recordPopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        recordPopup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        recordPopup.setContentView(R.layout.popup_record_);
        recordPopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        recordPopup.setCancelable(true);
        recordPopup.setCanceledOnTouchOutside(true);
    }

    private void initializeRecordPopup(final String tabeesh) {
        recordPopup.setContentView(R.layout.popup_record_);
        recordPopup.setCancelable(true);
        recordPopup.setCanceledOnTouchOutside(true);
        recordPopup.show();

        final ImageView stRecord = recordPopup.findViewById(R.id.st_rec);
        final ImageView stStop = recordPopup.findViewById(R.id.st_stop);
        final TextView tPhrase = recordPopup.findViewById(R.id.tPhrase);
        pharseT = recordPopup.findViewById(R.id.pharseT);

        String phrase = "Phrase " + tabeesh;

        tPhrase.setText(phrase);


//        final TextView view_cart = (TextView) recordPopup.findViewById(R.id.view_cart);
//        final ImageView view_cart_img =  recordPopup.findViewById(R.id.view_cart_img);


        int width = context.getResources().getDisplayMetrics().widthPixels - 100;
        int height = context.getResources().getDisplayMetrics().heightPixels - 250;
        recordPopup.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        recordPopup.getWindow().setGravity(Gravity.CENTER);


        stRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startListening();

                /*InitializeSuccessPopup();
                initializeSuccessPopup("Success");*/
            }
        });
        stStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordPopup.dismiss();
                stopListen();
                if (pharseT.getText().toString().toLowerCase().equalsIgnoreCase(tabeesh)){
                    InitializeSuccessPopup();
                    initializeSuccessPopup("Success");
                } else {
                    InitializeSuccessPopup();
                    initializeSuccessPopup("Unsuccess");
                }
                /*InitializeSuccessPopup();
                initializeSuccessPopup("Unsuccess");*/

            }
        });


        recordPopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        recordPopup.setCancelable(true);
        recordPopup.setCanceledOnTouchOutside(true);
        recordPopup.show();
    }


    private void InitializeSuccessPopup() {
        successPopup = new Dialog(context);
        successPopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        successPopup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        successPopup.setContentView(R.layout.popup_success_);
        successPopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        successPopup.setCancelable(true);
        successPopup.setCanceledOnTouchOutside(true);
    }

    private void initializeSuccessPopup(String status) {
        successPopup.setContentView(R.layout.popup_success_);
        successPopup.setCancelable(true);
        successPopup.setCanceledOnTouchOutside(true);
        successPopup.show();

        final ImageView iv_suc = successPopup.findViewById(R.id.iv_suc);
        final TextView tv_suc = successPopup.findViewById(R.id.tv_suc);
        final Button bt_cls = successPopup.findViewById(R.id.bt_cls);


        if (status.equals("Success")) {
            iv_suc.setImageDrawable(context.getResources().getDrawable(R.drawable.accept));
            tv_suc.setText(context.getResources().getString(R.string.successful_completion));
            bt_cls.setText("Close");
            bt_cls.setBackground(context.getResources().getDrawable(R.drawable.bg_gradient));
        } else {
            iv_suc.setImageDrawable(context.getResources().getDrawable(R.drawable.wrong));
            tv_suc.setText(context.getResources().getString(R.string.unsuccesful));
            bt_cls.setText("Try Again");
            bt_cls.setBackground(context.getResources().getDrawable(R.drawable.bt_gradient));
        }

//        final TextView view_cart = (TextView) recordPopup.findViewById(R.id.view_cart);
//        final ImageView view_cart_img =  recordPopup.findViewById(R.id.view_cart_img);


        int width = context.getResources().getDisplayMetrics().widthPixels - 100;
        int height = context.getResources().getDisplayMetrics().heightPixels - 250;
        successPopup.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        successPopup.getWindow().setGravity(Gravity.CENTER);


        /*stRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recordPopup.isShowing())
                    recordPopup.dismiss();
            }
        });
        stStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordPopup.dismiss();


            }
        });*/


        successPopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        successPopup.setCancelable(true);
        successPopup.setCanceledOnTouchOutside(true);
        successPopup.show();
    }


    private void startListening() {
        // start listening
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);
        speechRecognizer.setRecognitionListener(this);
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en");
        intent.putExtra("android.speech.extra.EXTRA_ADDITIONAL_LANGUAGES", new String[]{"en", "hi"});
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context.getPackageName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);
        intent.putExtra("android.speech.extra.PARTIAL_RESULTS", true);
        intent.putExtra("android.speech.extra.DICTATION_MODE", true);
        speechRecognizer.startListening(intent);
    }

    private void stopListen() {
        if (speechRecognizer != null) {
            speechRecognizer.stopListening();
            speechRecognizer.cancel();
            speechRecognizer.destroy();
        }
    }


    @Override
    public void onReadyForSpeech(Bundle bundle) {

    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float v) {

    }

    @Override
    public void onBufferReceived(byte[] bytes) {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onError(int i) {
        stopListen();
        startListening();
    }

    @Override
    public void onResults(Bundle results) {
        stopListen();
        if (results == null) {
            startListening();
            return;
        }
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        if (matches != null && matches.size() > 0) {
            String output = matches.get(0);
            Log.d("Tabeesh", "onResults: " + output);
            pharseT.append(output+" ");
        }
        startListening();
    }

    @Override
    public void onPartialResults(Bundle bundle) {

    }

    @Override
    public void onEvent(int i, Bundle bundle) {

    }

    /*public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        historyList.clear();
        if (charText.length() == 0) {
            historyList.addAll(arraylist);
        } else {
            for (Message wp : arraylist) {

                //String name = wp.getManf_name() + wp.getModel_name();
                String name2 = wp.getPhoneNumber();
                String name3 = wp.getGuestName();

                if (wp.getPhoneNumber().toLowerCase(Locale.getDefault())
                        .contains(charText) || wp.getPhoneNumber().toLowerCase(Locale.getDefault())
                        .contains(charText) || name2.toLowerCase(Locale.getDefault())
                        .contains(charText) || wp.getGuestName().toLowerCase(Locale.getDefault())
                        .contains(charText) || name3.toLowerCase(Locale.getDefault())
                        .contains(charText)){
                    historyList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }*/
}
