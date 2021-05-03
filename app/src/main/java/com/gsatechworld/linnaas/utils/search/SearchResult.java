package com.gsatechworld.linnaas.utils.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchResult {

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
