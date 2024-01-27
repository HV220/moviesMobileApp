package com.example.muviesmobileapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.muviesmobileapp.models.api.responses.movies.Movie;
import com.example.muviesmobileapp.models.db.MovieDao;
import com.example.muviesmobileapp.models.db.MovieDatabase;

import java.util.List;

public class FavoriteMovieViewModel extends AboutMovieViewModel {
    private final MovieDao movieDao;

    public FavoriteMovieViewModel(@NonNull Application application) {
        super(application);
        movieDao = MovieDatabase.getInstance(application).movieDao();
    }

    public LiveData<List<Movie>> getAllMoviesDb() {
        return  movieDao.getAllFavoriteMovies();
    }
}
