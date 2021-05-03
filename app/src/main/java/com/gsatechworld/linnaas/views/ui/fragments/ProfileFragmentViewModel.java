package com.gsatechworld.linnaas.views.ui.fragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.linnaas.utils.history.HistoryRepository;
import com.gsatechworld.linnaas.utils.history.HistoryResp;
import com.gsatechworld.linnaas.utils.homepage.HomeRepository;
import com.gsatechworld.linnaas.utils.profile.ProfileDetailResp;

import javax.inject.Inject;

public class ProfileFragmentViewModel extends ViewModel {
    private HomeRepository homeRepository;
    private LiveData<ProfileDetailResp> profileDetailRespLiveData;
    @Inject
    public ProfileFragmentViewModel(HomeRepository homeRepository) {
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
    public LiveData<ProfileDetailResp> getProfile(ProfileDetailResp profileDetailResp){
        profileDetailRespLiveData = new MutableLiveData<>();
        profileDetailRespLiveData = homeRepository.getProfile(profileDetailResp);
        return profileDetailRespLiveData;
    }
}