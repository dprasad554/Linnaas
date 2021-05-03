package com.gsatechworld.linnaas.utils.wallet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Walletresp {

    private String user_id;

    public Walletresp(String user_id){
        this.user_id = user_id;
    }

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("total_wallet_points")
    @Expose
    private String total_wallet_points;

    @SerializedName("data")
    @Expose
    private List<WalletResult> resultList;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<WalletResult> getResultList() {
        return resultList;
    }

    public void setResultList(List<WalletResult> resultList) {
        this.resultList = resultList;
    }

    public String getTotal_wallet_points() {
        return total_wallet_points;
    }

    public void setTotal_wallet_points(String total_wallet_points) {
        this.total_wallet_points = total_wallet_points;
    }
}
