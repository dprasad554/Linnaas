package com.gsatechworld.linnaas.utils.verifyOtp;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.linnaas.core.network.NetworkAPI;
import com.gsatechworld.linnaas.utils.login.LoginResponse;
import com.gsatechworld.linnaas.utils.login.LoginResultResp;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class VerifyOtpRepository {

    private NetworkAPI apiService;
    private MutableLiveData<VerifyOtpResult> verifyOtpResultMutableLiveData = new MutableLiveData<>();

    @Inject
    public VerifyOtpRepository(NetworkAPI apiService) {
        this.apiService = apiService;
    }

    public LiveData<VerifyOtpResult> getOtpCheck(VerifyOtpResp verifyOtpResp){

        Single<Response<VerifyOtpResp>> responseSingle =
                apiService.verifyotp(verifyOtpResp);

        responseSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<VerifyOtpResp>>() {
                    @Override
                            public void onSuccess(Response<VerifyOtpResp> respResponse) {
                        try {
                            Log.d("response",respResponse.message());
                            verifyOtpResultMutableLiveData.postValue(
                                    new VerifyOtpResult(respResponse.body()));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("error",e.getLocalizedMessage());
//                        loginResultRespMutableLiveData.postValue(
//                                new LoginResponse( e.getLocalizedMessage()));
                    }
                });
        return verifyOtpResultMutableLiveData;
    }
}
