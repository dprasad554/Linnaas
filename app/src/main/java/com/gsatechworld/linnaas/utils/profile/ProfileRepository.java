package com.gsatechworld.linnaas.utils.profile;

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

public class ProfileRepository {

    private NetworkAPI apiService;
    private MutableLiveData<ProfileResult> profileResultMutableLiveData = new MutableLiveData<>();

    @Inject
    public ProfileRepository(NetworkAPI apiService) {
        this.apiService = apiService;
    }

    public LiveData<ProfileResult> updateProfile(ProfileResp profileResp){

        Single<Response<ProfileResp>> responseSingle =
                apiService.updateprofile(profileResp);

        responseSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<ProfileResp>>() {
                    @Override
                    public void onSuccess(Response<ProfileResp> respResponse) {
                        try {
                            Log.d("response",respResponse.message());
                            profileResultMutableLiveData.postValue(
                                    new ProfileResult(respResponse.body()));

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
        return profileResultMutableLiveData;
    }
}
