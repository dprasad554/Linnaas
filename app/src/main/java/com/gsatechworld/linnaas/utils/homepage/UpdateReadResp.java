package com.gsatechworld.linnaas.utils.homepage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateReadResp {

    private String user_id;
    private String verse_id;
    private String read_type;
    private String read_count;

    public UpdateReadResp(String user_id,String verse_id,String read_type,String read_count){
        this.read_count = read_count;
        this.read_type = read_type;
        this.user_id = user_id;
        this.verse_id = verse_id;
    }
     @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("total_wallet_pts")
    @Expose
    private String wallet_pts;

    public String getWallet_pts() {
        return wallet_pts;
    }

    public void setWallet_pts(String wallet_pts) {
        this.wallet_pts = wallet_pts;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getVerse_id() {
        return verse_id;
    }

    public void setVerse_id(String verse_id) {
        this.verse_id = verse_id;
    }

    public String getRead_type() {
        return read_type;
    }

    public void setRead_type(String read_type) {
        this.read_type = read_type;
    }

    public String getRead_count() {
        return read_count;
    }

    public void setRead_count(String read_count) {
        this.read_count = read_count;
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
}
