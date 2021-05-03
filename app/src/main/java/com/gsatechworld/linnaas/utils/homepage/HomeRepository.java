package com.gsatechworld.linnaas.utils.homepage;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.linnaas.core.network.NetworkAPI;
import com.gsatechworld.linnaas.utils.history.HistoryResp;
import com.gsatechworld.linnaas.utils.login.LoginResponse;
import com.gsatechworld.linnaas.utils.login.LoginResultResp;
import com.gsatechworld.linnaas.utils.profile.ProfileDetailResp;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {

    private MutableLiveData<HomeResp> homeRespLiveData = new MutableLiveData<>();
    private MutableLiveData<UpdateReadResp> updateReadRespMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<HistoryResp> historyRespMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ProfileDetailResp> profileDetailRespMutableLiveData = new MutableLiveData<>();
    private NetworkAPI networkAPI;
    @Inject
    public HomeRepository(NetworkAPI apiService) {
        this.networkAPI = apiService;
    }

    public LiveData<HomeResp> getHomeData(String lang){

        Single<Response<HomeResp>> responseSingle =
                networkAPI.getverses(lang);

        responseSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<HomeResp>>() {
                    @Override
                    public void onSuccess(Response<HomeResp> respResponse) {
                        try {
                            homeRespLiveData.setValue(respResponse.body());
                            //homeRespLiveData.postValue(respResponse.body());

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
//        Call<HomeResp> call = networkAPI.getverses(lang);
//        call.enqueue(new Callback<HomeResp>() {
//            @Override
//            public void onResponse(Call<HomeResp> call, retrofit2.Response<HomeResp> response) {
//
//                //List<HomeResp.HomeData> res = response.body().getData();
//                homeRespLiveData.postValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<HomeResp> call, Throwable t) {
//
//            }
//
//
//        });
        return homeRespLiveData;
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
//                        loginResultRespMutableLiveData.postValue(
//                                new LoginResponse( e.getLocalizedMessage()));
                    }
                });
        return updateReadRespMutableLiveData;
    }

    public LiveData<HistoryResp> getHistory(HistoryResp historyResp){

        Single<Response<HistoryResp>> responseSingle =
                networkAPI.getHistory(historyResp);

        responseSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<HistoryResp>>() {
                    @Override
                    public void onSuccess(Response<HistoryResp> respResponse) {
                        try {
                            //Log.d("responseasdjalk",respResponse.body().getResults().get(0).getVerse_name());
                            historyRespMutableLiveData.postValue((respResponse.body()));

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

    public LiveData<ProfileDetailResp> getProfile(ProfileDetailResp profileDetailResp){

        Single<Response<ProfileDetailResp>> responseSingle =
                networkAPI.getProfile(profileDetailResp);

        responseSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<ProfileDetailResp>>() {
                    @Override
                    public void onSuccess(Response<ProfileDetailResp> respResponse) {
                        try {
                            Log.d("responseasdjalk",respResponse.message());
                            profileDetailRespMutableLiveData.postValue(respResponse.body());

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
        return profileDetailRespMutableLiveData;
    }
}
