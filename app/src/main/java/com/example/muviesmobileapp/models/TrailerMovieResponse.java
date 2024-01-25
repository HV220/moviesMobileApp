package com.example.muviesmobileapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TrailerMovieResponse implements Serializable {
    @SerializedName("docs")
    private List<Doc> trailersMovieList;

    @Override
    public String toString() {
        return "TrailerMovieResponse{" +
                "trailersMovieList=" + trailersMovieList +
                '}';
    }

    public List<Doc> getTrailersMovieList() {
        return trailersMovieList;
    }

    public TrailerMovieResponse(List<Doc> trailersMovieList) {
        this.trailersMovieList = trailersMovieList;
    }
}
