package com.gsatechworld.linnaas.utils.homepage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeResp {

//    private String language;
//
//    public HomeResp(String language){
//        this.language = language;
//    }


    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private List<HomeData> data;

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

    public List<HomeData> getData() {
        return data;
    }

    public void setData(List<HomeData> data) {
        this.data = data;
    }
    public class HomeData {
        @SerializedName("verse_id")
        @Expose
        private String verse_id;

        @SerializedName("verse_name")
        @Expose
        private String verse_name;

        public String getVerse_id() {
            return verse_id;
        }

        public void setVerse_id(String verse_id) {
            this.verse_id = verse_id;
        }

        public String getVerse_name() {
            return verse_name;
        }

        public void setVerse_name(String verse_name) {
            this.verse_name = verse_name;
        }
    }
}
