package com.example.muviesmobileapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.muviesmobileapp.R;
import com.example.muviesmobileapp.models.adapters.ReviewAdapter;
import com.example.muviesmobileapp.models.api.responses.movies.Movie;
import com.example.muviesmobileapp.models.adapters.TrailerAdapter;
import com.example.muviesmobileapp.viewmodels.AboutMovieViewModel;

public class AboutMovieActivity extends AppCompatActivity {
    private final static String EXTRA_MOVIE = "movie";
    private ImageView pictureMovieAboutMovieActivity;
    private TextView titleMovieAboutMovieActivity;
    private TextView yearMovieAboutMovieActivity;
    private TextView descriptionMovieAboutMovieActivity;
    private AboutMovieViewModel model;
    private RecyclerView trailerRecycler;
    private RecyclerView reviewRecycler;
    private ImageView imageViewStar;
    TrailerAdapter trailerAdapter;
    ReviewAdapter reviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_movie_view);

        initViews();

        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);

        model.loadTrailersMovies(movie.getId());
        model.loadReviews(movie.getId());

        Glide.with(this)
                .load(movie.getPoster().getPreviewUrl())
                .into(pictureMovieAboutMovieActivity);

        model.getTrailersMoviesResponseLiveData().observe(this,
                trailers -> trailerAdapter.setTrailers(trailers));

        model.getReviewLiveData().observe(this, reviews -> reviewAdapter.setReviews(reviews));

        trailerAdapter.setOnTrailerClickListener(trailer -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(trailer.getUrlTrailerMovie()));
            startActivity(intent);
        });

        Drawable starOff = ContextCompat.getDrawable(AboutMovieActivity.this, android.R.drawable.star_big_off);
        Drawable starOn = ContextCompat.getDrawable(AboutMovieActivity.this, android.R.drawable.star_big_on);

        model.getFavoriteMovie(movie.getId()).observe(this, movieFromDb -> {
            if (movieFromDb == null) {
                imageViewStar.setImageDrawable(starOff);
                imageViewStar.setOnClickListener(v -> model.insertMovieDb(movie));
            } else {
                imageViewStar.setImageDrawable(starOn);
                imageViewStar.setOnClickListener(v -> model.removeMovieDb(movie.getId()));
            }
        });

        titleMovieAboutMovieActivity.setText(movie.getName());
        yearMovieAboutMovieActivity.setText(String.valueOf(movie.getYear()));
        descriptionMovieAboutMovieActivity.setText(movie.getDescription());
    }

    public static Intent createIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, AboutMovieActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }

    private void initViews() {

        model = new ViewModelProvider(this).get(AboutMovieViewModel.class);
        pictureMovieAboutMovieActivity = findViewById(R.id.pictureMovieAboutMovieActivity);
        titleMovieAboutMovieActivity = findViewById(R.id.titleMovieAboutMovieActivity);
        yearMovieAboutMovieActivity = findViewById(R.id.yearMovieAboutMovieActivity);
        descriptionMovieAboutMovieActivity = findViewById(R.id.descriptionMovieAboutMovieActivity);
        imageViewStar = findViewById(R.id.favoriteStar);

        trailerRecycler = findViewById(R.id.trailersRecyclerView);
        reviewRecycler = findViewById(R.id.reviewRecyclerView);
        trailerAdapter = new TrailerAdapter();
        reviewAdapter = new ReviewAdapter();
        trailerRecycler.setAdapter(trailerAdapter);
        reviewRecycler.setAdapter(reviewAdapter);
    }
}