package com.example.muviesmobileapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.muviesmobileapp.R;
import com.example.muviesmobileapp.models.adapters.MoviesAdapter;
import com.example.muviesmobileapp.viewmodels.MainViewModel;

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

        moviesAdapter.setOnReachEndListener(() -> {
            mainViewModel.loadMovies();
        });

        mainViewModel.getIsLoadingMovies().observe(this, isLoading -> {
            if (isLoading) progressBar.setVisibility(View.VISIBLE);
            else progressBar.setVisibility(View.GONE);
        });

        moviesAdapter.setOnClickListener((movie) -> {
            Intent detailedMovieView = AboutMovieActivity.createIntent(MainActivity.this, movie);
            startActivity(detailedMovieView);
        });
    }

    private void init() {
        recyclerView = findViewById(R.id.recycler_mainActivity);
        progressBar = findViewById(R.id.progress_circular_loading_MainActivity);

        moviesAdapter = new MoviesAdapter();

        recyclerView.setAdapter(moviesAdapter);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemFavorite) {
            Intent intent = FavoriteMovieActivity.createIntent(MainActivity.this);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}