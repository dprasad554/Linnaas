package com.gsatechworld.linnaas.views.ui.fragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.linnaas.utils.history.HistoryRepository;
import com.gsatechworld.linnaas.utils.history.HistoryResp;
import com.gsatechworld.linnaas.utils.homepage.HomeRepository;
import com.gsatechworld.linnaas.utils.homepage.HomeResp;

import javax.inject.Inject;

public class HistoryFragmentViewModel extends ViewModel {
    private HistoryRepository historyRepository;
    private LiveData<HistoryResp> historyRespLiveData;
    @Inject
    public HistoryFragmentViewModel(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    /*private AppointmentUpcomingFragmentRepository repository;

    @Inject
    public HomeFragmentViewModel(AppointmentUpcomingFragmentRepository repository) {
        this.repository = repository;
    }


    LiveData<AppointmentUpcomingResp> getAppoints(String userId) {
        return repository.getAppoints(userId);
    }*/

    public LiveData<HistoryResp> getHistory (HistoryResp historyResp) {
        historyRespLiveData = new MutableLiveData<>();
        historyRespLiveData = historyRepository.getHomeData(historyResp);
        return historyRespLiveData;
    }
}