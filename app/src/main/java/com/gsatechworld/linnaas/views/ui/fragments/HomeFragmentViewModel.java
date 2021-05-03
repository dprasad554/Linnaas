package com.gsatechworld.linnaas.views.ui.fragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.linnaas.utils.homepage.HomeData;
import com.gsatechworld.linnaas.utils.homepage.HomeRepository;
import com.gsatechworld.linnaas.utils.homepage.HomeResp;
import com.gsatechworld.linnaas.utils.homepage.HomeResult;
import com.gsatechworld.linnaas.utils.homepage.UpdateReadResp;
import com.gsatechworld.linnaas.utils.login.LoginResponse;
import com.gsatechworld.linnaas.utils.login.LoginResultResp;

import java.util.List;

import javax.inject.Inject;

public class HomeFragmentViewModel extends ViewModel {
    private HomeRepository homeRepository;
    private LiveData<HomeResp> homeRespLiveData;
    private LiveData<UpdateReadResp> updateReadRespLiveData;
    @Inject
    public HomeFragmentViewModel(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    /*private AppointmentUpcomingFragmentRepository repository;

    @Inject
    public HomeFragmentViewModel(AppointmentUpcomingFragmentRepository repository) {
        this.repository = repository;
    }


    LiveData<AppointmentUpcomingResp> getAppoints(String userId) {
        return repository.getAppoints(userId);
    }*/

    public LiveData<HomeResp> homeData (String lang) {
        homeRespLiveData = new MutableLiveData<>();
        homeRespLiveData = homeRepository.getHomeData(lang);
        return homeRespLiveData;
    }

    public LiveData<UpdateReadResp> updateReadCount(UpdateReadResp updateReadResp){
        updateReadRespLiveData = new MutableLiveData<>();
        updateReadRespLiveData = homeRepository.updateReadCount(updateReadResp);
        return updateReadRespLiveData;
    }

}