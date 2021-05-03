package com.gsatechworld.linnaas.views.ui.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.linnaas.R;
import com.gsatechworld.linnaas.RecitationActivity;
import com.gsatechworld.linnaas.databinding.FragmentHomeBinding;
import com.gsatechworld.linnaas.utils.homepage.HomeData;
import com.gsatechworld.linnaas.utils.homepage.HomeRepository;
import com.gsatechworld.linnaas.utils.homepage.HomeResp;
import com.gsatechworld.linnaas.utils.homepage.HomeResult;
import com.gsatechworld.linnaas.utils.homepage.UpdateReadResp;
import com.gsatechworld.linnaas.views.base.BaseFragment;
import com.skyfishjy.library.RippleBackground;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;

//import static android.databinding.DataBindingUtil.inflate;
import dmax.dialog.SpotsDialog;

import static androidx.databinding.DataBindingUtil.inflate;

public class HomeFragment extends BaseFragment<HomeFragmentViewModel> {

    @Inject
    @Named("HomeFragment")
    ViewModelProvider.Factory factory;

    private HomeFragmentViewModel viewModel;
    private FragmentHomeBinding binding;

    Dialog popupP;
    Dialog updatePopup;
    Dialog recordPopup;
    private SpeechRecognizer speechRecognizer;
    Dialog successPopup;
    String finalOutput = "";
    TextView pharseT;
    TabeeshAdapter tabeeshAdapter;
    List<HomeData> listData = new ArrayList<>();
    private AlertDialog progressDialog;
    SharedPreferences preferences;

    boolean permissionGranted;
   // List<String> listData = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        //View view = inflater.inflate(R.layout.fragment_home_, container, false);
        binding = inflate(inflater, R.layout.fragment_home_, container, false);

        progressDialog = new SpotsDialog(getContext(), R.style.Custom);
        progressDialog.show();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        binding.verseRv.setLayoutManager(linearLayoutManager);

        tabeeshAdapter = new TabeeshAdapter();
        binding.verseRv.setAdapter(tabeeshAdapter);

