package com.gsatechworld.linnaas.utils.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResp {

    private String search;
    private String language;

    public SearchResp(String search,String language)
    {
        this.search = search;
        this.language = language;
    }
    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private List<SearchResult> result;

    public String getSearch() {
        return search;
    }

    public void setSearch(String searchTerm) {
        this.search = search;
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

    public List<SearchResult> getResult() {
        return result;
    }

    public void setResult(List<SearchResult> result) {
        this.result = result;
    }
}
