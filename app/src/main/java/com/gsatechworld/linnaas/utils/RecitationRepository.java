package com.gsatechworld.linnaas.utils;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.linnaas.core.network.NetworkAPI;
import com.gsatechworld.linnaas.utils.homepage.UpdateReadResp;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class RecitationRepository {

    private MutableLiveData<UpdateReadResp> updateReadRespMutableLiveData = new MutableLiveData<>();
    private NetworkAPI networkAPI;

    @Inject
    public RecitationRepository(NetworkAPI apiService) {
        this.networkAPI = apiService;
    }

    public LiveData<UpdateReadResp> updateReadCount(UpdateReadResp updateReadResp){

        Single<Response<UpdateReadResp>> responseSingle =
                networkAPI.updateReadCount(updateReadResp);

        responseSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<UpdateReadResp>>() {
                    @Override
                    public void onSuccess(Response<UpdateReadResp> updateReadRespResponse) {
                        try {
                            Log.d("response",updateReadRespResponse.message());
                            updateReadRespMutableLiveData.postValue(updateReadRespResponse.body());
                            //homeRespLiveData.postValue(respResponse.body());

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("error",e.getLocalizedMessage());
                    }
                });
        return updateReadRespMutableLiveData;
    }

}
