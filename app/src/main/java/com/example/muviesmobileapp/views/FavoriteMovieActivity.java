package com.example.muviesmobileapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.muviesmobileapp.R;
import com.example.muviesmobileapp.models.adapters.MoviesAdapter;
import com.example.muviesmobileapp.viewmodels.FavoriteMovieViewModel;

public class FavoriteMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_movies);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewMovies);
        MoviesAdapter moviesAdapter = new MoviesAdapter();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(moviesAdapter);

        FavoriteMovieViewModel model = new ViewModelProvider(this)
                .get(FavoriteMovieViewModel.class);

        model.getAllMoviesDb().observe(this, moviesAdapter::setMovies);

        moviesAdapter.setOnClickListener((movie) -> {
            Intent detailedMovieView = AboutMovieActivity.createIntent(FavoriteMovieActivity.this, movie);
            startActivity(detailedMovieView);
        });
    }

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, FavoriteMovieActivity.class);
        return intent;
    }
}