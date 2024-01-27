package com.example.muviesmobileapp.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.muviesmobileapp.models.api.responses.ApiFactory;

import com.example.muviesmobileapp.models.api.responses.aboutmovie.reviews.Review;
import com.example.muviesmobileapp.models.api.responses.aboutmovie.trailers.Trailer;
import com.example.muviesmobileapp.models.api.responses.movies.Movie;
import com.example.muviesmobileapp.models.db.MovieDao;
import com.example.muviesmobileapp.models.db.MovieDatabase;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

import io.reactivex.rxjava3.schedulers.Schedulers;

public class AboutMovieViewModel extends AndroidViewModel {
    private final static String TAG = "AboutMovieViewModel";
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<List<Trailer>> trailersMoviesResponseLiveData = new MutableLiveData<>();
    MutableLiveData<List<Review>> reviewLiveData = new MutableLiveData<>();
    private final MovieDao movieDao;

    public AboutMovieViewModel(@NonNull Application application) {

        super(application);
        movieDao = MovieDatabase.getInstance(application).movieDao();
    }

    public LiveData<List<Review>> getReviewLiveData() {
        return reviewLiveData;
    }

    public LiveData<List<Trailer>> getTrailersMoviesResponseLiveData() {
        return trailersMoviesResponseLiveData;
    }

    public LiveData<Movie> getFavoriteMovie(int id) {
        return movieDao.getFavoriteMovieById(id);
    }

    public void insertMovieDb(Movie movie) {
        Disposable disposable = movieDao.insertMovie(movie)
                .subscribeOn(Schedulers.io())
                .subscribe();

        compositeDisposable.add(disposable);
    }

    public void removeMovieDb(int movieId) {
        Disposable disposable = movieDao.removeMovie(movieId)
                .subscribeOn(Schedulers.io())
                .subscribe();

        compositeDisposable.add(disposable);
    }

    public void loadTrailersMovies(int id) {
        Disposable disposable = ApiFactory.apiService.loadTrailersMovies(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(trailerMovieResponse -> trailerMovieResponse.getTrailersMovieList().get(0)
                        .getVideos().getTrailersMovie())
                .subscribe(trailersMoviesResponseLiveData::setValue,
                        throwable -> System.out.println(throwable.toString()));
        compositeDisposable.add(disposable);
    }

    public void loadReviews(int movieId) {
        Disposable disposable = ApiFactory.apiService.loadReviewResponse(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(reviewResponse -> reviewLiveData.setValue(reviewResponse.getReviews()),
                        throwable -> Log.d(TAG, throwable.toString()));
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
