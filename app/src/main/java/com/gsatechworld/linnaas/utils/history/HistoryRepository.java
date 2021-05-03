package com.gsatechworld.linnaas.utils.history;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.linnaas.core.network.NetworkAPI;
import com.gsatechworld.linnaas.utils.homepage.HomeResp;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class HistoryRepository {
    private MutableLiveData<HistoryResp> historyRespMutableLiveData = new MutableLiveData<>();
    private NetworkAPI networkAPI;
    @Inject
    public HistoryRepository(NetworkAPI apiService) {
        this.networkAPI = apiService;
    }

    public LiveData<HistoryResp> getHomeData(HistoryResp historyResp){

        Single<Response<HistoryResp>> responseSingle =
                networkAPI.getHistory(historyResp);

        responseSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<HistoryResp>>() {
                    @Override
                    public void onSuccess(Response<HistoryResp> respResponse) {
                        try {
                            historyRespMutableLiveData.postValue(respResponse.body());

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
        return historyRespMutableLiveData;
    }
}
