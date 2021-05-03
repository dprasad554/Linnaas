package com.gsatechworld.linnaas.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.linnaas.utils.login.LoginRepository;
import com.gsatechworld.linnaas.utils.login.LoginResponse;
import com.gsatechworld.linnaas.utils.login.LoginResultResp;

import javax.inject.Inject;

public class LoginViewModel extends ViewModel {
    private LoginRepository loginRepository;
    private LiveData<LoginResponse> loginResultRespLiveData;
    @Inject
    public LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }
    public LiveData<LoginResponse> isLoginSuccess (LoginResultResp loginResultResp) {
        loginResultRespLiveData = new MutableLiveData<>();
        loginResultRespLiveData = loginRepository.getLoginStatus(loginResultResp);
        return loginResultRespLiveData;
    }
}