        return binding.getRoot();
    }

    private void getData(){
        preferences = getActivity().getSharedPreferences("logindata",0);
        String lang1 = preferences.getString("lang","en");
        String lang = null;
        if(lang1.equals("en")){
            lang = ""+"eng";
        }
        else {
            lang = ""+"arb";
        }
        viewModel.homeData(lang).observe(this, new Observer<HomeResp>() {
            @Override
            public void onChanged(HomeResp homeResult) {

               progressDialog.dismiss();
                List<HomeResp.HomeData> homeDataList = new ArrayList<>();
                homeDataList.addAll(homeResult.getData());
                //Toast.makeText(getActivity(),"hello",Toast.LENGTH_SHORT).show();
                //Toast.makeText(getActivity(),String.valueOf(homeResult.getData().get(0).getVerse_name()),Toast.LENGTH_SHORT).show();
//                //if(homeResp.getData() != null) {
//                    List<String> verse_name = new ArrayList<>();
//                    List<String> verse_id = new ArrayList<>();
//                    for(int i = 0;i < homeResult.size();i++){
//                        verse_name.add(homeResult.get(i).getVerse_name());
//                        verse_id.add(homeResult.get(i).getVerse_id());
//                    }
////                List<String> list = new ArrayList<>();
////                for(HomeData data : homeDataList){
////                    list.add(data.getVerse_name());
//                    //}
////                if(listData != null) {
////                    listData.clear();
////                }
//                    //listData.addAll(homebinding.searchText.getQuery().toString()DataList);
////
////                    Toast.makeText(getActivity(),homeResult.getHomeResp().getMessage(),Toast.LENGTH_SHORT).show();
                    tabeeshAdapter.setData(homeDataList);
                //}

            }
        });
    }


    @Override
    public HomeFragmentViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(HomeFragmentViewModel.class);

        return viewModel;
    }

    public class TabeeshAdapter extends RecyclerView.Adapter implements RecognitionListener {

        private List<HomeResp.HomeData> listData = new ArrayList<>();
        private List<String> verse_name = new ArrayList<>();
        private List<String> verse_id = new ArrayList<>();
        public TabeeshAdapter() {

        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.verse_list_, parent, false);
            return new TabeeshAdapter.ListViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
            ((TabeeshAdapter.ListViewHolder) holder).bindView(position);
            ((ListViewHolder) holder).tb_item.setText(listData.get(position).getVerse_name());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    InitializePopup();
                    initializePopup(listData.get(position).getVerse_name(),listData.get(position).getVerse_id());
                }
            });
        }

        @Override
        public int getItemCount() {
                return listData.size();
        }

        private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView tb_item;
            public ListViewHolder(@NonNull View itemView) {
                super(itemView);
                tb_item = itemView.findViewById(R.id.tb_item);

            }

            public void bindView(int position) {
            }

            @Override
            public void onClick(View view) {
            }
        }

        public void setData(List<HomeResp.HomeData> list)
        {
            listData.addAll(list);
            notifyDataSetChanged();
        }
        private void InitializeUpdatePopup() {
            updatePopup = new Dialog(getActivity());
            updatePopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
            updatePopup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            updatePopup.setContentView(R.layout.popup_manual_update_);
            updatePopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            updatePopup.setCancelable(true);
            updatePopup.setCanceledOnTouchOutside(true);
        }

        private void initializeUpdatePopup(final String verse_id) {
            updatePopup.setContentView(R.layout.popup_manual_update_);
            updatePopup.setCancelable(true);
            updatePopup.setCanceledOnTouchOutside(true);
            updatePopup.show();

            final EditText etCount = updatePopup.findViewById(R.id.et_count);
            final CardView cvUpdate = updatePopup.findViewById(R.id.cv_update);
            final TextView txt_inc_count = updatePopup.findViewById(R.id.txt_increase_count);

            int width = getActivity().getResources().getDisplayMetrics().widthPixels - 100;
            int height = getActivity().getResources().getDisplayMetrics().heightPixels - 250;
            updatePopup.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            updatePopup.getWindow().setGravity(Gravity.CENTER);

            txt_inc_count.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = Integer.parseInt(etCount.getText().toString());
                    i += 10;
                    etCount.setText(String.valueOf(i));
                }
            });

            cvUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!etCount.getText().toString().equals("") || !etCount.getText().toString().isEmpty()){
                        if (!etCount.getText().toString().equals("0")){
                            updatePopup.dismiss();
                            progressDialog.show();
                            Update("1",etCount.getText().toString(),verse_id);
                        } else {
                            Toast.makeText(getActivity(), "Count can not be 0(zero)", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Count can not be empty!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            updatePopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            updatePopup.setCancelable(true);
            updatePopup.setCanceledOnTouchOutside(true);
            updatePopup.show();
        }

        private void InitializePopup() {
            popupP = new Dialog(getActivity());
            popupP.requestWindowFeature(Window.FEATURE_NO_TITLE);
            popupP.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            popupP.setContentView(R.layout.popup_choose_);
            popupP.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            popupP.setCancelable(true);
            popupP.setCanceledOnTouchOutside(true);
        }

        private void initializePopup(final String tabeesh,final String tabeesh_id) {
            popupP.setContentView(R.layout.popup_choose_);
            popupP.setCancelable(true);
            popupP.setCanceledOnTouchOutside(true);
            popupP.show();

            final CardView cvManual = popupP.findViewById(R.id.cv_manual);
            final CardView cvRecord = popupP.findViewById(R.id.cv_record);

            int width = getActivity().getResources().getDisplayMetrics().widthPixels - 100;
            int height = getActivity().getResources().getDisplayMetrics().heightPixels - 250;
            popupP.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            popupP.getWindow().setGravity(Gravity.CENTER);


            cvManual.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    popupP.dismiss();
//                    InitializeUpdatePopup();
//                    initializeUpdatePopup(tabeesh_id);
                    Intent intent = new Intent(getActivity(),RecitationActivity.class);
                    intent.putExtra("verse_id",tabeesh_id);
                    intent.putExtra("verse_name",tabeesh);
                    startActivity(intent);
                /*InitializeSuccessPopup();
                initializeSuccessPopup("Success");*/
                }
            });
            cvRecord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popupP.dismiss();
                    preferences = getContext().getSharedPreferences("logindata",0);
                    String permission = preferences.getString("audiopermission","1");
                    if(permission.equals("0")) {
                        askAudioPermission();
                    }

                    InitializeRecordPopup();
                    initializeRecordPopup(tabeesh,tabeesh_id);

                }
            });


            popupP.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            popupP.setCancelable(true);
            popupP.setCanceledOnTouchOutside(true);
            popupP.show();
        }

        private void InitializeRecordPopup() {
            recordPopup = new Dialog(getActivity());
            recordPopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
            recordPopup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            recordPopup.setContentView(R.layout.popup_record_);
            recordPopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            recordPopup.setCancelable(true);
            recordPopup.setCanceledOnTouchOutside(true);
        }

        private void initializeRecordPopup(final String tabeesh,final String tabeesh_id) {
            recordPopup.setContentView(R.layout.popup_record_);
            recordPopup.setCancelable(true);
            recordPopup.setCanceledOnTouchOutside(true);
            recordPopup.show();

            final ImageView stRecord = recordPopup.findViewById(R.id.st_rec);
            final ImageView stStop = recordPopup.findViewById(R.id.st_stop);
            final TextView tPhrase = recordPopup.findViewById(R.id.tPhrase);
            final RippleBackground rippleBackground=(RippleBackground)recordPopup.findViewById(R.id.rp_animation);
            pharseT = recordPopup.findViewById(R.id.pharseT);

            String phrase = "Phrase " + tabeesh;

            tPhrase.setText(phrase);


//        final TextView view_cart = (TextView) recordPopup.findViewById(R.id.view_cart);
//        final ImageView view_cart_img =  recordPopup.findViewById(R.id.view_cart_img);


            int width = getActivity().getResources().getDisplayMetrics().widthPixels - 100;
            int height = getActivity().getResources().getDisplayMetrics().heightPixels - 250;
            recordPopup.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            recordPopup.getWindow().setGravity(Gravity.CENTER);


            stRecord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pharseT.setText("");
                    rippleBackground.startRippleAnimation();
                    startListening();

                /*InitializeSuccessPopup();
                initializeSuccessPopup("Success");*/
                }
            });
            stStop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recordPopup.dismiss();
                    rippleBackground.stopRippleAnimation();
                    stopListen();
                    if (pharseT.getText().toString().trim().toLowerCase().equalsIgnoreCase(tabeesh)){
                        InitializeSuccessPopup();
                        initializeSuccessPopup("Success",tabeesh_id);
                    } else {
                        InitializeSuccessPopup();
                        initializeSuccessPopup("Unsuccess",tabeesh_id);
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
            successPopup = new Dialog(getActivity());
            successPopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
            successPopup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            successPopup.setContentView(R.layout.popup_success_);
            successPopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            successPopup.setCancelable(true);
            successPopup.setCanceledOnTouchOutside(true);
        }

        private void initializeSuccessPopup(String status,String verse_id) {
            successPopup.setContentView(R.layout.popup_success_);
            successPopup.setCancelable(true);
            successPopup.setCanceledOnTouchOutside(true);
            successPopup.show();

            final ImageView iv_suc = successPopup.findViewById(R.id.iv_suc);
            final TextView tv_suc = successPopup.findViewById(R.id.tv_suc);
            final Button bt_cls = successPopup.findViewById(R.id.bt_cls);


            if (status.equals("Success")) {
                Update("2","10",verse_id);
                iv_suc.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.accept));
                tv_suc.setText(getActivity().getResources().getString(R.string.successful_completion));
                bt_cls.setText("Close");
                bt_cls.setBackground(getActivity().getResources().getDrawable(R.drawable.bt_g_gradient));
            } else {
                iv_suc.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.wrong));
                tv_suc.setText(getActivity().getResources().getString(R.string.unsuccesful));
                bt_cls.setText("Try Again");
                bt_cls.setBackground(getActivity().getResources().getDrawable(R.drawable.bt_gradient));
            }

            bt_cls.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(successPopup.isShowing()){
                        successPopup.dismiss();
                    }
                }
            });
