package com.example.muviesmobileapp.models.api.responses.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rating implements Serializable {

    @Override
    public String toString() {
        return "Rating{" +
                "rating='" + rating + '\'' +
                '}';
    }

    public Rating(double rating) {
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    @SerializedName("kp")
    private double rating;

    public void setRating(double rating) {
        this.rating = rating;
    }
}
