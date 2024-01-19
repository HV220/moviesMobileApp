package com.example.muviesmobileapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movies=" + movies +
                '}';
    }

    @SerializedName("docs")
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public MovieResponse(List<Movie> movies) {
        this.movies = movies;
    }
}
