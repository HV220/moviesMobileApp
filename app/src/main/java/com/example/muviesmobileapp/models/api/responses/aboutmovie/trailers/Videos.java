package com.example.muviesmobileapp.models.api.responses.aboutmovie.trailers;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Videos implements Serializable {
    @SerializedName("trailers")
    private List<Trailer> trailersMovie;

    @Override
    public String toString() {
        return "Videos{" +
                "trailersMovie=" + trailersMovie +
                '}';
    }

    public Videos(List<Trailer> trailersMovie) {
        this.trailersMovie = trailersMovie;
    }

    public List<Trailer> getTrailersMovie() {
        return trailersMovie;
    }
}
