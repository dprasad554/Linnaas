package com.gsatechworld.linnaas.utils.search;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.linnaas.core.network.NetworkAPI;
import com.gsatechworld.linnaas.utils.history.HistoryResp;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SearchRepository {

    private MutableLiveData<SearchResp> searchRespMutableLiveData = new MutableLiveData<>();
    private NetworkAPI networkAPI;
    @Inject
    public SearchRepository(NetworkAPI apiService) {
        this.networkAPI = apiService;
    }

    public LiveData<SearchResp> getSearchResult(SearchResp searchResp){

        Single<Response<SearchResp>> responseSingle =
                networkAPI.getSearchResult(searchResp);

        responseSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<SearchResp>>() {
                    @Override
                    public void onSuccess(Response<SearchResp> respResponse) {
                        try {
                            searchRespMutableLiveData.postValue(respResponse.body());

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
        return searchRespMutableLiveData;
    }

}
