package com.gsatechworld.linnaas.utils.history;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryResult {

    @SerializedName("verse_id")
    @Expose
    private String verse_id;

    @SerializedName("verse_name")
    @Expose
    private String verse_name;

    @SerializedName("read_count")
    @Expose
    private String read_count;

    @SerializedName("read_type")
    @Expose
    private String read_type;

    @SerializedName("points")
    @Expose
    private String points;

    @SerializedName("data")
    @Expose
    private String data;

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

    public String getRead_count() {
        return read_count;
    }

    public void setRead_count(String read_count) {
        this.read_count = read_count;
    }

    public String getRead_type() {
        return read_type;
    }

    public void setRead_type(String read_type) {
        this.read_type = read_type;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
