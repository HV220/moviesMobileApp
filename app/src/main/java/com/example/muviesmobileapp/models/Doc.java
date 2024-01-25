package com.example.muviesmobileapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Doc implements Serializable {
    @SerializedName("videos")
    private Videos videos;

    @Override
    public String toString() {
        return "Doc{" +
                "videos=" + videos +
                '}';
    }

    public Videos getVideos() {
        return videos;
    }

    public Doc(Videos videos) {
        this.videos = videos;
    }
}
