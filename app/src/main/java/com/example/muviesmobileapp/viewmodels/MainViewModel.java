package com.example.muviesmobileapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.muviesmobileapp.models.api.responses.ApiFactory;
import com.example.muviesmobileapp.models.api.responses.movies.Movie;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<List<Movie>> movieResponseMutableLiveData = new MutableLiveData<>();

    private final MutableLiveData<Boolean> isLoadingMovies = new MutableLiveData<>(false);

    private int page = 1;

    public LiveData<Boolean> getIsLoadingMovies() {
        return isLoadingMovies;
    }

    public LiveData<List<Movie>> getMovieResponseLiveData() {
        return movieResponseMutableLiveData;
    }


    public MainViewModel(@NonNull Application application) {
        super(application);
        loadMovies();
    }

    public void loadMovies() {

        Boolean isLoading = isLoadingMovies.getValue();

        if (isLoading != null && isLoading) return;

        Disposable disposable = ApiFactory.apiService.loadMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable1 -> isLoadingMovies.setValue(true))
                .doAfterTerminate(() -> isLoadingMovies.setValue(false))
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
