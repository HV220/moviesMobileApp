package com.example.muviesmobileapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Poster implements Serializable {
    public String getPreviewUrl() {
        return previewUrl;
    }

    @SerializedName("previewUrl")
    private String previewUrl;

    @Override
    public String toString() {
        return "Poster{" +
                "previewUrl='" + previewUrl + '\'' +
                '}';
    }

    public Poster(String url) {
        this.previewUrl = url;
    }
}
