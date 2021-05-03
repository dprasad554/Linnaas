package com.gsatechworld.linnaas.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.linnaas.utils.RecitationRepository;
import com.gsatechworld.linnaas.utils.homepage.UpdateReadResp;

import javax.inject.Inject;

public class RecitationViewModel extends ViewModel {

    RecitationRepository recitationRepository;
    LiveData<UpdateReadResp> updateReadRespLiveData;

    @Inject
    public RecitationViewModel(RecitationRepository recitationRepository){
        this.recitationRepository = recitationRepository;
    }

    public LiveData<UpdateReadResp> updateReadCount(UpdateReadResp updateReadResp){
        updateReadRespLiveData = new MutableLiveData<>();
        updateReadRespLiveData = recitationRepository.updateReadCount(updateReadResp);
        return updateReadRespLiveData;
    }
}
