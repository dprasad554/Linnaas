package com.gsatechworld.linnaas.utils.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileDetailResp {

    private String user_id;

    public ProfileDetailResp(String user_id){
        this.user_id = user_id;
    }

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private ProfileDetailResult profileDetailResult;

    @SerializedName("total_wallet_points")
    @Expose
    private String wallet_pts;

    @SerializedName("total_reads")
    @Expose
    private String total_reads;



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

    public ProfileDetailResult getProfileDetailResult() {
        return profileDetailResult;
    }

    public void setProfileDetailResult(ProfileDetailResult profileDetailResult) {
        this.profileDetailResult = profileDetailResult;
    }

    public String getTotal_reads() {
        return total_reads;
    }

    public void setTotal_reads(String total_reads) {
        this.total_reads = total_reads;
    }
}
