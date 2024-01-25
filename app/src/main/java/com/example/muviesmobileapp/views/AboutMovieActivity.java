package com.example.muviesmobileapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.muviesmobileapp.R;
import com.example.muviesmobileapp.models.Movie;
import com.example.muviesmobileapp.models.TrailerAdapter;
import com.example.muviesmobileapp.viewmodels.AboutMovieViewModel;

public class AboutMovieActivity extends AppCompatActivity {
    private final static String EXTRA_MOVIE = "movie";
    private ImageView pictureMovieAboutMovieActivity;
    private TextView titleMovieAboutMovieActivity;
    private TextView yearMovieAboutMovieActivity;
    private TextView descriptionMovieAboutMovieActivity;
    private AboutMovieViewModel aboutMovieModel;
    private RecyclerView recyclerView;
    TrailerAdapter trailerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_movie_view);

        initViews();

        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);

        aboutMovieModel.loadTrailersMovies(movie.getId());

        Glide.with(this)
                .load(movie.getPoster().getPreviewUrl())
                .into(pictureMovieAboutMovieActivity);

        aboutMovieModel.getTrailersMoviesResponseLiveData().observe(this,
                trailers -> trailerAdapter.setTrailers(trailers));

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

        aboutMovieModel = new ViewModelProvider(this).get(AboutMovieViewModel.class);

        pictureMovieAboutMovieActivity = findViewById(R.id.pictureMovieAboutMovieActivity);
        titleMovieAboutMovieActivity = findViewById(R.id.titleMovieAboutMovieActivity);
        yearMovieAboutMovieActivity = findViewById(R.id.yearMovieAboutMovieActivity);
        descriptionMovieAboutMovieActivity = findViewById(R.id.descriptionMovieAboutMovieActivity);

        recyclerView = findViewById(R.id.trailersRecyclerView);
        trailerAdapter = new TrailerAdapter();
        recyclerView.setAdapter(trailerAdapter);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
    }
}