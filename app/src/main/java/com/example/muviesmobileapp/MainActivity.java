package com.example.muviesmobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.muviesmobileapp.models.ApiFactory;
import com.example.muviesmobileapp.models.Movie;
import com.example.muviesmobileapp.models.MoviesAdapter;
import com.example.muviesmobileapp.viewmodel.MainViewModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private MainViewModel mainViewModel;
    private RecyclerView recyclerView;
    private MoviesAdapter moviesAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.init();

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        mainViewModel.getMovieResponseLiveData().observe(this,
                movies -> moviesAdapter.setMovies(movies));
        moviesAdapter.setOnReachEndListener(() -> mainViewModel.loadMovies());

        mainViewModel.getBooleanMutableLiveData().observe(this, isLoading -> {
            if (isLoading) progressBar.setVisibility(View.VISIBLE);
            else progressBar.setVisibility(View.GONE);
        });
        moviesAdapter.setOnClickListener(new MoviesAdapter.onMovieListener() {
            @Override
            public void onClick(Movie movie) {
                Intent detailedMovieView = AboutMovieView.createIntent(MainActivity.this, movie);
                startActivity(detailedMovieView);
            }
        });
    }

    private void init() {
        recyclerView = findViewById(R.id.recycler_mainActivity);
        progressBar = findViewById(R.id.progress_circular_loading_MainActivity);

        moviesAdapter = new MoviesAdapter();

        recyclerView.setAdapter(moviesAdapter);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }
}