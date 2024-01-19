package com.example.muviesmobileapp.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.muviesmobileapp.models.ApiFactory;
import com.example.muviesmobileapp.models.Movie;
import com.example.muviesmobileapp.models.MovieResponse;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<List<Movie>> movieResponseMutableLiveData = new MutableLiveData<>();

    public LiveData<Boolean> getBooleanMutableLiveData() {
        return booleanMutableLiveData;
    }

    private final MutableLiveData<Boolean> booleanMutableLiveData = new MutableLiveData<>(false);
    private int page = 1;

    public LiveData<List<Movie>> getMovieResponseLiveData() {
        return movieResponseMutableLiveData;
    }


    public MainViewModel(@NonNull Application application) {
        super(application);
        loadMovies();
    }

    public void loadMovies() {

        Boolean isLoading = booleanMutableLiveData.getValue();

        if (isLoading != null && isLoading) return;

        Disposable disposable = ApiFactory.apiService.loadMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable1 -> booleanMutableLiveData.setValue(true))
                .doAfterTerminate(() -> booleanMutableLiveData.setValue(false))
                .subscribe(movieResponse -> {
                    List<Movie> loadedMovies = movieResponseMutableLiveData.getValue();

                    if (loadedMovies != null) {
                        loadedMovies.addAll(movieResponse.getMovies());
                        movieResponseMutableLiveData.setValue(loadedMovies);
                    } else {
                        movieResponseMutableLiveData.setValue(movieResponse.getMovies());
                    }
                    page++;
                }, throwable -> System.out.println(throwable.toString())
                );
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
