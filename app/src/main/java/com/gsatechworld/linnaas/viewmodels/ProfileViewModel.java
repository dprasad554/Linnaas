package com.gsatechworld.linnaas.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.linnaas.utils.profile.ProfileRepository;
import com.gsatechworld.linnaas.utils.profile.ProfileResp;
import com.gsatechworld.linnaas.utils.profile.ProfileResult;
import com.gsatechworld.linnaas.utils.verifyOtp.VerifyOtpResp;
import com.gsatechworld.linnaas.utils.verifyOtp.VerifyOtpResult;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {

    private ProfileRepository profileRepository;
    private LiveData<ProfileResult> profileResultLiveData;
    @Inject
    public ProfileViewModel(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }
    public LiveData<ProfileResult> updateProfile (ProfileResp profileResp) {
        profileResultLiveData = new MutableLiveData<>();
        profileResultLiveData = profileRepository.updateProfile(profileResp);
        return profileResultLiveData;
    }
}
