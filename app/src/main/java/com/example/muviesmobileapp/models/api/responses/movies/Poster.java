package com.example.muviesmobileapp.models.api.responses.movies;

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

    public Poster() {
    }

    public Poster(String url) {
        this.previewUrl = url;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }
}
