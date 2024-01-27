package com.example.muviesmobileapp.models.api.responses.aboutmovie.trailers;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Trailer implements Serializable {
    @SerializedName("url")
    private String urlTrailerMovie;
    @SerializedName("name")
    private String nameTrailer;

    public String getNameTrailer() {
        return nameTrailer;
    }

    public String getUrlTrailerMovie() {
        return urlTrailerMovie;
    }

    @Override
    public String toString() {
        return "Trailer{" +
                "urlTrailerMovie='" + urlTrailerMovie + '\'' +
                ", nameTrailer='" + nameTrailer + '\'' +
                '}';
    }

    public Trailer(String urlTrailerMovie, String nameTrailer) {
        this.urlTrailerMovie = urlTrailerMovie;
        this.nameTrailer = nameTrailer;
    }


}
