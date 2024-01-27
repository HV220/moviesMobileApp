package com.example.muviesmobileapp.models.api.responses.aboutmovie.trailers;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TrailerResponse implements Serializable {
    @SerializedName("docs")
    private List<Doc> trailersMovieList;

    @Override
    public String toString() {
        return "TrailerResponse{" +
                "trailersMovieList=" + trailersMovieList +
                '}';
    }

    public List<Doc> getTrailersMovieList() {
        return trailersMovieList;
    }

    public TrailerResponse(List<Doc> trailersMovieList) {
        this.trailersMovieList = trailersMovieList;
    }
}
