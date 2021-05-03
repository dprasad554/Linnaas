package com.gsatechworld.linnaas.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.linnaas.utils.history.HistoryRepository;
import com.gsatechworld.linnaas.utils.history.HistoryResp;
import com.gsatechworld.linnaas.utils.homepage.HomeRepository;
import com.gsatechworld.linnaas.utils.homepage.HomeResp;
import com.gsatechworld.linnaas.utils.homepage.UpdateReadResp;
import com.gsatechworld.linnaas.utils.profile.ProfileDetailResp;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {
    private HomeRepository homeRepository;
    private LiveData<HomeResp> homeRespLiveData;
    private LiveData<UpdateReadResp> updateReadRespLiveData;
    private HistoryRepository historyRepository;
    private LiveData<HistoryResp> historyRespLiveData;
    private LiveData<ProfileDetailResp> profileDetailRespLiveData;

    @Inject
    public HomeViewModel(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

//    public LiveData<HomeResp> homeData () {
//        homeRespLiveData = new MutableLiveData<>();
//        homeRespLiveData = homeRepository.getHomeData();
//        return homeRespLiveData;
//    }

    public LiveData<UpdateReadResp> updateReadCount(UpdateReadResp updateReadResp){
        updateReadRespLiveData = new MutableLiveData<>();
        updateReadRespLiveData = homeRepository.updateReadCount(updateReadResp);
        return updateReadRespLiveData;
    }
    public LiveData<HistoryResp> getHistory (HistoryResp historyResp) {
        historyRespLiveData = new MutableLiveData<>();
        historyRespLiveData = homeRepository.getHistory(historyResp);
        return historyRespLiveData;
    }

    public LiveData<ProfileDetailResp> getProfile(ProfileDetailResp profileDetailResp){
        profileDetailRespLiveData = new MutableLiveData<>();
        profileDetailRespLiveData = homeRepository.getProfile(profileDetailResp);
        return profileDetailRespLiveData;
    }
}
