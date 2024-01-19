package com.example.muviesmobileapp.models;

import com.google.gson.annotations.SerializedName;

public class Rating {

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
}
