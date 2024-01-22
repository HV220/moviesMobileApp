package com.example.muviesmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.muviesmobileapp.models.Movie;

public class AboutMovieView extends AppCompatActivity {
    private final static String EXTRA_MOVIE = "movie";
    private ImageView pictureMovieAboutMovieActivity;
    private TextView titleMovieAboutMovieActivity;
    private TextView yearMovieAboutMovieActivity;
    private TextView descriptionMovieAboutMovieActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_movie_view);

        initViews();

        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);

        Glide.with(this)
                .load(movie.getPoster().getPreviewUrl())
                .into(pictureMovieAboutMovieActivity);

        titleMovieAboutMovieActivity.setText(movie.getName());
        yearMovieAboutMovieActivity.setText(String.valueOf(movie.getYear()));
        descriptionMovieAboutMovieActivity.setText(movie.getDescription());
    }

    public static Intent createIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, AboutMovieView.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }

    private void initViews() {
        pictureMovieAboutMovieActivity = findViewById(R.id.pictureMovieAboutMovieActivity);
        titleMovieAboutMovieActivity = findViewById(R.id.titleMovieAboutMovieActivity);
        yearMovieAboutMovieActivity = findViewById(R.id.yearMovieAboutMovieActivity);
        descriptionMovieAboutMovieActivity = findViewById(R.id.descriptionMovieAboutMovieActivity);
    }
}