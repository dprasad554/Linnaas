package com.gsatechworld.linnaas.utils.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.linnaas.core.network.NetworkAPI;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class LoginRepository {

    private NetworkAPI apiService;
    private MutableLiveData<LoginResponse> loginResultRespMutableLiveData = new MutableLiveData<>();
//
    @Inject
    public LoginRepository(NetworkAPI apiService) {
        this.apiService = apiService;
    }
//
    public LiveData<LoginResponse> getLoginStatus(LoginResultResp loginResultResp){

        Single<Response<LoginResultResp>> responseSingle =
                apiService.login(loginResultResp);

        responseSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<LoginResultResp>>() {
                    @Override
                    public void onSuccess(Response<LoginResultResp> respResponse) {
                        try {
                            Log.d("response",respResponse.message());
                            loginResultRespMutableLiveData.postValue(
                                    new LoginResponse(respResponse.body()));

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
        return loginResultRespMutableLiveData;
    }

}