//        final TextView view_cart = (TextView) recordPopup.findViewById(R.id.view_cart);
//        final ImageView view_cart_img =  recordPopup.findViewById(R.id.view_cart_img);


            int width = getActivity().getResources().getDisplayMetrics().widthPixels - 100;
            int height = getActivity().getResources().getDisplayMetrics().heightPixels - 250;
            successPopup.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            successPopup.getWindow().setGravity(Gravity.CENTER);


            successPopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            successPopup.setCancelable(true);
            successPopup.setCanceledOnTouchOutside(true);
            successPopup.show();
        }


        private void startListening() {
            // start listening
            String value = null;
            preferences = getContext().getSharedPreferences("logindata",0);
            value = "" + preferences.getString("lang","en");
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(getActivity());
            speechRecognizer.setRecognitionListener(this);
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, value);
            intent.putExtra("android.speech.extra.EXTRA_ADDITIONAL_LANGUAGES", new String[]{value, "hi"});
            intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getActivity().getPackageName());
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
            //Log.i("output",matches.get(0));
            if (matches != null && matches.size() > 0) {
                String output = matches.get(0);
                pharseT.append(output+" ");

                //pharseT.append(output + " ");
            }
            startListening();
        }

        @Override
        public void onPartialResults(Bundle bundle) {

        }

        @Override
        public void onEvent(int i, Bundle bundle) {

        }

    }
    public static String convertNumberEnglishToArabic(String input) {

        String value = "";

        for (char character : input.toCharArray()) {

            String str = "";
            int ascii = (int) character;

            if ((ascii >= 97 && ascii <= 122)
                || (ascii >= 65 && ascii <= 90))
            {
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
    private void Update(String read_type,String read_count,String verse_id){
        preferences = getActivity().getSharedPreferences("logindata",0);
        String id = preferences.getString("id","1");
        viewModel.updateReadCount(new UpdateReadResp(id,verse_id,read_type,read_count)).observe(this,
                new Observer<UpdateReadResp>() {
                    @Override
                    public void onChanged(UpdateReadResp updateReadResp) {

                        SharedPreferences preferences = getActivity().getSharedPreferences("logindata",0);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("wallet_pts",updateReadResp.getWallet_pts());
                        editor.apply();
                        if(progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }
                    }
                }
        );
    }

    private void askAudioPermission() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    1);
        } else
            permissionGranted = true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1 && grantResults.length > 0){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                permissionGranted = true;
            }
        }
    }
}