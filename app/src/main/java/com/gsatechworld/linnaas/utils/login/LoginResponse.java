package com.gsatechworld.linnaas.utils.login;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {


    private LoginResultResp loginResultResp;

    public LoginResponse(LoginResultResp loginResultResp){
        this.loginResultResp = loginResultResp;
    }

    public LoginResultResp getLoginResultResp() {
        return loginResultResp;
    }

    public void setLoginResultResp(LoginResultResp loginResultResp) {
        this.loginResultResp = loginResultResp;
    }
}
