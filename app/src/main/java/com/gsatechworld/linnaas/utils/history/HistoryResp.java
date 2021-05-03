package com.gsatechworld.linnaas.utils.history;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HistoryResp {
    private String user_id;
    public HistoryResp(String user_id){
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

    @SerializedName("total_reads")
    @Expose
    private String total_reads;

    @SerializedName("data")
    @Expose
    private List<HistoryResult> results;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTotal_reads() {
        return total_reads;
    }

    public void setTotal_reads(String total_reads) {
        this.total_reads = total_reads;
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

    public String getTotal_wallet_points() {
        return total_wallet_points;
    }

    public void setTotal_wallet_points(String total_wallet_points) {
        this.total_wallet_points = total_wallet_points;
    }

    public List<HistoryResult> getResults() {
        return results;
    }

    public void setResults(List<HistoryResult> results) {
        this.results = results;
    }
}
