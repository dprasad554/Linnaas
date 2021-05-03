package com.gsatechworld.linnaas.utils.wallet;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.linnaas.core.network.NetworkAPI;
import com.gsatechworld.linnaas.utils.verifyOtp.VerifyOtpResp;
import com.gsatechworld.linnaas.utils.verifyOtp.VerifyOtpResult;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class WalletHistoryRepository {

    private NetworkAPI apiService;
    private MutableLiveData<Walletresp> walletrespMutableLiveData = new MutableLiveData<>();

    @Inject
    public WalletHistoryRepository(NetworkAPI apiService) {
        this.apiService = apiService;
    }

    public LiveData<Walletresp> getWalletHistory(Walletresp walletresp){

        Single<Response<Walletresp>> responseSingle =
                apiService.getWalletHistory(walletresp);

        responseSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<Walletresp>>() {
                    @Override
                    public void onSuccess(Response<Walletresp> respResponse) {
                        try {
                            Log.d("response",respResponse.message());
                            walletrespMutableLiveData.postValue(
                                    respResponse.body());

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
        return walletrespMutableLiveData;
    }
}
