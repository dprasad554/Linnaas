package com.gsatechworld.linnaas.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.linnaas.utils.login.LoginResponse;
import com.gsatechworld.linnaas.utils.login.LoginResultResp;
import com.gsatechworld.linnaas.utils.verifyOtp.VerifyOtpRepository;
import com.gsatechworld.linnaas.utils.verifyOtp.VerifyOtpResp;
import com.gsatechworld.linnaas.utils.verifyOtp.VerifyOtpResult;

import javax.inject.Inject;

public class VerifyOtpModel extends ViewModel {

    private VerifyOtpRepository verifyOtpRepository;
    private LiveData<VerifyOtpResult> verifyOtpResultLiveData;
    @Inject
    public VerifyOtpModel(VerifyOtpRepository verifyOtpRepository) {
        this.verifyOtpRepository = verifyOtpRepository;
    }
    public LiveData<VerifyOtpResult> sendOtp (VerifyOtpResp verifyOtpResp) {
        verifyOtpResultLiveData = new MutableLiveData<>();
        verifyOtpResultLiveData = verifyOtpRepository.getOtpCheck(verifyOtpResp);
        return verifyOtpResultLiveData;
    }
}
