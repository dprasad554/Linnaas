package com.gsatechworld.linnaas.utils.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResultResp {

    private String mobile_number;
    private String country_code;

    public LoginResultResp(String country_code,String mobile_number){
        this.mobile_number = mobile_number;
        this.country_code = country_code;
    }

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("message")
    @Expose
    private String message;

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }
}
