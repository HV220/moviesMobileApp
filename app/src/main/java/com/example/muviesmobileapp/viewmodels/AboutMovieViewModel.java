package com.example.muviesmobileapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.muviesmobileapp.models.ApiFactory;

import com.example.muviesmobileapp.models.Trailer;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

import io.reactivex.rxjava3.schedulers.Schedulers;

public class AboutMovieViewModel extends AndroidViewModel {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<List<Trailer>> trailersMoviesResponseLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoadingTrailersMovies = new MutableLiveData<>(false);

    public AboutMovieViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Boolean> getIsLoadingTrailersMovies() {
        return isLoadingTrailersMovies;
    }

    public LiveData<List<Trailer>> getTrailersMoviesResponseLiveData() {
        return trailersMoviesResponseLiveData;
    }

    public void loadTrailersMovies(int id) {
        Disposable disposable = ApiFactory.apiService.loadTrailersMovies(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(trailerMovieResponse -> trailerMovieResponse.getTrailersMovieList().get(0)
                        .getVideos().getTrailersMovie())
                .doOnSubscribe(disposable1 -> isLoadingTrailersMovies.setValue(true))
                .doAfterTerminate(() -> isLoadingTrailersMovies.setValue(false))
                .subscribe(trailersMoviesResponseLiveData::setValue,
                        throwable -> System.out.println(throwable.toString()));
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
